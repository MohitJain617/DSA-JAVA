public class CircularLL {
	Node rear;
	int size;
	private class Node{
		int val;
		Node next;
		public Node(int data, Node next){
			this.val = data;
			this.next = next;
		}
	}
	public CircularLL(){
		rear = null;
		size = 0;
	}
	public int length(){
		return size;
	}
	public boolean isEmpty(){
		return (size == 0);
	}

	public void add(int data){
		addFirst(data);
	}
	public void addFirst(int data){
		Node temp = new Node(data,null);
		if(rear == null){
			rear = temp;
			rear.next = rear;
			size++;
		} else {
			temp.next = rear.next;
			rear.next = temp;
			size++;
		}
	}
	public void addLast(int data){
		Node temp = new Node(data,null);
		if(rear == null){
			rear = temp;
			rear.next = rear;
			size++;
		} else {
			temp.next = rear.next;
			rear.next = temp;
			rear=rear.next;
			size++;
		}
	}
	public void insertAt(int pos, int data){
		if(pos == 0) addFirst();
		else if(pos == (size-1)) addLast();
		else{
			Node temp = rear.next;
			for(int i = 1; i < pos; i++){
				temp = temp.next;
			}
			Node node = new Node(data,temp.next);
			temp.next = node;
			size++;
		} 
	}

	public int deleteFirst(){}
	public int deleteLast(){}
	public int deleteAt(int pos){}
}
