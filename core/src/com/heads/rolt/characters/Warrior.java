package com.heads.rolt.characters;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Warrior extends Character {
	
	private Sprite image;

	
	public Warrior(Integer attack, Integer defense, Integer life, Integer move, Integer range, Sprite image) {
		super(attack, defense, life, move, range);
		this.image = image;
	}	
	
	public Sprite getImage() {
		return this.image;
	}
	
	public void setImage(Sprite image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Warrior [image=" + image + ", Attack=" + getAttack()
				+ ", Defense=" + getDefense() + ", Life=" + getLife()
				+ ", Move=" + getMove() + ", Range=" + getRange()
				+ "]";
	}
}
