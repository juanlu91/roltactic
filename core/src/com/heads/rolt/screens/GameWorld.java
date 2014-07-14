package com.heads.rolt.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.net.Socket;
import com.heads.rolt.MyGame;
import com.heads.rolt.entities.MyCharacter;

public class GameWorld implements Screen, InputProcessor {

	private MyGame g;
	private String game_id;
	private Socket socket;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	private MyCharacter my_player, op_player;
	private List<MyCharacter> my_team;
	private List<MyCharacter> opponent_team;

	private ShapeRenderer sr;

	private JSONArray move;

	public GameWorld(MyGame g) {
		this.g = g;
		game_id = null;
		my_team = new ArrayList<MyCharacter>();
		opponent_team = new ArrayList<MyCharacter>();
		move = null;
	}

	@Override
	public void show() {
		map = new TmxMapLoader().load("maps/map.tmx");

		renderer = new OrthogonalTiledMapRenderer(map);
		sr = new ShapeRenderer();

		camera = new OrthographicCamera(Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());

		for (MyCharacter c : my_team) {
			// c.setPosition(c.getX(), c.getY());
			c.setCollisionLayer((TiledMapTileLayer) map.getLayers().get(0));
		}

		for (MyCharacter c : opponent_team) {
			// c.setPosition(c.getX(), c.getY());
			c.setCollisionLayer((TiledMapTileLayer) map.getLayers().get(0));
		}

		my_player = my_team.get(0);
		my_player.setPosition(0.0f, 8.0f);

		op_player = opponent_team.get(0);
		op_player.setPosition(8.0f, 8.0f);

		Gdx.input.setInputProcessor(this);
		
		final BufferedReader in = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		
		Timer timer = new Timer();
		TimerTask timerTask = new TimerTask(){
			@Override
			public void run() {
				try {
					String message = in.readLine();
					System.out.println("message: "+message);
					JSONObject res = new JSONObject(message);
					String cmd = res.getString("cmd");

					if (cmd.equals("updateMove")) {
						move = res.getJSONArray("data");
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, 100);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(189 / 255f, 236 / 255f, 255 / 255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		renderer.setView(camera);
		renderer.render();

		renderer.getSpriteBatch().begin();
		my_player.draw(renderer.getSpriteBatch(), 1f);
		op_player.draw(renderer.getSpriteBatch(), 1f);
		renderer.getSpriteBatch().end();

		sr.begin(ShapeType.Line);
		sr.setColor(1, 1, 0, 1);
		sr.rect(my_player.getX(), my_player.getY(), my_player.getWidth(),
				my_player.getHeight());
		sr.end();

		if (move != null) {
			updateMove(move);
		}
	}

	public void updateMove(JSONArray move) {
		try {
			Float x = new Float(move.getDouble(0));
			Float y = new Float(move.getDouble(1));
			op_player.setPosition(x, y);
			this.move = null;
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		System.out
				.println(String.format("click on: %s", Keys.toString(keycode)));

		switch (keycode) {
		case Keys.RIGHT:
			my_player.setX(my_player.getX()
					+ my_player.getCollisionLayer().getTileWidth());
			break;
		case Keys.LEFT:
			my_player.setX(my_player.getX()
					- my_player.getCollisionLayer().getTileWidth());
			break;
		case Keys.DOWN:
			my_player.setY(my_player.getY()
					- my_player.getCollisionLayer().getTileHeight());
			break;
		case Keys.UP:
			my_player.setY(my_player.getY()
					+ my_player.getCollisionLayer().getTileHeight());
			break;
		}

		List<Float> pos = new ArrayList<Float>();
		pos.add(my_player.getX());
		pos.add(my_player.getY());

		// Mandar position
		try {
			JSONObject req = new JSONObject();
			req.put("cmd", "move");

			JSONObject data = new JSONObject();
			data.put("game_id", game_id);
			data.put("pos", pos);
			req.put("data", data);

			socket.getOutputStream().write(req.toString().getBytes());

		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		// int posX = (int) (screenX /
		// player.getCollisionLayer().getTileWidth());
		// int posY = (int) (screenY /
		// (player.getCollisionLayer().getTileHeight() +
		// player.getCollisionLayer().getHeight()));
		//
		// System.out.println(String.format("click world on: %d, %d", posX,
		// posY));
		// player.setPosition(screenX, screenY);

		System.out.println(String.format("player is in %f %f", my_player.getX()
				+ my_player.getWidth() / 2, my_player.getY()));

		System.out.println(String.format("click world on: %d, %d", screenX,
				screenY));

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}

	public void setTeam(List<MyCharacter> my_team) {
		this.my_team = my_team;
	}

	public void setOpponentTeam(List<MyCharacter> opponent_team) {
		this.opponent_team = opponent_team;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.update();
	}

	@Override
	public void hide() {
		dispose();
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
		map.dispose();
		renderer.dispose();
	}
}
