package com.heads.rolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.heads.rolt.MyGame;

public class MainMenuScreen implements Screen {
	private MyGame g;
	private Skin skin;
	private Stage stage;
	
	public MainMenuScreen(MyGame g){
		this.g = g;
	}

	@Override
	public void show() {
		stage = new Stage();
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		
		Label lbl_username = new Label("Username", skin, "default");
		lbl_username.setAlignment(Align.center);
		lbl_username.setPosition(550, 425);
		
		Label lbl_exp = new Label("Exp", skin, "default");
		lbl_exp.setAlignment(Align.center);
		lbl_exp.setPosition(700, 425);

		final TextButton btn_play = new TextButton("Play", skin, "default");
		btn_play.setWidth(200f);
		btn_play.setHeight(50f);
		btn_play.setPosition((Gdx.graphics.getWidth() - btn_play.getWidth()) / 2, 270);
		btn_play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				btn_play.setText("Select Team");
				g.setScreen(g.teamScreen);
			}
		});
		
		final TextButton btn_ranking = new TextButton("Ranking", skin, "default");
		btn_ranking.setWidth(200f);
		btn_ranking.setHeight(50f);
		btn_ranking.setPosition((Gdx.graphics.getWidth() - btn_ranking.getWidth()) / 2, 200);
		btn_ranking.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				btn_ranking.setText("Go to Ranking");
			}
		});
		
		final TextButton btn_options = new TextButton("Options", skin, "default");
		btn_options.setWidth(200f);
		btn_options.setHeight(50f);
		btn_options.setPosition((Gdx.graphics.getWidth() - btn_options.getWidth()) / 2, 130);
		btn_options.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				btn_options.setText("Go to Options");
			}
		});
		
		final TextButton btn_quit = new TextButton("Quit", skin, "default");
		btn_quit.setWidth(200f);
		btn_quit.setHeight(50f);
		btn_quit.setPosition((Gdx.graphics.getWidth() - btn_quit.getWidth()) / 2, 60);
		btn_quit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				btn_quit.setText("Quit game");
			}
		});

		stage.addActor(lbl_username);
		stage.addActor(lbl_exp);
		stage.addActor(btn_play);
		stage.addActor(btn_ranking);
		stage.addActor(btn_options);
		stage.addActor(btn_quit);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(189/255f, 236/255f, 255/255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
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
