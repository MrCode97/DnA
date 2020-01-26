import java.util.List;
import java.util.Objects;

public class Graph<T> {
	private int[][] distances;
	private int[][] graph;
	private int length;
	
	private class Node<T>{
		
	}
	private class Edge implements Comparable<Edge> {
		private int cost;
		private int from;
		private int to;
		
		public Edge(int distance, int from, int to) {
			this.cost = distance;
			this.from = from;
			this.to = to;
		}
		
		@Override
		public int compareTo(Edge arg0) {
			return this.cost > arg0.cost ? 1 : -1; 
		}
		
		@Override
		public boolean equals(Object arg0) {
			return this.to == ((Edge) arg0).to ? true : false;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(((Edge) this).to);
		}
	}
	
	public Graph(int[][] graph) {
		this.distances = new int[graph.length][graph.length];
		this.graph = graph;
		this.length = graph[0].length;
	}

	public Graph(List<List<Integer>> graph) {
		
	}
	
	public Graph(Node<T> root) {
		
	}
	
	public boolean[] bfs(int from){
		// returns array whether node[i] is reachable from `from` 
		Queue<Integer> q = new Queue<Integer>();
		boolean[] visited = new boolean[this.length];
		q.enqueue(from);
		
		while(! q.isEmpty()) {
			Integer next = q.dequeue();
			if(! visited[next]) {
				visited[next]=true;
				for(int notVisited = 0; notVisited < this.length; notVisited++) {
					if(graph[next][notVisited] != Integer.MIN_VALUE) q.enqueue(notVisited);
				}
			} else continue;
		}
		
		return visited;
	}

	public boolean[] dfs(int from) {
		boolean[] visited = new boolean[this.length];
		dfs_rec(from, visited);
		return visited;
	}

	private void dfs_rec(int from, boolean[] visited) {
		visited[from]=true;
		for(int node = 0; node < this.length; node ++) {
			if(visited[node]==false && graph[from][node] != Integer.MIN_VALUE) dfs_rec(node, visited);
		}	
	}
	
	public int[] dijkstraDenseGraph(int from) {
		// use this on dense graphs because its not worth the over-head of the indexed-priority queue
		int[] distances = new int[this.length];
		for(int i = 0; i < this.length; i++) {
			distances[i] = Integer.MAX_VALUE;
		} distances[from]=0;
		MinHeap<Edge> q = new MinHeap<Edge>();
		q.add(new Edge(0, from, from));
		boolean[] visited = new boolean[this.length];
		
		while(! q.isEmpty()) {
			Edge nextEdge = q.poll();
			if(visited[nextEdge.to]) continue;
			else {
				visited[nextEdge.to]=true;
				distances[nextEdge.to] =  Math.min(distances[nextEdge.from] + nextEdge.cost, distances[nextEdge.to]);
				for(int node = 0; node < this.length; node++) {
					if(visited[node] || graph[nextEdge.to][node] == Integer.MIN_VALUE) continue;
					if((distances[nextEdge.to] + graph[nextEdge.to][node]) < distances[node]) {
						distances[node] = distances[nextEdge.to] + graph[nextEdge.to][node];
						}
					q.add(new Edge(graph[nextEdge.to][node], nextEdge.to, node));
					}
				}
			}
		return distances;
	}
	
	public int[] dijkstra(int from) {
		int[] distances = new int[this.length];
		for(int i = 1; i < this.length; i++) 
			distances[i] = Integer.MAX_VALUE;
		boolean[] visited = new boolean[this.length];
		IndexedMinHeap<Edge> q = new IndexedMinHeap<>();
		q.add(new Edge(0, from, from));

		while(! q.isEmpty()) {
			Edge edge = q.poll();
			if(visited[edge.to]) continue;
			visited[edge.to]=true;
			distances[edge.to] = distances[edge.from] + edge.cost;
			for(int outGoing = 0; outGoing < this.length; outGoing++) {
				if(graph[edge.to][outGoing] == Integer.MIN_VALUE) continue;
				distances[outGoing] = Math.min(distances[outGoing], graph[edge.to][outGoing]);
				if(! visited[outGoing]) q.addOrUpdate(new Edge(graph[edge.to][outGoing], edge.to, outGoing));
			}
		}
		return distances;
	}

	

}
