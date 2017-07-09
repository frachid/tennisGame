package com.tennisGame;

import java.util.HashMap;

public class TennisRule {

	public static final Integer TENNIS_DIFFERENCE = 2;
	public static final Integer MINIMUM_TO_WIN_GAME = 4;
	public static final Integer MINIMUM_TO_WIN_TIE_BREAK = 7;
	public static final Integer MINIMUM_GAMES_TO_WIN_SET = 6;
	public static final Integer MINIMUM_SETS_TO_WIN_MATCH = 3;
	protected static final Integer ZERO = 0;
	protected static final Integer ONE = 1;
	protected static final Integer TWO = 2;
	protected static final Integer THREE = 3;
	protected static final Integer FIFTEEN = 15;
	protected static final Integer THIRTY = 30;
	protected static final Integer FORTY = 40;
	protected static final HashMap<Integer, Integer> tennisScore() {
        return new HashMap<Integer, Integer>() {
            
			private static final long serialVersionUID = 1L;

			{
                put(ZERO, ZERO);
                put(ONE, FIFTEEN);
                put(TWO, THIRTY);
                put(THREE, FORTY);
            }
        };

	}
}
