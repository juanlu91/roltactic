package com.heads.rolt;

import com.badlogic.gdx.Game;
import com.heads.rolt.screens.GameWorld;
import com.heads.rolt.screens.MainMenuScreen;
import com.heads.rolt.screens.RankingScreen;
import com.heads.rolt.screens.TeamScreen;
import com.heads.rolt.screens.WaitingScreen;

public class MyGame extends Game {
	
	public MainMenuScreen mainMenuScreen;
	public TeamScreen teamScreen;
	public WaitingScreen waitingScreen;
	public RankingScreen rankingScreen;
	public GameWorld gameWorld;
	
	@Override
	public void create() {
		mainMenuScreen = new MainMenuScreen(this);
		teamScreen = new TeamScreen(this);
		waitingScreen = new WaitingScreen(this);
		rankingScreen = new RankingScreen(this);
		gameWorld = new GameWorld(this);
		setScreen(mainMenuScreen);
	}
	
}