package src;
import java.util.*;


public class FocusBoard {

	private Square[][] board = new Square[8][8];
	private String colors = "RB";
	private Random r = new Random();
	private int piecesRemoved;
	private Character turn;
	
	public FocusBoard(){
		piecesRemoved = 0;
		turn = 'B';
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(i == 0 || i == 7){
					//add upper and lower empty squares
					for(int x = 2; x <= 5; x++){
						board[i][x] = new Square();
					}
				}
				else{
					//add random color squares
					for(int x = 1; x <= 6; x++){
						board[i][x] = new Square(getRandomColor());
					}
					//implement empty squares on left side and right side
					for(int y = 2; y < 6;y++){
						board[y][0] = new Square();
						board[y][7] = new Square();
					}
				}
			}
		}
	}
	public FocusBoard(Square[][] board, int piecesRemoved, Character turn){
		this.board = new Square[8][8];
		/*for (int i = 0; i < 8; i++){ 
			for(int j = 0; j < 8; j++){
				try{
				this.board[i][j] = new Square(board[i][j]);
				}
				catch(Exception e){}
			}
		}*/
		this.board = this.copyBoard(board);
		
		this.colors = "RB";
		this.r = new Random();
		this.piecesRemoved = piecesRemoved;
		this.turn = turn;
	}
	public Square[][] copyBoard(Square[][] fboard){
		Square[][] result = new Square[fboard.length][fboard.length];
		for(int r = 0; r < fboard.length; r++){
			for(int s = 0; s < fboard.length; s++){
				result[r][s] = fboard[r][s];
			}
		}
		
		return result;
	}
	//add generateSuccessors
	//check if goal/game ended
	public boolean gameEnd(){
		if(piecesRemoved == 8){
			return true;
		}
		else if(generateSuccessors().size() == 0){
			return true;
		}
		return false;
	}
	private Character changeTurn(Character t){
		if(t == 'B'){
			return 'R';
		}
		else{
			return 'B';
		}
	}
	public HashSet<FocusBoard> generateSuccessors() {
		HashSet<FocusBoard> successors = new HashSet<FocusBoard>();
		if(turn == 'B'){
			for(int i = 0;i < 8; i++){
				for(int j = 0; j<8;j++){
					try{
					if(board[i][j].getTopPiece() == turn){
						System.out.println(+i+" "+j + " turn:" + turn);
						
						successors.addAll(findMoves(board[i][j],i,j));
					}
					}catch(Exception e){}
					
				}
			}
		}
		
		else if(turn == 'R'){
			for(int i = 0;i < 8; i++){
				for(int j = 0; j<8;j++){
					try{
					if(board[i][j].getTopPiece() == turn)
						successors.addAll(findMoves(board[i][j],i,j));
					}
					catch(Exception e){}
				}
			}
		}
		return successors;
	}
	private Collection<? extends FocusBoard> findMoves(Square square,int x, int y) {
		int squareStackSize = square.size();
		HashSet<FocusBoard> movesPossible = new HashSet<FocusBoard>();
		for(int i = 1; i <=squareStackSize;i++){
			try{
			movesPossible.add(move(x,y,x+i,y,i));
			}catch(Exception e){}
			try{
			movesPossible.add(move(x,y,x-i,y,i));
			}catch(Exception e){}
			try{
			movesPossible.add(move(x,y,x,y+i,i));
			} catch(Exception e){}
			try{
			movesPossible.add(move(x,y,x,y-i,i));
			}catch(Exception e){}
		}
		System.out.println("HI"+movesPossible.size());
		return movesPossible;
	}
	//add move which returns new state
	public FocusBoard move(int startX, int startY, int endX, int endY, int numPiecesMove){
		Character newTurn = changeTurn(turn);
		FocusBoard fb = new FocusBoard(board,piecesRemoved,newTurn);
		
		
		LinkedList<Character> pieces = new LinkedList<Character>();
		Square startSquare = new Square(fb.board[startX][startY]);
		Square endSquare = new Square(fb.board[endX][endY]);
		
		pieces.addAll(startSquare.getPieces(numPiecesMove));
		
		fb.board[startX][startY] = startSquare.removeStartPiece(pieces);
		piecesRemoved += endSquare.addPiece(pieces);
		fb.board[endX][endY] = endSquare;
		
		System.out.println("This is copy:");
		System.out.println(fb);
		
		System.out.println("MY SHIT:");
		System.out.println(this);
		
		
		return fb;
	}
	public char getRandomColor(){
		return colors.charAt(r.nextInt(colors.length()));
	}
	public String toString(){
		String s = "";
		for (int i = 0; i < 8; i++){	
			for(int j = 0; j < 8; j++){
				if(board[i][j] == null){
					int spacesCount = 13;
					while(spacesCount >0){
						s+= " ";
						spacesCount--;
					}
					
				}
				else{
					int spacesCount = 10 - board[i][j].size();
					
					s += "{ " + board[i][j];
					while(spacesCount > board[i][j].size()){
						s+= " ";
						spacesCount--;
					}
					s += "}";
				}
			}
			s+= "\n";
			
		}
		return s;
	}
	@Override
	public boolean equals(Object o) {
		FocusBoard fb = (FocusBoard) o;
		return ((piecesRemoved == fb.piecesRemoved) && (Arrays.deepEquals(board, fb.board) && turn == fb.turn));
	}
	public static void main(String[] args){
		
		FocusBoard fb = new FocusBoard();
		System.out.println(fb);
		
		System.out.println(fb.generateSuccessors().size());
		/*
		
		ArrayList<Character> pieces = new ArrayList<Character>();
		pieces.add('R');
		pieces.add('B');
		
		fb.move(1,1,1,2,1);
		System.out.println(fb.boardToString());
		
		fb.move(1,2,1,3,2);
		System.out.println(fb.boardToString());
		
		fb.move(2,1,2,2,1);
		System.out.println(fb.boardToString());
		
		fb.move(2,2,2,3,2);
		System.out.println(fb.boardToString());
		
		fb.move(1,3,2,3,3);
		System.out.println(fb.boardToString());
		System.out.println(fb.piecesRemoved);*/
	}
}