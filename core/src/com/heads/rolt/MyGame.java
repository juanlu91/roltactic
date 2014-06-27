package com.heads.rolt;

import com.badlogic.gdx.Game;
import com.heads.rolt.screens.MainMenuScreen;
import com.heads.rolt.screens.TeamScreen;
import com.heads.rolt.screens.WaitingScreen;

public class MyGame extends Game {
	
	public MainMenuScreen mainMenuScreen;
	public TeamScreen teamScreen;
	public WaitingScreen waitingScreen;
	
	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		teamScreen = new TeamScreen(this);
		waitingScreen = new WaitingScreen(this);
		setScreen(mainMenuScreen);
	}
	
}