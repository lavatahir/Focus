package src;
import java.util.*;

public class Square {

	private LinkedList<Character> stack;
	
	public Square(){
		stack = new LinkedList<Character>();
	}
	public Square(Square toClone){
		this.stack = new LinkedList<Character>();
		this.stack.addAll(toClone.stack);
	}
	public Square(Character c){
		stack = new LinkedList<Character>();
		stack.add(c);
	}
	public SquareRemoval addPiece(List<Character> pieces){
		
		Square copySquare = new Square(this);
		ArrayList<Character> piecesRemoved = new ArrayList<Character>();
		copySquare.stack.addAll(0,pieces);
		if(copySquare.stack.size() >5){
			for(int i = copySquare.stack.size(); i>5; i--){
				piecesRemoved.add(copySquare.stack.get(i-1));
				copySquare.stack.remove(i-1);	
			}
		}
		return new SquareRemoval(copySquare, piecesRemoved);
	}
	public Square removeStartPiece(List<Character> pieces){
		Square copySquare = new Square(this);
		
		for(Character c :pieces){
			copySquare.stack.removeFirstOccurrence(c);
		}
		//removes all instances of pieces elements in stack
		//copySquare.stack.removeAll(pieces);
		return copySquare;
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
	public Character getTopPiece(){
		return stack.get(0);
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
		Square sq1 = new Square('B');
		
		Square sq2 = new Square();
		
		SquareRemoval sqr = sq2.addPiece(sq1.stack);
		sq2 = sqr.getSquare();
		
		
		System.out.println(sq1);
		System.out.println(sq2);
		System.out.println(sqr.getSquaresRemoved());
	}
	
}