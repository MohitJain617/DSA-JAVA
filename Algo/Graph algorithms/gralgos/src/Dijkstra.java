import java.util.*;
public class Dijkstra {
	static int[] dijkstra(ArrayList<ArrayList<Nbr>> graph, boolean [] visited, int mindist[], int start, int end){
		//to retrace path we need a parent array which gives parent of current 
		//point. visited marks the point as having the minimum value it can achieve
		//in terms of distarce from the source
		int vtcs = graph.size();
		int parent[] = new int[vtcs];
		for(int i = 0; i < vtcs; i++){
			mindist[i] = Integer.MAX_VALUE;
			parent[i] = -1;
		}
		mindist[start] = 0;
		BinaryHeap<Point> pq = new BinaryHeap(Point.class,vtcs*vtcs);
		pq.insert(new Point(start,0));
		while(pq.isEmpty() == false){
			Point curr = pq.poll();
			if(visited[curr.p] == true) continue;
			mindist[curr.p] = curr.dist; 
			visited[curr.p] = true;
			if(curr.p == end) break;
			for(Nbr edge : graph.get(curr.p)){
				if(visited[edge.p] == true) continue;
				int wt = mindist[curr.p] + edge.wt;
				if(wt < mindist[edge.p]){
					mindist[edge.p] = wt;
					parent[edge.p] = curr.p;
					pq.insert(new Point(edge.p,mindist[edge.p]));
				}
			}
		}
		return parent;
	}
	public static void main(String args[]){
		Scanner scn = new Scanner(System.in);
		int vtcs = scn.nextInt();
		ArrayList<ArrayList<Nbr>> graph = new ArrayList<ArrayList<Nbr>>();
		for(int i = 0; i < vtcs; i++){
			graph.add(new ArrayList<Nbr>());
		}
		int edges = scn.nextInt();
		for(int i = 0; i < edges; i++){
			int p1 = scn.nextInt();
			int p2 = scn.nextInt();
			int wt = scn.nextInt();
			graph.get(p1).add(new Nbr(p2,wt));
			graph.get(p2).add(new Nbr(p1,wt));
		}
		int start = scn.nextInt();
		int end = scn.nextInt();
		boolean visited[] = new boolean[vtcs];
		int minDist[] = new int[vtcs];
		int[] parent = dijkstra(graph,visited,minDist,start,end);
		System.out.println(minDist[end]);
		System.out.println();
		int trav = end;
		while(trav != -1){
			System.out.print(trav + "->");
			trav = parent[trav];
		}

		scn.close();
	}
}

class Nbr{
	int p;
	int wt;
	public Nbr(int p, int wt){
		this.p = p;
		this.wt = wt;
	}
}

class Point implements Comparable<Point>{
	int p;
	int dist;
	public Point(int p, int dist){
		this.p = p;
		this.dist = dist;
	}
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return this.dist-o.dist;
	}

}

class BinaryHeap<T extends Comparable<T>> {

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
		return a.compareTo(b) > 0;
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

	
}