package com.tennisGame;

public abstract class AbstractGame {

	protected static final String CURRENT_TIE_BREAK_STATUS = "Current tie break status : ";
	protected static final String CURRENT_GAME_STATUS = "Current game status : ";
	private static final String GAME_FINISHED = " already won the game";
	
	protected Player player1;
	protected Player player2;
	protected Player winner;
	protected Integer pointScorePlayer1;
	protected Integer pointScorePlayer2;

	//-----------------------------------------------------------------------------------------------------------------------//
	//--------------------------------------------------Constructor----------------------------------------------------------//
	//-----------------------------------------------------------------------------------------------------------------------//
	protected AbstractGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		pointScorePlayer1 = 0;
		pointScorePlayer2 = 0;
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
	public void setWinner(Player player) {
		this.winner = player;
	}
	public Player getWinner() {
		return winner;
	}
	
	
	/**
	 * Add one score point to the player
	 * @param {@link Player} : the player which is winning the point
	 */
	public void pointTo(Player player) {
		assertGameIsOnGoing();
		if(player.equals(player1)){
			pointToPlayer1();
		} else{
			pointToPlayer2();
		}
		checkWinner();
	}
	
	/**
	 * Called when trying to add a point in an {@link AbstractGame} to one of the 2 players
	 * @throws IllegalStateException: in case the {@link AbstractGame} is already won by one of the players
	 */
	private void assertGameIsOnGoing() throws IllegalStateException{
		if(winner!=null){
			throw new IllegalStateException(winner.getName()+GAME_FINISHED);
		}
	}
	
	/**
	 * Increments pointScorePlayer1
	 */
	public void pointToPlayer1() {
		pointScorePlayer1++;
	}
	
	/**
	 * Increments pointScorePlayer2
	 */
	public void pointToPlayer2() {
		pointScorePlayer2++;
	}
	
	/**
	 * Set the {@link AbstractGame} winner if the {@link AbstractGame} is finished
	 */
	public void checkWinner(){
		Player abstractGameWinner = calculateGameWinner();
		if(abstractGameWinner!=null){
			setWinner(abstractGameWinner);
		}
	}

	/**
	 * @return the {@link Player} who won the {@link AbstractGame}. null if the {@link AbstractGame} is not won yet
	 */
	protected abstract Player calculateGameWinner();


	
	
}
