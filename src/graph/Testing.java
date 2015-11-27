package graph;

public class Testing {

	public static void main(String[] args) {
		Graph graph = new Graph();
		Vertex vers[] = new Vertex[10];
		for(int i = 0; i < 10; ++i) {
			vers[i] = new Vertex(i);
		}
		Edge edges[] = new Edge[11];
		edges[0] = new Edge(vers[0], vers[3]);
		edges[1] = new Edge(vers[1], vers[3]);
		edges[2] = new Edge(vers[2], vers[4]);
		edges[3] = new Edge(vers[3], vers[4]);
		edges[4] = new Edge(vers[3], vers[7]);
		edges[5] = new Edge(vers[4], vers[7]);
		edges[6] = new Edge(vers[4], vers[5]);
		edges[7] = new Edge(vers[5], vers[8]);
		edges[8] = new Edge(vers[8], vers[9]);
		edges[9] = new Edge(vers[7], vers[9]);
		edges[10] = new Edge(vers[9], vers[6]);
		
		for(Vertex v : vers) {
			graph.addVertex(v);
		}
		for(Edge e : edges) {
			graph.addEdge(e);
		}
		
		
//		graph.reportGraph();
		graph.BFS(vers[7]);
	}
}
 