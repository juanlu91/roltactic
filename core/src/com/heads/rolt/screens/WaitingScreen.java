package com.heads.rolt.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.heads.rolt.MyGame;

public class WaitingScreen implements Screen {
	private MyGame g;
	private Skin skin;
	private Stage stage;
	private SpriteBatch batch;
	private Socket socket;

	public WaitingScreen(MyGame g) {
		this.g = g;
	}

	@SuppressWarnings("unchecked")
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
		socket = Gdx.net.newClientSocket(Protocol.TCP, "192.168.56.1", 8882,
				socketHints);
		if (socket.isConnected()) {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				
				String message = in.readLine();
				JSONObject json = new JSONObject(message);
				String cmd = json.getString("type");
				
				if(cmd.equals("info")){
					String data = json.getString("data");
					info(data);
				}
				
				JSONObject data = new JSONObject();
				data.put("command", "gameList");
				data.put("data", "");
				socket.getOutputStream().write(data.toString().getBytes());
				
				message = in.readLine();
				json = new JSONObject(message);
				cmd = json.getString("type");
				
				if(cmd.equals("gameList")){
					JSONArray data_array = json.getJSONArray("data");
					
					List<String> pjs = new ArrayList<String>();
					pjs.add("warrior");
					
					if(data_array.length() == 0){
						data = new JSONObject();
						data.put("command", "newGame");
						
						Date d = Calendar.getInstance().getTime();
						Long id = d.getTime();
						
						JSONObject params = new JSONObject();
						params.put("id", id.toString());
						params.put("team", pjs);
						
						data.put("data", params);
						
						socket.getOutputStream().write(data.toString().getBytes());
					} else {
						System.out.println("cargar partida...");
						data = new JSONObject();
						data.put("command", "loadGame");					
						data.put("data", pjs);
					}				
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch(JSONException e){
				e.printStackTrace();
			}
		}
	}

	private void info(String data) {
		System.out.println(data);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(189 / 255f, 236 / 255f, 255 / 255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		batch.begin();
		stage.draw();
		batch.end();
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
