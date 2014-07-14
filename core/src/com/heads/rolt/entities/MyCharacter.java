package com.heads.rolt.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class MyCharacter extends Sprite {
	
	private TiledMapTileLayer collisionLayer;
	
	private Sprite sprite;
	
	private Race race;
	private Integer attack;
	private Integer defense;
	private Integer health;
	private Integer movement;
	private Integer range;
	
	public MyCharacter(Race race, Sprite sprite, Integer attack, Integer defense, Integer health, Integer movement, Integer range) {
		super(sprite);
		this.race = race;
		this.sprite = sprite;
		this.attack = attack;
		this.defense = defense;
		this.health = health;
		this.movement = movement;
		this.range = range;
	}
	
	@Override
	public void draw(Batch batch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(batch);
	}
	
	public void update(float delta) {
		
	}
	
	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public Race getRace() {
		return race;
	}

	public void setRace(Race race) {
		this.race = race;
	}

	public Integer getAttack() {
		return attack;
	}
	
	public void setAttack(Integer attack) {
		this.attack = attack;
	}
	
	public Integer getDefense() {
		return defense;
	}

	public void setDefense(Integer defense) {
		this.defense = defense;
	}
	
	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}
	
	public Integer getMovement() {
		return movement;
	}
	
	public void setMovement(Integer move) {
		this.movement = move;
	}
	
	public Integer getRange() {
		return range;
	}
	
	public void setRange(Integer range) {
		this.range = range;
	}
	
	public TiledMapTileLayer getCollisionLayer() {
		return collisionLayer;
	}

	public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
		this.collisionLayer = collisionLayer;
	}
}
