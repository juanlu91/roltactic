package com.heads.rolt.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.heads.rolt.MyGame;
import com.heads.rolt.entities.Player;

public class GameWorld implements Screen, InputProcessor {

	private MyGame g;
	private TiledMap map;
	private OrthogonalTiledMapRenderer renderer;
	private OrthographicCamera camera;

	private Player player;
	private ShapeRenderer sr;

	public GameWorld(MyGame g) {
		this.g = g;
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

		player = new Player(new Sprite(
				new Texture("img/characters/warrior2.png")),
				(TiledMapTileLayer) map.getLayers().get(0));

		player.setPosition(0.0f, 8.0f);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(189 / 255f, 236 / 255f, 255 / 255f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();

		renderer.setView(camera);
		renderer.render();

		renderer.getSpriteBatch().setProjectionMatrix(camera.combined);
		renderer.getSpriteBatch().begin();
		player.draw(renderer.getSpriteBatch(), 1f);
		renderer.getSpriteBatch().end();

		sr.begin(ShapeType.Line);
		sr.setColor(1, 1, 0, 1);
		sr.rect(player.getX(), player.getY(), player.getWidth(),
				player.getHeight());
		sr.end();
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

	@Override
	public boolean keyDown(int keycode) {
		System.out
				.println(String.format("click on: %s", Keys.toString(keycode)));

		switch (keycode) {
		case Keys.RIGHT:
			player.setX(player.getX()
					+ player.getCollisionLayer().getTileWidth());
			break;
		case Keys.LEFT:
			player.setX(player.getX()
					- player.getCollisionLayer().getTileWidth());
			break;
		case Keys.DOWN:
			player.setY(player.getY()
					- player.getCollisionLayer().getTileHeight());
			break;
		case Keys.UP:
			player.setY(player.getY()
					+ player.getCollisionLayer().getTileHeight());
			break;
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

		System.out.println(String.format("player is in %f %f", player.getX()
				+ player.getWidth() / 2, player.getY()));

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

}
