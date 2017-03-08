import java.util.*;

public class Square {

	private LinkedList<Character> stack;
	
	public Square(){
		stack = new LinkedList<Character>();
	}
	public Square(Character c){
		stack = new LinkedList<Character>();
		stack.add(c);
	}
	public int addPiece(List<Character> pieces){
		int piecesRemoved = 0;
		stack.addAll(0, pieces);
		if(stack.size() >5){
			for(int i = stack.size();i>5;i--){
				stack.remove(i-1);
				piecesRemoved++;
			}
		}
		return piecesRemoved;
	}
	public void removeStartPiece(List<Character> pieces){
		if(pieces.size() > 1){
			stack.removeAll(pieces);
		}
		else{
			stack.remove(pieces.get(0));
		}
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
}