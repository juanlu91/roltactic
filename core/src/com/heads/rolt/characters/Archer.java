package com.heads.rolt.characters;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Archer extends Character {
	
	private Sprite image;
	
	public Archer(Integer attack, Integer defense, Integer life, Integer move, Integer range, Sprite image) {
		super(attack, defense, life, move, range);
		this.image = image;
	}

	public Sprite getImage() {
		return this.image;
	}
	
	public void setImage(Sprite image) {
		this.image = image;
	}
	
	public String toString() {
		return "Archer [image=" + image + ", Attack=" + getAttack()
				+ ", Defense=" + getDefense() + ", Life=" + getLife()
				+ ", Move=" + getMove() + ", Range=" + getRange()
				+ "]";
	}
}
