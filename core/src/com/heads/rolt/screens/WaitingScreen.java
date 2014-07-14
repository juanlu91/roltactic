package com.heads.rolt.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.heads.rolt.MyGame;
import com.heads.rolt.entities.MyCharacter;
import com.heads.rolt.entities.Race;
import com.heads.rolt.utils.Net;

public class WaitingScreen implements Screen {

	private MyGame g;
	private Socket socket;

	private Skin skin;
	private Stage stage;
	private SpriteBatch batch;

	private List<MyCharacter> my_team;
	protected JSONObject data;

	public WaitingScreen(MyGame g) {
		this.g = g;
		my_team = new ArrayList<MyCharacter>();
		data = null;
	}

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();
		batch = new SpriteBatch();

		Label lbl_username = new Label("Username", skin, "default");
		lbl_username.setAlignment(Align.center);
		lbl_username.setPosition(550, 425);

		Label lbl_exp = new Label("Exp", skin, "default");
		lbl_exp.setAlignment(Align.center);
		lbl_exp.setPosition(700, 425);

		Label lbl_waiting = new Label("Waiting for an opponent...", skin,
				"default");
		lbl_waiting.setAlignment(Align.center);
		lbl_waiting.setPosition(
				(Gdx.graphics.getWidth() - lbl_waiting.getWidth()) / 2, 200);

		stage.addActor(lbl_username);
		stage.addActor(lbl_exp);
		stage.addActor(lbl_waiting);

		Gdx.input.setInputProcessor(stage);

		// Connections Thread - Controller.java

		SocketHints socketHints = new SocketHints();
		// Socket will time our in 4 seconds
		socketHints.connectTimeout = 4000;

		try {
			socket = Gdx.net.newClientSocket(Protocol.TCP, Net.LOCALHOST_URL,
					Net.LOCALHOST_PORT, socketHints);

			if (socket.isConnected()) {

				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				OutputStream os = socket.getOutputStream();

				String message = in.readLine();
				JSONObject res = new JSONObject(message);
				String cmd = res.getString("cmd");

				if (cmd.equals("info")) {
					String data = res.getString("data");
					info(data);
				}

				JSONObject req = new JSONObject();
				req.put("cmd", "gameList");
				req.put("data", "");
				os.write(req.toString().getBytes());

				message = in.readLine();
				res = new JSONObject(message);
				cmd = res.getString("cmd");

				if (cmd.equals("gameList")) {
					JSONArray data_array = res.getJSONArray("data");
					info("Gamelist: " + data_array);

					List<JSONObject> team = new ArrayList<JSONObject>();
					for (MyCharacter c : my_team) {
						JSONObject pj = new JSONObject();
						JSONObject props = new JSONObject();
						props.put("x", c.getX());
						props.put("y", c.getY());
						props.put("attack", c.getAttack());
						props.put("defense", c.getDefense());
						props.put("health", c.getHealth());
						props.put("movement", c.getMovement());
						props.put("range", c.getY());

						pj.put("race", c.getRace());
						pj.put("props", props);

						team.add(pj);
					}

					if (data_array.length() == 0) {
						req = new JSONObject();
						req.put("cmd", "newGame");

						Date d = Calendar.getInstance().getTime();
						Long id = d.getTime();

						JSONObject params = new JSONObject();
						params.put("id", id.toString());
						params.put("team", team);

						req.put("data", params);

						os.write(req.toString().getBytes());

						info("New game created! ID: " + id);

						waitForResponse(in);

					} else {
						System.out.println("cargar partida...");

						req = new JSONObject();
						req.put("cmd", "loadGame");
						req.put("data", team);

						os.write(req.toString().getBytes());

						waitForResponse(in);
					}
				}
			}
		} catch (GdxRuntimeException e) {
			lbl_waiting.setColor(Color.RED);
			lbl_waiting.setText(e.getMessage());
		} catch (IOException e) {
			lbl_waiting.setColor(Color.RED);
			lbl_waiting
					.setText("Cannot connect to the server... Try again in a few minutes.");
		} catch (JSONException e) {
			lbl_waiting.setColor(Color.RED);
			lbl_waiting
					.setText("Fatal error. Please, contact with the admin by email (roltactic_team@example.com) or visit our web (roltactic.appspot.com/contact)");
		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(189 / 255f, 236 / 255f, 255 / 255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		batch.begin();
		stage.draw();
		batch.end();

		if (data != null){
			startGame(data);
		}
	}

	private void info(String data) {
		System.out.println(data);
	}

	private void startGame(JSONObject data) {
		List<MyCharacter> opponent_team = new ArrayList<MyCharacter>();

		try {
			String game_id = data.getString("game_id");
			JSONArray op_team_json = data.getJSONArray("opponent_team");
			
			this.data = null;

			for (int i = 0; i < op_team_json.length(); i++) {
				JSONObject pj = op_team_json.getJSONObject(i);
				Race race = Race.valueOf(pj.getString("race"));

				JSONObject props = pj.getJSONObject("props");
				Integer x = props.getInt("x");
				Integer y = props.getInt("y");
				Integer attack = props.getInt("attack");
				Integer defense = props.getInt("defense");
				Integer health = props.getInt("health");
				Integer movement = props.getInt("movement");
				Integer range = props.getInt("range");

				MyCharacter c = null;

				switch (race) {
				case WARRIOR:
					c = new MyCharacter(Race.WARRIOR, new Sprite(new Texture(
							"img/characters/warrior.png")), attack, defense,
							health, movement, range);
					break;
				case ARCHER:
					c = new MyCharacter(Race.ARCHER, new Sprite(new Texture(
							"img/characters/archer.png")), attack, defense,
							health, movement, range);
					break;
				case MAGE:
					c = new MyCharacter(Race.MAGE, new Sprite(new Texture(
							"img/characters/mage.png")), attack, defense,
							health, movement, range);
					break;
				case HEALER:
					c = new MyCharacter(Race.HEALER, new Sprite(new Texture(
							"img/characters/healer.png")), attack, defense,
							health, movement, range);
					break;
				}
				c.setX(x);
				c.setX(y);
				opponent_team.add(c);
			}
			
			g.gameWorld.setSocket(socket);
			g.gameWorld.setGame_id(game_id);
			g.gameWorld.setOpponentTeam(opponent_team);
			g.setScreen(g.gameWorld);			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void setTeam(List<MyCharacter> my_team) {
		this.my_team = my_team;
	}

	protected void waitForResponse(final BufferedReader in) {

		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String message = in.readLine();
					JSONObject res = new JSONObject(message);
					String cmd = res.getString("cmd");

					if (cmd.equals("startGame")) {
						data = res.getJSONObject("data");
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

		});
		t.start();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
