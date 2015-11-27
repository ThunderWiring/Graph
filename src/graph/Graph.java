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
			System.err
					.println("Error: cannot assign negative value as a distance.");
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

	// TODO: creat an enum called EdgeOptions which indicate:
	// whether the edge is new or already discovered

	// c'tor:
	public Edge(Vertex from, Vertex to) {
		if (from == null || to == null) {
			throw new NullPointerException(
					"Error: Cannot assign null value as an edge's endpoint");
		}
		this.from = from;// new Vertex(_from.id, _from.get_data());
		this.to = to;// new Vertex(_to.id, _to.get_data());
		this.weight = 0;
	}

	public Edge(int weight) {
		this.weight = weight;
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
	public void setFrom(final Vertex newFrom) {
		if (newFrom == null) {
			System.err
					.println("Error: cannot assign 'null' as an edge's endpoint");
			return;
		}
		this.from = newFrom;
	}

	public void setTo(final Vertex newTo) {
		if (newTo == null) {
			System.err
					.println("Error: cannot assign 'null' as an edge's endpoint");
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
/*
 * Main Class: Simple graph data structure.
 */
public class Graph {
	public enum Option {
		Vertex, Edge, NoSuchVertix, NoSuchEdge
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

	/*
	 * Adds a new edge between 2 vertices: src and dst. Should first verify that
	 * the 2 vertices exist in the graph, if not, return error. Also, if there
	 * is already an edge between the given 2 vertices, return error, since our
	 * graph is simple.
	 */
	public void addEdge(Vertex src, Vertex dst) {
		if ((src == null) || (dst == null)) {
			System.err
					.println("Error: cannot add an edge between 2 null vertices.");
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
		for (Vertex ver : this.verteces) {
			if (ver.equals(newVertex)) {
				System.err.println("Error: Vertex already exist.");
				return;
			}
		}
		this.verteces.add(newVertex);
		vertecesSize++;
	}

	/*
	 * Add a new vertex with the given id number. Should verify that there is no
	 * other vertex already exists with the same id.
	 */
	public void addVertex(final int id, final int data) {
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
		for (Vertex ver : this.verteces) {
			if (ver.equals(vertex)) {
				this.verteces.remove(ver);
				return;
			}
		}
		System.err.println("Error: No suc vertex found");
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
				this.verteces.remove(edge);
			}
		}
	}

	public final List<Vertex> getNeighbours(final Vertex ver) {
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
		this.verteces.clear();
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
			for (Vertex ver : this.verteces) {
				if (ver.get_k() == value) {
					removeNeighbourEdges(ver);
				}
			}
		}
	}

	// Algorithms:
	public void BFS(final Vertex src) {
		if (src == null) {
			return;
		}
		src.set_k(0);
		src.set_parent(null);
		src.set_dist(0);
		for (Vertex ver : this.verteces) {
			if(ver.equals(src)) {
				continue;
			}
			ver.set_k(ver.INITIAL_K_VALUE);
			ver.set_parent(null);
		}
		Queue<Vertex> bfsQ = new LinkedList<Vertex>();
		bfsQ.add(src);
		while (bfsQ.size() != 0) {
			Vertex tmp = bfsQ.poll();
			int tagNumber = tmp.get_k() + 1;
			List<Vertex> neighbours = getNeighbours(tmp);
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
		for (Vertex v : verteces) {
			System.out.println("vertex id: " + v.id
					+ " distance from source = " + v.get_dist());
		}
	}

	public void DFS(final Vertex src) {
		// TODO: implement later
	}

	public void prim() {
		// TODO: implement later
	}

	public void kruskal() {
		// TODO: implement later
	}

	public void reportGraph() {
		System.out.println("vertices: ");
		for (Vertex v : verteces) {
			System.out.println("adding vertex: " + v.id);
		}

		System.out.println("edges: " + edges.size());
		for (Edge e : this.edges) {
			if (e == null) {
				continue;
			}
			System.out.println("adding edge: (" + e.getFrom().id + ") -> ("
					+ e.getTo().id + ")");
		}
	}
}
