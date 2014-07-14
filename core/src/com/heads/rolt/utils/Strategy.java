package com.heads.rolt.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public enum Strategy {

	STRAT_3_1, STRAT_2_2, STRAT_1_3, STRAT_1_1_2;

	public static CircularArrayList<Strategy> STRATEGIES = new CircularArrayList<Strategy>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(STRAT_3_1);
			add(STRAT_2_2);
			add(STRAT_1_3);
			add(STRAT_1_1_2);
		}
	};

	public static Vector2 getPositionByStrategy(int index, Strategy s,
			Float width, Float height) {
		Vector2 pos = new Vector2();

		switch (s) {
		case STRAT_3_1:
			switch (index) {
			case 0:
				pos.x = Gdx.graphics.getWidth() / 2 - width - 5;
				pos.y = Gdx.graphics.getHeight() / 2 - height - height / 2 - 5;
				break;
			case 1:
				pos.x = Gdx.graphics.getWidth() / 2 - width - 5;
				pos.y = Gdx.graphics.getHeight() / 2 - height / 2;
				break;
			case 2:
				pos.x = Gdx.graphics.getWidth() / 2 - width - 5;
				pos.y = Gdx.graphics.getHeight() / 2 + height / 2 + 5;
				break;
			case 3:
				pos.x = Gdx.graphics.getWidth() / 2 + 5;
				pos.y = Gdx.graphics.getHeight() / 2 - height / 2;
				break;
			}
		}

		return pos;
	}
}
