package src;
import java.util.*;


public class FocusBoard {

	private Square[][] board = new Square[8][8];
	private String colors = "RB";
	private Random r = new Random();
	//private ArrayList<Character> piecesRemoved;
	private int bPiecesRemoved;
	private int rPiecesRemoved;
	private Character turn;
	
	public FocusBoard(){
		//piecesRemoved = new ArrayList<Character>();
		bPiecesRemoved = 0;
		rPiecesRemoved = 0;
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
	public FocusBoard(Square[][] board, Character turn, int currentTurnPiecesRemoved, int opponentTurnPiecesRemoved){
		this.board = new Square[8][8];
		this.board = this.copyBoard(board);
		this.colors = "RB";
		this.r = new Random();
		//this.piecesRemoved = piecesRemoved;
		this.bPiecesRemoved = currentTurnPiecesRemoved;
		this.rPiecesRemoved = opponentTurnPiecesRemoved;
		this.turn = turn;
	}
	public Square[][] getBoard(){
		return board;
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
	public int getRPiecesRemoved(){
		return rPiecesRemoved;
	}
	public int getBPiecesRemoved(){
		return bPiecesRemoved;
	}
	public boolean gameEnd(){
		if(rPiecesRemoved > 8){
			System.out.println("B won the game");
			return true;
		}
		else if(bPiecesRemoved > 8){
			System.out.println("R won the game");
			return true;
		}
		else if(generateSuccessors('B').isEmpty()){
			System.out.println("Player R won");
			return true;
		}
		else if(generateSuccessors('R').isEmpty()){
			System.out.println("Player B won");
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
	public ArrayList<FocusBoard> generateSuccessors(Character turn) {
		HashSet<FocusBoard> successors = new HashSet<FocusBoard>();
		if(turn == 'B'){
			for(int i = 0;i < 8; i++){
				for(int j = 0; j<8;j++){
					try{
					if(board[i][j].getTopPiece() == turn){
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
		return new ArrayList<FocusBoard>(successors);
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
		return movesPossible;
	}
	//add move which returns new state
	public FocusBoard move(int startX, int startY, int endX, int endY, int numPiecesMove){
		Character newTurn = changeTurn(turn);
		FocusBoard fb = new FocusBoard(board,newTurn, bPiecesRemoved, rPiecesRemoved);	
		LinkedList<Character> pieces = new LinkedList<Character>();
		/*
		for(int i = 0; i < numPiecesMove;i++){
			pieces.add(fb.board[startX][startY].getStack().get(i));
		}*/
		pieces = fb.board[startX][startY].getFirstXPieces(numPiecesMove);
		
		fb.board[endX][endY] = fb.board[endX][endY].addPieces(pieces);
		
		//System.out.println(pieces);
		fb.board[startX][startY] = fb.board[startX][startY].removePieces(numPiecesMove);
		
		
		
		int origBAmount = findColorAmount(this,'B');
		int origRAmount = findColorAmount(this, 'R');
		int fbBAmount = findColorAmount(fb, 'B');
		int fbRAmount = findColorAmount(fb, 'R');
		
		fb.bPiecesRemoved = origBAmount - fbBAmount;
		fb.rPiecesRemoved = origRAmount - fbRAmount;
		
		//fb.piecesRemoved.addAll(fb.board[endX][endY].getRemovedChars());
		//fb.findWhichPiecesRemoved(fb.piecesRemoved);
		//fb.board[endX][endY].clearRemovedChars();
		
		return fb;
	}
	private int findColorAmount(FocusBoard fb, char color){
		int amount = 0;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				try{
				LinkedList<Character> bAmountInStack = fb.board[i][j].getStack();
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
	}/*
	private void findWhichPiecesRemoved(ArrayList<Character> piecesRemoved) {
		bPiecesRemoved.clear();
		rPiecesRemoved.clear();
		for(Character c : piecesRemoved){
			if(c == 'B'){
				bPiecesRemoved.add(c);
			}
			else if(c == 'R'){
				rPiecesRemoved.add(c);
			}
		}
	}*/
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
		return ((fb.bPiecesRemoved == this.bPiecesRemoved) &&(fb.rPiecesRemoved == this.rPiecesRemoved) && (Arrays.deepEquals(board, fb.board) && turn == fb.turn));
	}
	public static void main(String[] args){
		
		FocusBoard fb = new FocusBoard();
		System.out.println(fb);
		/*ArrayList<FocusBoard> successors = fb.generateSuccessors('B');
		for(FocusBoard f : successors){
			System.out.println(f);
		}
		System.out.println(successors.size());
		System.out.println(fb.findColorAmount(fb, 'B'));
		
		System.out.println("HI");
		System.out.println(fb);
		
		ArrayList<Character> pieces = new ArrayList<Character>();
		pieces.add('R');
		pieces.add('B');
		pieces.add('B');
		pieces.add('B');
		pieces.add('B');
		for(int i = 0; i < 20; i++){
			ArrayList<FocusBoard> playerBMoves = fb.generateSuccessors('B');
			fb = playerBMoves.get(i);
			System.out.println(fb);
			System.out.println(fb.getRPiecesRemoved());
			System.out.println(fb.getBPiecesRemoved());
		}*/
		
		
		fb = fb.move(3,3,3,4,1);
		System.out.println(fb);
		fb = fb.move(3,2,3,4,1);
		System.out.println(fb);
		fb = fb.move(3,1,3,4,1);
		System.out.println(fb);
		fb = fb.move(3,5,3,4,1);
		System.out.println(fb);
		fb = fb.move(3,6,3,4,1);
		System.out.println(fb);
		System.out.println("HI");
		System.out.println(fb.getRPiecesRemoved());
		System.out.println(fb.getBPiecesRemoved());
		fb = fb.move(4,4,3,4,1);
		System.out.println(fb);
		System.out.println("BYE");
		System.out.println(fb.getRPiecesRemoved());
		System.out.println(fb.getBPiecesRemoved());
		fb = fb.move(1,3,2,3,1);
		System.out.println("right");
		System.out.println(fb);
		fb = fb.move(2,3,3,4,2);
		System.out.println(fb);
		System.out.println("CRY");
		System.out.println(fb.getRPiecesRemoved());
		System.out.println(fb.getBPiecesRemoved());
		
		
	}
}