package com.heads.rolt.characters;

public class Character {
	
	private Integer attack;
	private Integer defense;
	private Integer life;
	private Integer move;
	private Integer range;
	
	public Character() {
		this.attack = 0;
		this.defense = 0;
		this.life = 0;
		this.move = 0;
		this.range = 0;
	}
	
	public Character(Integer attack, Integer defense, Integer life, Integer move, Integer range) {
		this.attack = attack;
		this.defense = defense;
		this.life = life;
		this.move = move;
		this.range = range;
	}
	
	public Integer getAttack() {
		return attack;
	}
	
	public Integer getDefense() {
		return defense;
	}
	
	public Integer getLife() {
		return life;
	}
	
	public Integer getMove() {
		return move;
	}
	
	public Integer getRange() {
		return range;
	}
	
	public void setAttack(Integer attack) {
		this.attack = attack;
	}
	
	public void setDefense(Integer defense) {
		this.defense = defense;
	}

	public void setLife(Integer life) {
		this.life = life;
	}
	
	public void setMove(Integer move) {
		this.move = move;
	}
	
	public void setRange(Integer range) {
		this.range = range;
	}
}
