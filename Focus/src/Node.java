package src;


public class Node {
	private FocusBoard curState;
	private Node parent;
	
	public Node(FocusBoard s){
		curState = s;
		parent = null;
	}
	public Node(Node prev, FocusBoard s){
		parent = prev;
		curState = s;
	}
	public FocusBoard getCurState() {
		return curState;
	}

	public Node getParent() {
		return parent;
	}
}
