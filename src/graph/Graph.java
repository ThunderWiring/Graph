package graph;
import java.util.*;
/*
 * Represents a single vertex node of the graph
 * @param k: degree of the vertex - used for BFS, DFS, and other algorithms.
 * @param data: the data which the vertex holds.
 * @param lowpoint: updated by DFS
 * @param dist: represents the distance for a pre-defined source vertex 's'
 */
class Vertex {
	private int k;
	private int data;
	private int lowPoint;
	private int dist;
	private Vertex parent;

	// c'tors:
	public Vertex() {
		this.k = 0;
		this.data = 0;
		this.lowPoint = 0;
		this.dist = 0;
		this.parent = null;
	}

	public Vertex(int data) {
		this.data = data;
	}

	// getters and setters:
	public void set_k(final int newK) {
		if (newK < 0) {
			System.err.println("Error: cannot assign negative value as a vertex degree.");
			return;
		}
		this.k = newK;
	}

	public int get_k() {
		return this.k;
	}

	public void set_data(final int newData) {
		this.data = newData;
	}

	public int get_data() {
		return this.data;
	}

	public void set_lowPoint(final int newLowPoint) {
		this.lowPoint = newLowPoint;
	}

	public int get_lowPoint() {
		return this.lowPoint;
	}

	public void set_dist(final int newDist) {
		if (newDist < 0) {
			System.err.println("Error: cannot assign negative value as a distance.");
			return;
		}
		this.dist = newDist;
	}

	public int get_dist() {
		return this.dist;
	}

	public Vertex get_parent() {
		return this.parent;
	}

	// note: a parent of a vertex could be 'null'
	public void set_parent(final Vertex newParent) {
		this.parent = newParent;
	}
}

/******************************************************************************/
class Edge {
	private Vertex from;
	private Vertex to;
	private int weight;

	// c'tor:
	public Edge(Vertex _from, Vertex _to) {
		// TODO: implement later
	}

	public Edge(int _weight) {
		// TODO: implement later
	}

	// getters and setters:
	public void setFrom(final Vertex newFrom) {
		if (newFrom == null) {
			System.err.println("Error: cannot assign 'null' as an edge's endpoint");
			return;
		}
		this.from = newFrom;
	}

	public void setTo(final Vertex newTo) {
		if (newTo == null) {
			System.err.println("Error: cannot assign 'null' as an edge's endpoint");
			return;
		}
		this.to = newTo;
	}

	public void setWeight(final int newWeight) {
		this.weight = newWeight;
	}

	public int getWeight() {
		return this.weight;
	}

	public Vertex getFrom() {
		return this.from;
	}

	public Vertex getTo() {
		return this.to;
	}
}

/******************************************************************************/
public class Graph {
	public enum Option {
		Vertex, Edge
	};

	private int vertecesSize;
	private int edgesSize;
	private List<Vertex> verteces = new ArrayList<Vertex>();
	private List<Edge> edges = new ArrayList<Edge>();

	// c'tor:
	public Graph() {
		this.vertecesSize = 0;
		this.edgesSize = 0;
	}

	// getters and setters:
	public final int Size(Option op) {
		if (op == Option.Vertex) {
			return this.vertecesSize;
		} else if (op == Option.Edge) {
			return this.edgesSize;
		}
		return -1;
	}

	// API:
	public void addEdge(Edge newEdge) {
		this.edges.add(newEdge);
		edgesSize++;
	}

	public void addVertex(Vertex newVertex) {
		this.verteces.add(newVertex);
		vertecesSize++;
	}

	/*
	 * removes a certain given vertex form the graph. if that vertex is not
	 * fount, then don't remove anything.
	 */
	public void removeVertex(Vertex v) {
		// TODO: implement later
	}

	/* removes all the vertices(and edges) from the graph. */
	public void clearAll() {
		// TODO: implement later
	}

	/*
	 * if op == Vertex: removes all the vertices with the given 'value = k'
	 * degree. if op == Edge: removes all the edges with the given 'value =
	 * weight'.
	 */
	public void clearAll(final int value, Option op) {
		// TODO: implement later
	}

	// Algorithms:
	public void BFS(final Vertex src) {
		// TODO: implement later
	}

	public void DFS(final Vertex src) {
		// TODO: implement later
	}
}
