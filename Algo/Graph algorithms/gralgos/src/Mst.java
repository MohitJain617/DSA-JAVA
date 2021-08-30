//minimum spanning tree
import java.util.*;

public class Mst {
    static int findMst(ArrayList<ArrayList<Nbr>> graph, boolean visited[]){
        //we can find mst only when graphs are connected
        //start from one point and insert all the edges adjacent into a pq
        //while pq is not empty, loop
        //take out the top most edge(lowest weight) and if visited continue
        //adjacent to the new point added, add the adjacent edges into a pq
        //
        int vtcs = graph.size();
        int start = 0;
        int pathwt= 0;
        int count = 0;
        //making a pq with v^2 max capacity
        BinaryHeap<Edge> pq = new BinaryHeap<Edge>(Edge.class, vtcs*vtcs);
        //insert starting edges
        visited[start] = true;
        for(Nbr temp: graph.get(0)){
            pq.insert(new Edge(start,temp.p,temp.wt));
        }
        while((pq.isEmpty() == false) && (count < vtcs-1)){
            Edge edge = pq.poll();
            if(visited[edge.p2] == true) continue;
            visited[edge.p2] = true;
            for(Nbr temp: graph.get(edge.p2)){
                if(visited[temp.p] == false)
                    pq.insert(new Edge(edge.p2, temp.p, temp.wt));
            }
            pathwt += edge.wt;
            count++;
        }

        return pathwt;
    }
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        ArrayList<ArrayList<Nbr>> graph = new ArrayList<ArrayList<Nbr>>();
        int n = scn.nextInt();
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<Nbr>());
        }
        int q = scn.nextInt(); //q queries follow
        //q lines, each line: start end weight
        //assume undirected
        for(int i = 0; i < q; i++){
            int p1 = scn.nextInt();
            int p2 = scn.nextInt();
            int wt = scn.nextInt();
            graph.get(p1).add(new Nbr(p2,wt));
            graph.get(p2).add(new Nbr(p1,wt));
        }
        boolean visited[] = new boolean[n];
        System.out.println(findMst(graph,visited));
        scn.close();
    }
}
class Nbr{
    //neighbours
    int p;
    int wt;
    public Nbr(int p, int wt){
        this.p = p;
        this.wt = wt;
    }
}

class Edge implements Comparable<Edge>{
    int p1; //point 1, starting 
    int p2; //point 2, ending
    int wt; //weight
    public Edge(int p1, int p2, int wt){
        this.p1 = p1;
        this.p2 = p2;
        this.wt = wt;
    }
    @Override
    public int compareTo(Edge o) {
        // TODO Auto-generated method stub
        return (this.wt - o.wt);
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
		return a.compareTo(b) < 0;
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