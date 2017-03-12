package src;

import java.util.*;

public class GamePlay {
	private FocusBoard boardToPlay;
	
	private Character playerB;
	private Character playerR;
	private int origBAmount;
	private int origRAmount;
	private int newBAmount;
	private int newRAmount;
	
	public GamePlay(){
		boardToPlay = new FocusBoard();
		origBAmount = findColorAmount(boardToPlay,'B');
		newBAmount = origBAmount;
		origRAmount = findColorAmount(boardToPlay,'R');
		newRAmount = origRAmount;
		playerB = 'B';
		playerR = 'R';
	}
	public boolean gameEnds(){
		if((boardToPlay.generateSuccessors('B').isEmpty()) || ((origBAmount - newBAmount) == 5)){
			System.out.println("Player R won");
			System.out.println("WINBold"+origBAmount);
			System.out.println("WINBnew"+newBAmount);
			return true;
		}
		else if(boardToPlay.generateSuccessors('R').isEmpty() || ((origRAmount - newRAmount) == 5)){
			System.out.println("Player B won");
			System.out.println("WINRold"+origRAmount);
			System.out.println("WINRnew"+newRAmount);
			return true;
		}
		return false;
	}
	public int findColorAmount(FocusBoard fb, char color){
		int amount = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				try{
				LinkedList<Character> bAmountInStack = fb.getBoard()[i][j].getStack();
					for(int x = 0; x < bAmountInStack.size();x++){
						if(bAmountInStack.get(x).charValue() == color){
							amount++;
						}
					}
				}
				catch(Exception e){}
			}
		}
		return amount;
	}
	public void playGame() {
		int i = 100;
		
		while(!gameEnds()){
			System.out.println("The board:");
			System.out.println(boardToPlay);
			System.out.println("FINAL"+origBAmount);
			System.out.println("FINAL"+origRAmount);
			
			ArrayList<FocusBoard> playerBMoves = boardToPlay.generateSuccessors(playerB);
			boardToPlay = playerBMoves.get(0);
			System.out.println("After playerB move:");
			System.out.println(boardToPlay);
			int prevRAmount = newRAmount;
			newRAmount = findColorAmount(boardToPlay,'R');
			if(newRAmount <=0){
				newRAmount = prevRAmount;
			}
			System.out.println("B has captured:" + (origRAmount - newRAmount) + " R colors");
			
			ArrayList<FocusBoard> playerRMoves = boardToPlay.generateSuccessors(playerR);
			boardToPlay = playerRMoves.get(0);
			System.out.println("After playerR move:");
			System.out.println(boardToPlay);
			int prevBAmount = newBAmount;
			newBAmount = findColorAmount(boardToPlay,'B');
			if(newBAmount <=0){
				newBAmount = prevBAmount;
			}
			System.out.println("R has captured:" + (origBAmount - newBAmount) + " B colors");
			i--;
		}
		/*
		while(!gameEnds()){
		
			System.out.println("The board:");
			System.out.println(boardToPlay);
			
			ArrayList<FocusBoard> playerBMoves = boardToPlay.generateSuccessors(playerB);
			boardToPlay = playerBMoves.get(0);
			System.out.println("After playerB move:");
			System.out.println(boardToPlay);
			int prevBAmount = newBAmount;
			newBAmount = findColorAmount(boardToPlay,'R');
			if(newBAmount <=0){
				newBAmount = prevBAmount;
			}
			System.out.println("B has captured:" + (origRAmount - newRAmount) + " R colors");
			
			ArrayList<FocusBoard> playerRMoves = boardToPlay.generateSuccessors(playerR);
			boardToPlay = playerRMoves.get(0);
			System.out.println("After playerR move:");
			System.out.println(boardToPlay);
			int prevRAmount = newRAmount;
			newRAmount = findColorAmount(boardToPlay,'R');
			if(newRAmount <=0){
				newRAmount = prevRAmount;
			}
			System.out.println("R has captured:" + (origRAmount - newRAmount) + " B colors");
			System.out.println("HIB"+origBAmount);
			System.out.println("HIR"+origRAmount);
		}
		*/
	}
	public static void main(String[] args){
		GamePlay gp = new GamePlay();
		gp.playGame();
	}
}
