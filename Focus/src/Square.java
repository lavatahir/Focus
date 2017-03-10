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
		copySquare.stack.addAll(0, pieces);
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
		copySquare.stack.removeAll(pieces);
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
		
		Square sq2 = new Square('R');
		
		ArrayList<Character> pieces = new ArrayList<Character>();
		pieces.add('R');
		pieces.add('R');
		pieces.add('B');
		pieces.add('B');
		
		SquareRemoval sqr = sq1.addPiece(pieces);
		sq1 = sqr.getSquare();
		SquareRemoval sqr2 = sq2.addPiece(sq1.stack);
		sq2 = sqr2.getSquare();
		
		System.out.println(sq1);
		System.out.println(sq2);
		System.out.println(sqr2.getSquaresRemoved());
	}
	
}