package com.heads.rolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.net.Socket;
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

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();
		batch = new SpriteBatch();
		socket = Gdx.net.newClientSocket(Protocol.TCP, "localhost", 8882, null);

		Label lbl_username = new Label("Username", skin, "default");
		lbl_username.setAlignment(Align.center);
		lbl_username.setPosition(550, 425);

		Label lbl_exp = new Label("Exp", skin, "default");
		lbl_exp.setAlignment(Align.center);
		lbl_exp.setPosition(700, 425);
		
		Label lbl_waiting = new Label("Waiting for an opponent...", skin, "default");
		lbl_waiting.setAlignment(Align.center);
		lbl_waiting.setPosition((Gdx.graphics.getWidth() - lbl_waiting.getWidth()) / 2, 200);
		
		stage.addActor(lbl_username);
		stage.addActor(lbl_exp);
		stage.addActor(lbl_waiting);

		Gdx.input.setInputProcessor(stage);
		
		
		
		
		// Connections Thread
		
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				
			}
			
		});
		
		
		
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
