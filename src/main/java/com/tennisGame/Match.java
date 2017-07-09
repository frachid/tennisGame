package com.tennisGame;

import java.util.ArrayList;
import java.util.List;

public class Match {

	private static final String PLAYER_ONE = "Player 1 : ";
	private static final String PLAYER_TWO = "Player 2 : ";
	private static final String SAUT_A_LA_LIGNE = "\n";
	private static final String SCORE = "Score : ";
	private static final String MATCH_STATUS = "Match Status : ";
	private static final String IN_PROGRESS = "in progress";
	private static final String PLAYER_ONE_WINS = "Player1 wins";
	private static final String PLAYER_TWO_WINS = "Player2 wins";
	private static final String SPACE = " ";
	private static final String MATCH_FINISHED = " already won the match";
	private Player player1;
	private Player player2;
	private Player winner;
	private Integer setScorePlayer1;
	private Integer setScorePlayer2;
	private List<Set> listSets;

	//-----------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------------Constructor----------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------------------------------//

	public Match(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		setScorePlayer1 = 0;
		setScorePlayer2 = 0;
		this.listSets = new ArrayList<Set>();
		listSets.add(new Set(player1, player2));
		
	}

	//-----------------------------------------------------------------------------------------------------------------------//
	//----------------------------------------------Getters and Setters------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------------------------------//

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public List<Set> getListSets() {
		return listSets;
	}

	public void setListSets(List<Set> listSets) {
		this.listSets = listSets;
	}

	/**
	 * Add one score point to the player
	 * @param {@link Player} : the player which is winning the point
	 */
	public void pointTo(Player player) {
		assertMatchIsOnGoing();
		getActualSet().pointTo(player);
		checkActualSet();
	}
	
	/**
	 * Called when trying to add a point in a {@link Match} to one of the 2 players
	 * @throws IllegalStateException: in case the game is already won by one of the players
	 */
	private void assertMatchIsOnGoing() throws IllegalStateException{
		if(winner!=null){
			throw new IllegalStateException(winner.getName()+MATCH_FINISHED);
		}
	}
	
	/**
	 * @return The Last {@link Set} in the listSets which corresponds to the actual Set played
	 */
	private Set getActualSet() {
		return listSets.get(listSets.size()-1);
	}

	/**
	 * called after when adding a point to a {@link Player}
	 * update Set scores and check if the {@link Match} is over in case the actual {@link Set} is over
	 */
	private void checkActualSet() {
		if(getActualSet().getWinner()!=null){
			updateSetScores(getActualSet());
			checkMatchWinner();
			
		}
		
		
	}
	
	/**
	 * Called in case the actual {@link Set} is over
	 * Set the {@link Match} winner if the {@link Match} is finished
	 * adding a new {@link Set} if not.
	 */
	private void checkMatchWinner() {
		Player matchWinner = calculateMatchWinner();
		if(matchWinner!=null){
			setWinner(matchWinner);
		} else{
			this.listSets.add(new Set(player1, player2));
		}
		
	}

	/**
	 * @return the {@link Player} who won the {@link Match}. null if the {@link Match} is not won yet
	 */
	private Player calculateMatchWinner() {
		Player matchWinner = null;
		if(setScorePlayer1 >=TennisRule.MINIMUM_SETS_TO_WIN_MATCH){
			matchWinner = player1;
		} else if(setScorePlayer2 >=TennisRule.MINIMUM_SETS_TO_WIN_MATCH){
			matchWinner = player2;
		}
		return matchWinner;
	}


	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		toReturn.append(PLAYER_ONE);
		toReturn.append(player1.getName());
		toReturn.append(SAUT_A_LA_LIGNE);
		toReturn.append(PLAYER_TWO);
		toReturn.append(player2.getName());
		toReturn.append(SAUT_A_LA_LIGNE);
		toReturn.append(SCORE);
		for(Set set : listSets){
			toReturn.append(set.toString());
			toReturn.append(SPACE);
		}
		toReturn.append(SAUT_A_LA_LIGNE);
		if(winner == null){
			toReturn.append(getActualSet().getActualGame().toString());
			toReturn.append(SAUT_A_LA_LIGNE);
			toReturn.append(MATCH_STATUS);
			toReturn.append(IN_PROGRESS);
		} else if(winner.equals(player1)){
			toReturn.append(MATCH_STATUS);
			toReturn.append(PLAYER_ONE_WINS);
		} else{
			toReturn.append(MATCH_STATUS);
			toReturn.append(PLAYER_TWO_WINS);
		}
		
		
		return toReturn.toString();
	}

	/**
	 * depends on the winner, increments setScorePlayer1 or setScorePlayer2
	 * @param set
	 */
	private void updateSetScores(Set set) {
		if(player1.equals(set.getWinner())){
			setScorePlayer1++;
		} else if(player2.equals(set.getWinner())){
			setScorePlayer2++;
		}
	}
	
}
