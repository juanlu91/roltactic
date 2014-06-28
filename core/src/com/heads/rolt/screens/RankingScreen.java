package com.heads.rolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.heads.rolt.MyGame;

public class RankingScreen implements Screen {
	
	private MyGame g;
	private Skin skin;
	private Stage stage;
	private SpriteBatch batch;
	private TextButton btn_back;
	
	public RankingScreen(MyGame g){
		this.g = g;
	}
	

	@Override
	public void show() {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		batch = new SpriteBatch();
		
		Label lbl_position = new Label("Position", skin, "default");
		lbl_position.setAlignment(Align.center);
		lbl_position.setPosition(200 - lbl_position.getWidth()/2, 400);
		
		Label lbl_name = new Label("Name", skin, "default");
		lbl_name.setAlignment(Align.center);
		lbl_name.setPosition(400 - lbl_name.getWidth()/2, 400);
		
		Label lbl_experience = new Label("Experience", skin, "default");
		lbl_experience.setAlignment(Align.center);
		lbl_experience.setPosition(600 - lbl_experience.getWidth()/2, 400);
		
		btn_back = new TextButton("Back", skin, "default");
		btn_back.setWidth(100f);
		btn_back.setHeight(50f);
		btn_back.setPosition(
				Gdx.graphics.getWidth()/2 - btn_back.getWidth()/2, 50);
		btn_back.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				btn_back.setText("Back");
				g.setScreen(g.mainMenuScreen);
			}
		});
		
		stage.addActor(lbl_name);
		stage.addActor(lbl_experience);
		stage.addActor(lbl_position);
		
		stage.addActor(btn_back);
		
		Gdx.input.setInputProcessor(stage);
		
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
