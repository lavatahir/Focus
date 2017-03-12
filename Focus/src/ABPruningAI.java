package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class ABPruningAI {
	private int uptoDepth;
	private Node bestMove;
	private int origBAmount;
	private int origRAmount;
	private Character color;
	
	public ABPruningAI(int uptoDepth, int bAmount, int rAmount, Character color){
		this.uptoDepth = uptoDepth;
		bestMove = null;
		this.origBAmount = bAmount;
		this.origRAmount = rAmount;
		this.color = color;
	}
	public int alphaBetaMiniMax(Node node, int alpha, int beta, int depth, Character color){
		
		if(depth == uptoDepth || node.getCurState().gameEnd()){
			return evaluateHeuristic(node, color);
		}
		
		//Production System
		ArrayList<Node> expandedNodes = generateChildren(node, color);
		
		if (expandedNodes.isEmpty()){
			return evaluateHeuristic(node, color);
		}
		
		int currentScore;
		
		if(color == 'B'){
			for (Node newNode: expandedNodes){
				currentScore = alphaBetaMiniMax(newNode, alpha, beta, depth+1, 'R');
				
				if(currentScore > alpha){
					alpha = currentScore;
					if(depth == 1){
						//Sets the best possible next move
						bestMove = newNode;
					}
				}
				
				if(alpha >= beta){
					return alpha;
				}
			}
			return alpha;
		} else{
			for (Node newNode: expandedNodes){
				currentScore = alphaBetaMiniMax(newNode, alpha, beta, depth+1, 'B');
				
				if(currentScore < beta){
					beta = currentScore;
					if(depth == 1){
						//Sets the best possible next move
						bestMove = newNode;
					}
				}
				
				if(beta <= alpha){
					return beta;
				}
			}
			return beta;
		}
	}
	public int evaluateHeuristic(Node node, Character player){
		if(player=='B'){
			return playerBHeuristic(node);
		}
		
		else{
			return playerRHeuristic(node);
		}
		
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
	private int playerBHeuristic(Node node) {
		int fbRAmount = findColorAmount(node.getCurState(),'R');
		return origRAmount - fbRAmount;	
	}

	private int playerRHeuristic(Node node) {		
		int min = 0;
		int max = 0;
		for(int i=0; i<8; i++){
			for(int j=0; j<node.getCurState().getBoard()[i].length; j++){
				if(node.getCurState().getBoard()[i][j]!=null){
					max++;
					if(node.getCurState().getBoard()[i][j].size()>=1){
						min++;
					}
					}
			}
		}	
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
		
	public ArrayList<Node> generateChildren(Node node, Character color)
	{
		ArrayList<Node> children = new ArrayList<>();
		ArrayList<FocusBoard> successors = node.getCurState().generateSuccessors(color);
		for(FocusBoard fb : successors){
			children.add(new Node(node, fb));
		}
		//children.addAll(node.getCurState().generateSuccessors(color));
		return children;
		
	}
	public Node getBestMove(){
		return bestMove;
	}
}
