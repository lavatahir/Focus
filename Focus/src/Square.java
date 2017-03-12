package src;
import java.util.*;

public class Square {

	private LinkedList<Character> stack;
	private ArrayList<Character> removedChars;
	
	public Square(){
		stack = new LinkedList<Character>();
		removedChars = new ArrayList<Character>();
	}
	public Square(Square toClone){
		this.stack = new LinkedList<Character>();
		this.stack.addAll(toClone.stack);
		this.removedChars = new ArrayList<Character>();
		this.removedChars.addAll(toClone.removedChars);
	}
	public Square(Character c){
		stack = new LinkedList<Character>();
		stack.add(c);
		this.removedChars = new ArrayList<Character>();
	}
	
	public Square addPieces(List<Character> pieces){
		Square copySquare = new Square(this);
		copySquare.stack.addAll(0,pieces);
		if(copySquare.stack.size() > 5){
			for(int i = copySquare.stack.size(); i>5; i--){
				copySquare.removedChars.add(copySquare.stack.get(i-1));
				copySquare.stack.remove(i-1);	
			}
		}
		return copySquare;
	}
	public Square removePieces(List<Character> pieces){
		Square copySquare = new Square(this);
		
		for(Character c :pieces){
			copySquare.stack.removeFirstOccurrence(c);
		}
		//removes all instances of pieces elements in stack
		//copySquare.stack.removeAll(pieces);
		return copySquare;
	}
	public Square removePieces(int numPieces){
		Square copySquare = new Square(this);
		//System.out.println(copySquare.stack);
		for(int i = 0; i < numPieces;i++){
			//copySquare.stack.remove(i);
			copySquare.stack.poll();
		}
		return copySquare;
	}
	public void clearRemovedChars(){
		removedChars.clear();
	}
	public List<Character> removeNumPieces(int numPiecesToRemove){
		LinkedList<Character> piecesToRemove = new LinkedList<Character>();
		for(int i = 0; i < numPiecesToRemove; i++){
			piecesToRemove.add(stack.remove(i));
		}
		return piecesToRemove;
	}
	public int size(){
		return stack.size();
	}
	public LinkedList<Character> getFirstXPieces(int x){
		LinkedList<Character> firstXPieces = new LinkedList<Character>();
		for(int i = 0 ; i < x; i++){
			firstXPieces.add(stack.get(i));
		}
		return firstXPieces;
	}
	public Character getTopPiece(){
		return stack.get(0);
	}
	public LinkedList<Character> getStack(){
		return stack;
	}
	public ArrayList<Character> getRemovedChars(){
		return removedChars;
	}
	public LinkedList<Character> getPieces(int numPiecesMove){
		LinkedList<Character> pieces = new LinkedList<Character>();
		for(int i = 0; i < numPiecesMove; i++){
			pieces.add(stack.get(i));
		}
		return pieces;
	}
	public String toString(){
		String s = "";
		for(int i = 0; i < stack.size(); i++){
			s+= stack.get(i) + " ";
		}
		return s;
	}
	
	public static void main(String[] args){
		
		Square sq = new Square('B');
		ArrayList<Character> pieces = new ArrayList<Character>();
		pieces.add('R');
		pieces.add('B');
		pieces.add('R');
		sq = sq.addPieces(pieces);
		System.out.println(sq.stack);
		
		Square sq2 = new Square();
		System.out.println(sq2.stack);
		LinkedList<Character> toMove = sq.getFirstXPieces(2);
		System.out.println("yo");
		System.out.println(toMove);
		sq2 = sq2.addPieces(toMove);
		sq = sq.removePieces(2);
		
		System.out.println(sq.stack);
		System.out.println(sq2.stack);
		
		
	}
	
}