package com.heads.rolt.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PlayerTest extends Actor {

	public TextureRegion region;
	
	/** the movement velocity */
	private Vector2 velocity = new Vector2();

	private float speed = 60 * 2, gravity = 60 * 1.8f;

	private TiledMapTileLayer collisionLayer;

	public PlayerTest(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super();
		region = new TextureRegion(sprite);
		this.collisionLayer = collisionLayer;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		update(Gdx.graphics.getDeltaTime());
		
		Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
            getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
	}

	public void update(float delta) {

		float tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer
				.getTileHeight();
		boolean collisionX = false, collisionY = false;

		if (velocity.x < 0) {
			// top-left
			collisionX = collisionLayer
					.getCell((int) (getX() / tileWidth),
							(int) ((getY() + getHeight()) / tileHeight))
					.getTile().getProperties().containsKey("blocked");

			// middle-left
			if (!collisionX)
				collisionX = collisionLayer
						.getCell((int) (getX() / tileWidth),
								(int) ((getY() + getHeight() / 2) / tileHeight))
						.getTile().getProperties().containsKey("blocked");

			// bottom-left
			if (!collisionX)
				collisionX = collisionLayer
						.getCell((int) (getX() / tileWidth),
								(int) (getY() / tileHeight)).getTile()
						.getProperties().containsKey("blocked");

		} else if (velocity.x < 0) {
			// top-right
			collisionX = collisionLayer
					.getCell((int) ((getX() + tileWidth) / tileWidth),
							(int) ((getY() + getHeight()) / tileHeight))
					.getTile().getProperties().containsKey("blocked");

			// middle-right
			if (!collisionX)
				collisionX = collisionLayer
						.getCell((int) ((getX() + tileWidth) / tileWidth),
								(int) ((getY() + getHeight() / 2) / tileHeight))
						.getTile().getProperties().containsKey("blocked");

			// bottom-right
			if (!collisionX)
				collisionX = collisionLayer
						.getCell((int) ((getX() + tileWidth) / tileWidth),
								(int) (getY() / tileHeight)).getTile()
						.getProperties().containsKey("blocked");
		}

		if (!collisionX)
			// move on x
			setX(getX() + velocity.x * delta);
		else
			velocity.x = 0;

		// move on y
		setY(getY() + velocity.y * delta);

		if (velocity.x > 0) {
			// top-left

		} else if (velocity.x < 0) {
			//

		}
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
}
