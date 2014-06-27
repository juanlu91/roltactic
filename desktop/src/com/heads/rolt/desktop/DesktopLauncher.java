package com.heads.rolt.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.heads.rolt.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "rol-tactics";
        cfg.width = 800;
        cfg.height = 480;
        
		new LwjglApplication(new MyGame(), cfg);
	}
}
