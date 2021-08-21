import java.util.*;
public class BinaryHeap {

	int[] heap;
	int capacity;
	int size;
	boolean maxType;

	public BinaryHeap(int capacity, boolean type){
		heap = new int[capacity];
		this.capacity = capacity;
		this.size = 0;
		maxType = type;
	}
	public void insert(int elem){
		insertHeap(elem,this.heap,this.size,this.maxType);
		this.size++;
	}
	public int extract(){
		return extractFirst(this.heap,this.size-1,this.maxType);
	}
	public int size(){
		return this.size;
	}
	//takes in an array and makes it a heap in O(n) time
	public void loadHeap(int[] H, int size){
		this.heap = H;
		this.size = size;
		this.capacity = size;
		if(this.maxType == true) maxHeap(this.heap,this.size-1);
		else minHeap(this.heap,this.size-1);
	}

	public int peek(){
		return peek(this.heap,this.size-1);
	}
	

	public int parent(int index){return (index-1)/2;}
	public int lchild(int index){return (index*2 + 1);}
	public int rchild(int index){return (index*2 + 2);}
	//rchild = (index*2 + 2)

	//given arbitrary elements, makes min Heap in O(n)

	//type = true means max heap, else min heap
	public void heapSort(int[] H, boolean ascending){
		int len = H.length;
		if(ascending == true)
			maxHeap(H,len-1);
		else
			minHeap(H,len-1);
		int temp = len-1;
		for(int i = 0; i < len; i++){
			extractFirst(H,temp--,ascending);
		}
	}
	private void bubbleUp(int[] H, int curr, boolean type){
		if(curr == 0){
			return;
		} else {
			int p = parent(curr);
			if(type == true){
				if(H[p] < H[curr]){
					int temp = H[p];
					H[p] = H[curr];
					H[curr] = temp;
					bubbleUp(H,p,type);
				} else {
					return;
				}
			}
			else{
				if(H[p] > H[curr]){
					int temp = H[p];
					H[p] = H[curr];
					H[curr] = temp;
					bubbleUp(H,p,type);
				} else {
					return;
				}
			}
			return;
		}
	}
	//type = true means max heap, else min heap
	//end inclusive
	private void bubbleDown(int[] H, int curr, int end, boolean type){
		if(curr >= end){
			return;
		} 
		else if(lchild(curr) > end){
			return;
		}
		else {
			int lc = lchild(curr);
			int rc;
			if(rchild(curr) > end) rc = -1;
			else rc = rchild(curr);
			if(type == true){
				if(((rc == -1) || (H[lc] >= H[rc])) && (H[curr] < H[lc])){
					int temp = H[curr];
					H[curr] = H[lc];
					H[lc] = temp;
					bubbleDown(H,lc,end,type);
				}
				else if(((rc != -1) && (H[rc] > H[lc])) && (H[curr] < H[rc])){
					int temp = H[curr];
					H[curr] = H[rc];
					H[rc] = temp;
					bubbleDown(H,rc,end,type);
				}
			}
			else{
				if(((rc == -1) || (H[lc] <= H[rc])) && (H[curr] > H[lc])){
					int temp = H[curr];
					H[curr] = H[lc];
					H[lc] = temp;
					bubbleDown(H,lc,end,type);
				}
				else if(((rc != -1) && (H[rc] < H[lc])) && (H[curr] > H[rc])){
					int temp = H[curr];
					H[curr] = H[rc];
					H[rc] = temp;
					bubbleDown(H,rc,end,type);
				}
			}
		}
	}

	public void maxHeap(int[] H, int end){
		int len = end+1;
		int trav = len/2;
		for(;trav >= 0; trav--){
			//bubble down each of these values 
			bubbleDown(H,trav,end,true);
		}
	}
	public void minHeap(int[] H, int end){
		int len = end + 1;
		int trav = len/2;
		for(;trav >= 0; trav--){
			bubbleDown(H,trav,end,false);
		}
	}
	//make sure the size of the heap array is sufficiently large
	private void insertHeap(int element, int[] H, int end, boolean type){
		if(end == H.length-1){
			return;
		}
		H[end+1] = element;
		bubbleUp(H,end+1,type);
	}
	//return and remove the minimum element O(log(n))
	private int extractFirst(int[] H, int end, boolean type){
		if(end == 0){
			return H[0];
		}

		int toRet = H[0];
		//swap
		int temp = H[end];
		H[end] = H[0];
		H[0] = temp;
		bubbleDown(H,0,end-1,type);
		return toRet;
	}
	//return the min element O(1)
	private int peek(int[] H, int end){
		if(end < 0) return Integer.MAX_VALUE;
		return H[0];
	}
	//find and decrease the value of key by d, O(n)
	private boolean decreaseKey(int key, int d, int[] H, boolean maxType){
		int i;
		for(i = 0; i < H.length; i++){
			if(H[i] == key){
				break;
			}
		}
		//key not in list, return false
		if(i == H.length) return false;
		H[i] = H[i]-d;
		bubbleUp(H,i,maxType);
		bubbleDown(H,i,H.length-1,maxType);
		//if found and action taken, return true
		return true;
	}

	public static void main(String args[]){
		int n;
		Scanner scn = new Scanner(System.in);
		n = scn.nextInt();
		int[] arr = new int[n];
		for(int i = 0; i < n; i++){
			arr[i] = scn.nextInt();
		}
		heapSort(arr,false);
		for(int i = 0; i < n; i++){
			System.out.print(arr[i] + " ");
		}
		scn.close();
	}
}

