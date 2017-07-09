# tennisGame
The program's aim is to be able to make tennis player win points and to print the score, and the game status if requested (0-0, 15-0,.... Deuce advantage)
also the status of the Match (in Progress, player 1 wins, player 2 wins)
These are some examples (that you could find in the JUnit (MatchTest)


Exemple 1
Player  1 :  nom du joueur1
Player  2 : nom du joueur2
Score : (6-1) (7-5) (1-0)
Current game status : 15-30
Match Status : in progress
 
Exemple 2
Player  1 :  nom du joueur1
Player  2 : nom du joueur2
Score : (6-1) (7-5) (0-0)
Current game status : deuce
Match Status : in progress
 
Exemple 3
Player  1 :  nom du joueur1
Player  2 : nom du joueur2
Score : (6-1) (7-5) (0-0)
Current game status : advantage
Match Status : in progress
 
Exemple 4
Player 1 :  nom du joueur1
Player 2 : nom du joueur2
Score : (6-1) (7-5) (6-0)
Match Status : Player1 wins
 
Exemple 5
Player 1 :  nom du joueur1
Player 2 : nom du joueur2
Score : (6-1) (7-5) (2-6) (6-7) (4-6)
Match Status : Player2 wins

a java.lang.IllegalStateException will be thrown by the program if we try to add point in a finished Game, TieBreak, Set or Match. 
and also if we try to add a game to a finished Set.

I wrote this code while applying for a Job

2017-07-09 Fahd Rachid <fahdrachid5@gmail.com>
