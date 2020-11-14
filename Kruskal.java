import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){

        /* Fill this method (The statement return null is here only to compile) */
    	WGraph toReturn = new WGraph();
    	DisjointSets gDisJoint = new DisjointSets(g.getNbNodes());
        for ( Edge e : g.listOfEdgesSorted())
        {
        	if (IsSafe(gDisJoint, e)) // if edge is safe for Kruskal, add to graph to return
        	{
        		toReturn.addEdge(e);
        		gDisJoint.union(e.nodes[0], e.nodes[1]);
        	}
        }
        return toReturn;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){

        /* Only need to return false if e is in the set, because kruskal() uses a sorted ArrayList of edges */
    	if (p.find(e.nodes[0]) == p.find(e.nodes[1])) return false;
        return true;
    
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
