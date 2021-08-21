import java.util.*;
public class Graphs {
    public static void main(String args[]){
        ArrayList<ArrayList<Edge>> graph = new ArrayList<ArrayList<Edge>>(5);
        ArrayList<Edge> node = new ArrayList<Edge>();
        node.add(new Edge(1,1));
        node.add(new Edge(2,1));
        node.add(new Edge(3,1));
        graph.add(node);
        System.out.println(graph.get(0).get(0).nbr);
    }
}

class Edge{
    int nbr;
    int wt;
    public Edge(int nbr, int wt){
        this.nbr = nbr;
        this.wt = wt;
    }
}