import java.util.*;

@SuppressWarnings("Unchecked")
public class BinaryHeap<T extends Comparable<T>> {

	T[] heap;
	int capacity;
	int size;

	public BinaryHeap(Class<T> clazz,int capacity){
		this.heap = (T[]) java.lang.reflect.Array.newInstance(clazz, capacity);
		this.size = 0;
		this.capacity = capacity;
	}

	//checking a >= b
	private boolean compare(T a, T b){
		return a.compareTo(b) >= 0;
	}

	public int size(){ return this.size;}
	public boolean isEmpty(){ return this.size == 0;}

	private int parent(int i){return (i-1)/2;}
	private int lchild(int i){return (2*i + 1);}
	private int rchild(int i){return (2*i + 2);}

	private void bubbleUp(int i){
		if((i <= 0) || (i >= this.size)) return;
		int p = parent(i);
		if(compare(heap[p],heap[i]) == false){
			//swap
			T temp = heap[p];
			heap[p] = heap[i];
			heap[i] = temp;
			bubbleUp(p);
		}
		return;
	}
	private void bubbleDown(int i){
		if(i >= this.size) return;
		int lc = lchild(i);
		int rc = rchild(i);
		if(lc >= this.size) return;

		T lvalue = heap[lc];
		T rvalue;
		if(rc >= this.size) rvalue = null;
		else rvalue = heap[rc];

		if((rvalue == null) || (compare(lvalue,rvalue) == true)){
			if(compare(heap[i],lvalue) == false){
			//swap towards left
			T temp = heap[lc];
			heap[lc] = heap[i];
			heap[i] = temp;
			bubbleDown(lc);
			}
		} else if(compare(heap[i],rvalue) == false){
			//swap towards right
			T temp = heap[rc];
			heap[rc] = heap[i];
			heap[i] = temp;
			bubbleDown(rc);
		}
	}

	public void insert(T elem){
		this.size += 1;
		if(this.capacity < this.size){System.out.println("capacity exceeded"); return;}
		heap[this.size-1] = elem;
		bubbleUp(this.size-1);
	}
	public T peek(){
		if(this.size == 0) return null;
		else return heap[0];
	}
	public T poll(){
		if(this.size == 0) return null;
		T value = heap[0];
		heap[0] = heap[this.size-1];
		this.size--;
		bubbleDown(0);
		return value;
	}
	public void heapify(T[] arr){
		this.size = arr.length;
		for(int i = 0; i < this.size; i++){
			heap[i] = arr[i];
		}
		//heapify algorithm
		for(int i = (this.size)/2-1; i>= 0; i--){
			bubbleDown(i);
		}
	}

	public static void main(String args[]){
		int n;
		Scanner scn = new Scanner(System.in);
		n = scn.nextInt();
		BinaryHeap<Integer> pq = new BinaryHeap<>(Integer.class,n);
		for(int i = 0; i < n; i++){
			int x = scn.nextInt();
			pq.insert(x);
		}
		for(int i = 0; i < n; i++){
			System.out.println(pq.poll());
		}
		scn.close();
	}
}

