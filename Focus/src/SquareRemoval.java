package src;

import java.util.*;

public class SquareRemoval {
	private Square square;
	private ArrayList<Character> squaresRemoved;
	
	public SquareRemoval(Square square, ArrayList<Character> squaresRemoved){
		this.square = square;
		this.squaresRemoved = new ArrayList<Character>(squaresRemoved);
	}
	public SquareRemoval getSquareRemoval(){
		return this;
	}
	public Square getSquare() {
		return square;
	}
	public void setSquare(Square square) {
		this.square = square;
	}
	public ArrayList<Character> getSquaresRemoved() {
		return squaresRemoved;
	}
	public void setSquaresRemoved(ArrayList<Character> squaresRemoved) {
		this.squaresRemoved = squaresRemoved;
	}

}
