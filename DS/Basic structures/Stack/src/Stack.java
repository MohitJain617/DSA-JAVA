public class Stack<T> {
	private int size;
	Node<T> head;
	public Stack(){
		head = null;
		size = 0;
	}
	//internal nodes 
	private class Node<T>{
		T data;
		Node<T> next;
		public Node(T val, Node<T> next){
			this.data = val;
			this.next = next;
		}
	}
	public boolean isEmpty(){
		return (size == 0);
	}
	public int size(){
		return size;
	}
	public void clear(){
		//relying on the garbage collector here 
		head = null; size = 0;
	}
	public void push(T val){
		Node<T> x = new Node<T>(val,head);
		head = x;
		size++;
	}
	public T pop(){
		if(isEmpty()){return null;}
		T temp = head.data;
		head = head.next;
		size--;
		return temp;
	}
	public T peek(){
		if(!(isEmpty())) return head.data;
		return null;
	}
	//returns element position relative to head
	public int search(T val){
		Node<T> temp = head;
		int j=0;
		while(temp != null){
			if(temp.data.equals(val)) return j;
			temp = temp.next;
			j++;
		}
		return -1;
	}
}
