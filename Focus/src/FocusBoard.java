import java.util.*;

public class FocusBoard {

	private Square[][] board = new Square[8][8];
	private String colors = "RB";
	private Random r = new Random();
	private int piecesRemoved;
	
	public FocusBoard(){
		piecesRemoved = 0;
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
	public FocusBoard(Square[][] board, int piecesRemoved){
		this.board = board;
		this.colors = "RB";
		this.r = new Random();
		this.piecesRemoved = piecesRemoved;
	}
	//add generateSuccessors
	//add goalState
	//check if goal/game ended
	public boolean gameEnd(){
		if(piecesRemoved == 8){
			return true;
		}
		else if(generateSuccessors('B').size() == 0){
			return true;
		}
		return false;
	}
	private HashSet<FocusBoard> generateSuccessors(char c) {
		// TODO Auto-generated method stub
		return null;
	}
	//add move which returns new state
	public FocusBoard move(int startX, int startY, int endX, int endY, int numPiecesMove){
		FocusBoard fb = new FocusBoard(board,piecesRemoved);
		LinkedList<Character> pieces = fb.board[startX][startY].getPieces(numPiecesMove);
		piecesRemoved+=fb.board[endX][endY].addPiece(pieces);
		fb.board[startX][startY].removeStartPiece(pieces);
		return fb;
	}
	public char getRandomColor(){
		return colors.charAt(r.nextInt(colors.length()));
	}
	public String boardToString(){
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
	public static void main(String[] args){
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.pop();
		System.out.println(stack);
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		System.out.println(queue);
		
		FocusBoard fb = new FocusBoard();
		System.out.println(fb.boardToString());
		
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
		System.out.println(fb.piecesRemoved);
	}
}