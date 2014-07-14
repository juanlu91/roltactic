package com.heads.rolt.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player extends Sprite {

	private TiledMapTileLayer collisionLayer;

	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.collisionLayer = collisionLayer;
	}

	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}

	public void update(float delta) {
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
}
