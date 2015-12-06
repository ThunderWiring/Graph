package graph;

import graph.Edge.EdgeState;

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

	public final int id;
	public final int INITIAL_K_VALUE = -1;

	// c'tors:
	public Vertex(int id) {
		this.id = id;
		this.k = INITIAL_K_VALUE;
		this.data = 0;
		this.lowPoint = 0;
		this.dist = 0;
		this.parent = null;
	}

	// Inheritance:
	@Override
	public boolean equals(Object ver) {
		if ((ver == null) || (!(ver instanceof Vertex))) {
			return false;
		}
		Vertex v = (Vertex) ver;
		return v.id == this.id;
	}

	public Vertex(int id, int data) {
		this.data = data;
		this.id = id;
	}

	// getters and setters:
	public void set_k(final int newK) {
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
			System.err.println("Error: distance cannot be negative.");
			return;
		}
		this.dist = newDist;
	}

	public int get_dist() {
		return this.dist;
	}

	public Vertex get_parent() {
		if (parent == null) {
			Vertex v = new Vertex(-1);
			return v;
		}
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
	private EdgeState state;

	/* Edges'states used by the algorithms:
	 * @RED @BLUE: used inorder to determine a MST used by Kruskal and Prim
	 * @NEW: an edge which wasn'r discovered since the start of an algorithm
	 * @OLD: an edge whichwas already discovered.
	 * @CROSS_EDGE, TREE_EDGE, BACK_EDGE: Edge types in DFS*/
	public enum EdgeState {
		RED, BLUE, NEW, OLD, CROSS_EDGE, TREE_EDGE, BACK_EDGE
	};

	// c'tor:
	public Edge(Vertex from, Vertex to) {
		if (from == null || to == null) {
			throw new NullPointerException(
					"Error: Cannot assign null value as an edge's endpoint");
		}
		this.from = from;
		this.to = to;
		this.weight = 0;
		this.state = EdgeState.NEW;
	}

	public Edge(int weight) {
		this.weight = weight;
		this.state = EdgeState.NEW;
	}

	// Inhiretance:
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Edge)) {
			return false;
		}
		Edge e = (Edge) obj;
		return (e.getFrom().equals(from)) && (e.getTo().equals(to));
	}

	// getters and setters:
	public void setState(final EdgeState newState) {
		this.state = newState;
	}

	public final EdgeState getState() {
		return this.state;
	}

	public void setFrom(final Vertex newFrom) {
		if (newFrom == null) {
			System.err.println("Error: edge's endpoint cannot be 'null'");
			return;
		}
		this.from = newFrom;
	}

	public void setTo(final Vertex newTo) {
		if (newTo == null) {
			System.err.println("Error: edge's endpoint cannot be 'null'");
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
final class Path {
	private List<Vertex> pathVertices;
	private List<Edge> pathEdges;

	public void addEdge(Edge e) {
		for (Edge edge : pathEdges) {
			if (edge.equals(e)) {
				return;
			}
		}
		pathEdges.add(e);
		boolean from = false;
		boolean to = false;
		for (Vertex v : pathVertices) {
			if (v.equals(e.getFrom())) {
				from = true;
			} else if (v.equals(e.getTo())) {
				to = true;
			} else if (from && to) {
				break;
			}
		}
		if (!to) {
			pathVertices.add(e.getTo());
		} else if (!from) {
			pathVertices.add(e.getFrom());
		}
	}

	/* print the path's edges. */
	public void printPath() {
		for (Edge e : pathEdges) {
			if (e == null) {
				continue;
			}
			System.out.print("(" + e.getFrom().id + ")->(" + e.getTo().id);
		}
	}
}

/******************************************************************************/
/*
 * Main Class: Simple graph data structure.
 */
public class Graph {
	public enum Option {
		Vertex, Edge, NoSuchVertix, NoSuchEdge
	};

	private List<Vertex> vertices = new ArrayList<Vertex>();
	private List<Edge> edges = new ArrayList<Edge>();

	// getters and setters:
	public final int Size(Option op) {
		if (op == Option.Vertex) {
			return this.vertices.size();
		} else if (op == Option.Edge) {
			return this.edges.size();
		}
		return -1;
	}

	// API:
	public void addEdge(Edge newEdge) {
		this.edges.add(newEdge);
	}

	/*
	 * Adds a new edge between 2 vertices: src and dst. Should first verify that
	 * the 2 vertices exist in the graph, if not, return error. Also, if there
	 * is already an edge between the given 2 vertices, return error, since our
	 * graph is simple.
	 */
	public void addEdge(Vertex src, Vertex dst) {
		if ((src == null) || (dst == null)) {
			System.err.println("Error: 2 null vertices can't make edge.");
			return;
		}
		Edge newEdge = new Edge(src, dst);
		this.edges.add(newEdge);
	}

	public void addVertex(final Vertex newVertex) {
		if (newVertex == null) {
			System.err.println("Error: cannot add null vertices to Graph.");
			return;
		}
		for (Vertex ver : this.vertices) {
			if (ver.equals(newVertex)) {
				System.err.println("Error: Vertex already exist.");
				return;
			}
		}
		this.vertices.add(newVertex);
	}

	/*
	 * Add a new vertex with the given id number. Verify there is no other
	 * vertex already exists with the same id.
	 */
	public void addVertex(final int id, final int data) {
		for (Vertex v : vertices) {
			if (v.id == id) {
				return;
			}
		}
		Vertex newVertex = new Vertex(id, data);
		addVertex(newVertex);
	}

	/*
	 * removes a certain given vertex form the graph. if that vertex is not
	 * fount, then don't remove anything.
	 */
	public void removeVertex(Vertex vertex) {
		if (vertex == null) {
			return;
		}
		for (Vertex ver : this.vertices) {
			if (ver.equals(vertex)) {
				this.vertices.remove(ver);
				return;
			}
		}
	}

	/*
	 * removes the passed vertex from the vertices of the graph along with all
	 * the nieghbours of this vertex. NOTE: @param 'vertex' is removed from the
	 * graph as well.
	 */
	public void removeNeighbourEdges(Vertex vertex) {
		if (vertex == null) {
			return;
		}
		for (Edge edge : this.edges) {
			if ((edge.getFrom().equals(vertex))
					|| (edge.getTo().equals(vertex))) {
				this.vertices.remove(edge);
			}
		}
	}

	/* return a list of edges containg all the brancing edges out of 'ver' */
	private final List<Edge> getNeighborEdges(final Vertex ver) {
		if (ver == null) {
			return null;
		}
		List<Edge> result = new ArrayList<Edge>();
		for (Edge e : edges) {
			if (e == null) {
				continue;
			} else if (e.getFrom().equals(ver) || e.getTo().equals(ver)) {
				result.add(e);
			}
		}
		return result;
	}

	/* return a list of vertices containng all the neighbor vertices of 'ver' */
	private final List<Vertex> getNeighbourVertices(final Vertex ver) {
		if (ver == null) {
			return null;
		}
		List<Vertex> result = new ArrayList<Vertex>();
		for (Edge e : this.edges) {
			if (e == null) {
				continue;
			}
			if (e.getFrom().equals(ver)) {
				result.add(e.getTo());
			} else if (e.getTo().equals(ver)) {
				result.add(e.getFrom());
			}
		}
		return result;
	}

	/* removes all the vertices(and edges) from the graph. */
	public void clearAll() {
		this.vertices.clear();
		this.edges.clear();
	}

	/*
	 * if op == Vertex: removes all the vertices with the given 'value = k'
	 * degree.
	 * 
	 * if op == Edge: removes all the edges with the given 'value = weight'.
	 */
	public void clearAll(final int value, Option op) {
		if (op == Option.Edge) {
			for (Edge edge : this.edges) {
				if (edge.getWeight() == value) {
					this.edges.remove(edge);
				}
			}
			return;
		} else if (op == Option.Vertex) {
			for (Vertex ver : this.vertices) {
				if (ver.get_k() == value) {
					removeNeighbourEdges(ver);
				}
			}
		}
	}

	// Algorithms:
	private enum Algorithm {
		BFS, DFS, PRIM, KRUSKAL, Dijistra
	};

	private void init(final Algorithm algo, Vertex ver) {
		if (algo == Algorithm.BFS) {
			ver.set_k(0);
			ver.set_parent(null);
			ver.set_dist(0);
			for (Vertex v : this.vertices) {
				if (ver.equals(ver)) {
					continue;
				}
				v.set_k(v.INITIAL_K_VALUE);
				v.set_parent(null);
			}
		} else if (algo == Algorithm.DFS) {
			// TODO: implement later
		} else if (algo == Algorithm.Dijistra) {
			for (Vertex v : this.vertices) {
				v.set_dist(v.INITIAL_K_VALUE);
			}
			ver.set_dist(0);
		}
	}

	/* traverses the graph using Breadth Width First algorithm. */
	public void BFS(final Vertex src) {
		if (src == null) {
			return;
		}
		init(Algorithm.BFS, src);
		Queue<Vertex> bfsQ = new LinkedList<Vertex>();
		bfsQ.add(src);
		while (bfsQ.size() != 0) {
			Vertex tmp = bfsQ.poll();
			int tagNumber = tmp.get_k() + 1;
			List<Vertex> neighbours = getNeighbourVertices(tmp);
			for (Vertex v : neighbours) {
				if (v.get_k() == v.INITIAL_K_VALUE) {
					v.set_k(tagNumber);
					v.set_dist(tagNumber);
					v.set_parent(tmp);
					bfsQ.add(v);
				}
			}
			neighbours.clear();
		}
		// report BFS result:
		System.out.println("source = " + src.id);
		for (Vertex v : vertices) {
			System.out.println("vertex id: " + v.id
					+ " distance from source = " + v.get_dist());
		}
	}

	/*
	 * return 'true' if there still new edges branch out of a given vertex
	 * 'ver'. return 'false' otherwise.
	 */
	private boolean areNewEdgesLeft(Vertex ver) {
		if (ver == null) {
			return false;
		}
		for (Edge e : edges) {
			if (((e.getFrom().equals(ver)) || (e.getTo().equals(ver)))
					&& (e.getState() == EdgeState.NEW)) {
				return true;
			}
		}
		return false;
	}

	/* traverses the graph using Depth Width First algorithm. */
	public void DFS(final Vertex src) {
		if (src == null) {
			return;
		}
		init(Algorithm.DFS, src);
		List<Edge> neighbors = new ArrayList<Edge>();
		Vertex tmp = src;
		src.set_k(0);
		while (areNewEdgesLeft(tmp)) {
			for(Edge e : neighbors) {
				e.setState(EdgeState.OLD);
			}
			
		}
	}

	private void prim() {
		// TODO: implement later
		// needs a min-heap DS..
		List<Vertex> mstVertices = new ArrayList<Vertex>();
		mstVertices.add(this.vertices.get(0));
	}

	private void kruskal() {
		// TODO: implement later
		// needs a union find DS..
	}

	public void getMST() {
		// TODO: get it to return a tree insttead of void
		// call to krusccal or prim
	}

	/*
	 * calculates the shortest path between Vertex 'src' 
	 * and all the other vertices in the graph. 
	 */
	public void dijkstra(Vertex src) {
		// TODO: implement later
		init(Algorithm.Dijistra, src);
		List<Vertex> temp = this.vertices;
		while(temp.size() != 0) {
			Vertex u = getMinmalVertex(temp);
		}
	}
	private Vertex getMinmalVertex(List<Vertex> vers) {
		Vertex result = this.vertices.get(0);
		for(Vertex v : vers) {
			if(v.get_dist() < 0) {
				continue;
			}
			
		}
		return result;
	}

	/***********************************************************************/
	// prints the graph's status and information of the vertices and edges.
	public void reportGraph() {
		System.out.println("Showing Vertices and Edges of the graph:");
		System.out.println("vertices:" + vertices.size());
		for (Vertex v : vertices) {
			System.out.println("id: " + v.id + ", data:" + v.get_data());
		}

		System.out.println("edges: " + edges.size());
		for (Edge e : this.edges) {
			if (e == null) {
				continue;
			}
			System.out.println("(" + e.getFrom().id + ") -> (" + e.getTo().id
					+ ")");
		}
	}
}
