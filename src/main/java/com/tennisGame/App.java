package com.tennisGame;


public class App {
	private static final Integer[] MATCH_ONE = {10,1,10,5,4,8,4,8,4,5,10,1,10,0,4,1,1,1};

	public static void main( String[] args ){

		Player player1 = new Player("nom du joueur1");
		Player player2 = new Player("nom du joueur2");
		Match match = new Match(player1, player2);
		TennisUtils.processMatch(match,MATCH_ONE);
		System.out.println(match.toString());
    }
}
