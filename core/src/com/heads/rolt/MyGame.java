package com.heads.rolt;

import com.badlogic.gdx.Game;
import com.heads.rolt.screens.MainMenuScreen;
import com.heads.rolt.screens.TeamScreen;

public class MyGame extends Game {
	
	public MainMenuScreen mainMenuScreen;
	public TeamScreen teamScreen;
	
	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		teamScreen = new TeamScreen(this);
		setScreen(mainMenuScreen);
	}
	
}