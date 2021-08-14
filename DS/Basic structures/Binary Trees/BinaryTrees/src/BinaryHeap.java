import jdk.javadoc.internal.doclets.formats.html.resources.standard;

public class BinaryHeap {
	static int parent(int index){return (index-1)/2;}
	static int lchild(int index){return (index*2 + 1);}
	static int rchild(int index){return (index*2 + 2);}
	//rchild = (index*2 + 2)

	//given arbitrary elements, makes min Heap in O(n)

	//type = true means max heap, else min heap
	static void bubbleUp(int[] H, int curr, boolean type){
		if(curr == 0){
			return;
		} else {
			p = parent(curr);
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
	static void bubbleDown(int[] H, int curr, int end, boolean type){
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
					H[lc] = temp;
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
					H[lc] = temp;
					bubbleDown(H,rc,end,type);
				}
			}
		}
	}

	static void maxHeap(int[] H, int end){
		int len = end+1;
		int trav = len/2;
		for(;trav >= 0; trav--){
			//bubble down each of these values 
			bubbleDown(H,trav,end,true);
		}
	}
	static void minHeap(int[] H, int end){
		int len = end + 1;
		int trav = len/2;
		for(;trav >= 0; trav--){
			bubbleDown(H,trav,end,false);
		}
	}
	//make sure the size of the heap array is sufficiently large
	static void insertHeap(int[] arr, int end){}
	//return and remove the minimum element O(log(n))
	static int extractMin(int[] H, int end){
		//
	}
	//return the min element O(1)
	static int findMin(int[] H, int end){
		if(end < 0) return Integer.MAX_VALUE;
		return H[0];
	}
	//find and decrease the value of key by d, O(n)
	static void decreaseKey(int key, int d, int[] H){}
	//merge 2 given heaps
	static int[] mergeHeap(int[] H1, int[] H2){}
}

