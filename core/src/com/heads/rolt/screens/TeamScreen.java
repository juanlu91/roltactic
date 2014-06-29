package com.heads.rolt.screens;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.heads.rolt.MyGame;

public class TeamScreen implements Screen {
	private MyGame g;
	private Skin skin;
	private Stage stage;
	private SpriteBatch batch;
	private TextButton btn_warrior, btn_archer, btn_mage, btn_shaman, btn_play;
	private Map<Integer, Sprite> map_img_pjs;
	private Map<Integer, TextButton> map_rmv_pjs;
	private Sprite img_pj1, img_pj2, img_pj3, img_pj4;
	private Integer team_index, pjs_selected;
	private List<Integer> list_pjs_deleted;

	public TeamScreen(MyGame g) {
		this.g = g;
	}

	@Override
	public void show() {
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));
		stage = new Stage();
		batch = new SpriteBatch();
		map_img_pjs = new HashMap<Integer, Sprite>();
		map_rmv_pjs = new HashMap<Integer, TextButton>();
		team_index = 0;
		pjs_selected = 0;
		list_pjs_deleted = new ArrayList<Integer>();
		
		
		Label lbl_username = new Label("Username", skin, "default");
		lbl_username.setAlignment(Align.center);
		lbl_username.setPosition(550, 425);

		Label lbl_exp = new Label("Exp", skin, "default");
		lbl_exp.setAlignment(Align.center);
		lbl_exp.setPosition(700, 425);

		btn_warrior = new TextButton("Warrior", skin, "default");
		btn_warrior.setWidth(100f);
		btn_warrior.setHeight(50f);
		btn_warrior.setPosition(160 - btn_warrior.getWidth() / 2, 300);
		btn_warrior.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				// btn_warrior.setText("Warrior added!");
				if (pjs_selected < 4) {
					if(list_pjs_deleted.isEmpty()) {
						map_img_pjs.get(team_index).setTexture(
								new Texture(Gdx.files.internal("img/characters/warrior.png")));
						map_rmv_pjs.get(team_index).setVisible(true);
						team_index++;
						pjs_selected++;
					}
					else {
						map_img_pjs.get(list_pjs_deleted.get(0)).setTexture(
								new Texture(Gdx.files.internal("img/characters/warrior.png")));
						map_rmv_pjs.get(list_pjs_deleted.get(0)).setVisible(true);
						team_index++;
						pjs_selected++;
						list_pjs_deleted.remove(0);
					}
				}
			}
		});

		btn_archer = new TextButton("Archer", skin, "default");
		btn_archer.setWidth(100f);
		btn_archer.setHeight(50f);
		btn_archer.setPosition(320 - btn_warrior.getWidth() / 2, 300);
		btn_archer.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (pjs_selected < 4) {
					if(list_pjs_deleted.isEmpty()) {
						map_img_pjs.get(team_index).setTexture(
								new Texture(Gdx.files.internal("img/characters/archer.png")));
						map_rmv_pjs.get(team_index).setVisible(true);
						team_index++;
						pjs_selected++;
					}
					else {
						map_img_pjs.get(list_pjs_deleted.get(0)).setTexture(
								new Texture(Gdx.files.internal("img/characters/archer.png")));
						map_rmv_pjs.get(list_pjs_deleted.get(0)).setVisible(true);
						team_index++;
						pjs_selected++;
						list_pjs_deleted.remove(0);
					}
				}
			}
		});

		btn_mage = new TextButton("Mage", skin, "default");
		btn_mage.setWidth(100f);
		btn_mage.setHeight(50f);
		btn_mage.setPosition(480 - btn_warrior.getWidth() / 2, 300);
		btn_mage.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (pjs_selected < 4) {
					if(list_pjs_deleted.isEmpty()) {
						map_img_pjs.get(team_index).setTexture(
								new Texture(Gdx.files.internal("img/characters/mage.png")));
						map_rmv_pjs.get(team_index).setVisible(true);
						team_index++;
						pjs_selected++;
					}
					else {
						map_img_pjs.get(list_pjs_deleted.get(0)).setTexture(
								new Texture(Gdx.files.internal("img/characters/mage.png")));
						map_rmv_pjs.get(list_pjs_deleted.get(0)).setVisible(true);
						team_index++;
						pjs_selected++;
						list_pjs_deleted.remove(0);
					}
				}
			}
		});

		btn_shaman = new TextButton("Shaman", skin, "default");
		btn_shaman.setWidth(100f);
		btn_shaman.setHeight(50f);
		btn_shaman.setPosition(640 - btn_warrior.getWidth() / 2, 300);
		btn_shaman.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (pjs_selected < 4) {
					if(list_pjs_deleted.isEmpty()) {
						map_img_pjs.get(team_index).setTexture(
								new Texture(Gdx.files.internal("img/characters/shaman.png")));
						map_rmv_pjs.get(team_index).setVisible(true);
						team_index++;
						pjs_selected++;
					}
					else {
						map_img_pjs.get(list_pjs_deleted.get(0)).setTexture(
								new Texture(Gdx.files.internal("img/characters/shaman.png")));
						map_rmv_pjs.get(list_pjs_deleted.get(0)).setVisible(true);
						team_index++;
						pjs_selected++;
						list_pjs_deleted.remove(0);
					}
				}
			}
		});

		img_pj1 = new Sprite(new Texture(Gdx.files.internal("img/characters/no_pj.png")));
		img_pj2 = new Sprite(new Texture(Gdx.files.internal("img/characters/no_pj.png")));
		img_pj3 = new Sprite(new Texture(Gdx.files.internal("img/characters/no_pj.png")));
		img_pj4 = new Sprite(new Texture(Gdx.files.internal("img/characters/no_pj.png")));

		map_img_pjs.put(0, img_pj1);
		map_img_pjs.put(1, img_pj2);
		map_img_pjs.put(2, img_pj3);
		map_img_pjs.put(3, img_pj4);

		for (int i = 0; i < 4; i++) {
			final int k = i;
			final TextButton btn_rmv_pj = new TextButton("x", skin, "default");
			btn_rmv_pj.setWidth(25f);
			btn_rmv_pj.setHeight(25f);
			btn_rmv_pj.setPosition((i + 1) * 160 - btn_rmv_pj.getWidth() / 2,
					140);
			btn_rmv_pj.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					map_img_pjs.get(k).setTexture(
							new Texture(Gdx.files.internal("img/characters/no_pj.png")));
					
					btn_rmv_pj.setVisible(false);
					pjs_selected--;
					list_pjs_deleted.add(k);
					Collections.sort(list_pjs_deleted);
				}
			});
			btn_rmv_pj.setVisible(false);
			map_rmv_pjs.put(i, btn_rmv_pj);
		}
		
		Integer offset = 20;
		
		btn_play = new TextButton("Play", skin, "default");
		btn_play.setWidth(100f);
		btn_play.setHeight(50f);
		btn_play.setPosition(
				Gdx.graphics.getWidth()/2 + offset, 50);
		btn_play.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!btn_play.isDisabled())
					g.setScreen(g.waitingScreen);
			}
		});
		btn_play.setDisabled(true);
		btn_play.setColor(0f, 0f, 0f, 0.25f);
		

		final TextButton btn_back = new TextButton("Back", skin, "default");
		btn_back.setWidth(100f);
		btn_back.setHeight(50f);
		btn_back.setPosition(
				Gdx.graphics.getWidth()/2 - btn_back.getWidth() - offset, 50);
		btn_back.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				btn_back.setText("Select Team");
				g.setScreen(g.mainMenuScreen);
			}
		});

		stage.addActor(lbl_username);
		stage.addActor(lbl_exp);

		stage.addActor(btn_warrior);
		stage.addActor(btn_archer);
		stage.addActor(btn_mage);
		stage.addActor(btn_shaman);

		for (TextButton b : map_rmv_pjs.values())
			stage.addActor(b);

		stage.addActor(btn_play);
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
		batch.begin();
		batch.draw(img_pj1, 160 - img_pj1.getWidth() / 2, 175);
		batch.end();
		batch.begin();
		batch.draw(img_pj2, 320 - img_pj2.getWidth() / 2, 175);
		batch.end();
		batch.begin();
		batch.draw(img_pj3, 480 - img_pj3.getWidth() / 2, 175);
		batch.end();
		batch.begin();
		batch.draw(img_pj4, 640 - img_pj4.getWidth() / 2, 175);
		batch.end();

		if (pjs_selected == 4) {
			btn_warrior.setDisabled(true);
			btn_warrior.setColor(0f, 0f, 0f, 0.25f);

			btn_archer.setDisabled(true);
			btn_archer.setColor(0f, 0f, 0f, 0.25f);

			btn_mage.setDisabled(true);
			btn_mage.setColor(0f, 0f, 0f, 0.25f);

			btn_shaman.setDisabled(true);
			btn_shaman.setColor(0f, 0f, 0f, 0.25f);
			
			btn_play.setDisabled(false);
			btn_play.setColor(1f, 1f, 1f, 1f);
		} else {
			btn_play.setDisabled(true);
			btn_play.setColor(0f, 0f, 0f, 0.25f);
			
			btn_warrior.setDisabled(false);
			btn_warrior.setColor(1f, 1f, 1f, 1f);

			btn_archer.setDisabled(false);
			btn_archer.setColor(1f, 1f, 1f, 1f);

			btn_mage.setDisabled(false);
			btn_mage.setColor(1f, 1f, 1f, 1f);

			btn_shaman.setDisabled(false);
			btn_shaman.setColor(1f, 1f, 1f, 1f);
		}
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
