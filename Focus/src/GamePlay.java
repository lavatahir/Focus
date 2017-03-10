package src;

import java.util.*;

public class GamePlay {
	private FocusBoard boardToPlay;
	
	private Character player1;
	private Character player2;

	
	public GamePlay(){
		boardToPlay = new FocusBoard();
		player1 = 'B';
		player2 = 'R';
		
	}


	public void playGame() {
		int i = 30;
		while( i > 0){
			System.out.println("The board:");
			System.out.println(boardToPlay);
			ArrayList<FocusBoard> player1Moves = boardToPlay.generateSuccessors(player1);
			if(player1Moves.isEmpty()){
				System.out.println("Player " + player2 + " wins");
				break;
			}
			else{
				boardToPlay = player1Moves.get(0);
				System.out.println("After player1 move:");
				System.out.println(boardToPlay);
				System.out.println("Captured for player1:"+boardToPlay.getOpponentRemoved());
			}
			ArrayList<FocusBoard> player2Moves = boardToPlay.generateSuccessors(player2);
			if(player2Moves.isEmpty()){
				System.out.println("Player " + player1 + " wins");
				break;
			}
			else{
				boardToPlay = player2Moves.get(0);
				System.out.println("After player2 move:");
				System.out.println(boardToPlay);
				System.out.println("Captured for player2:"+boardToPlay.getOpponentRemoved());
			}
			i--;
		}
	}
	public static void main(String[] args){
		GamePlay gp = new GamePlay();
		gp.playGame();
	}
}
