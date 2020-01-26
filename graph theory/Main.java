
public class Main {
	public static void main(String args[]) {
		int[][] graph = new int[7][7];
		for(int x = 0; x<graph.length; x++) {
			for(int y = 0; y < graph.length; y++) {
				graph[x][y]=Integer.MIN_VALUE;
			}
		}
		graph[0][1] = 7;
		graph[0][2] = 11;
		graph[1][0] = 7;
		graph[1][2] = 5;
		graph[1][3] = 1;
		graph[2][0] = 11;
		graph[2][1] = 5;
		graph[2][4] = 2;
		graph[3][1] = 1;
		graph[3][5] = 1;
		graph[4][2] = 2;
		graph[4][6] = Integer.MIN_VALUE;
		graph[4][5] = 8;
		graph[5][4] = 8;
		graph[5][3] = 1;
		graph[6][4] = Integer.MIN_VALUE;
		
		Graph g = new Graph(graph);
		boolean[] reachable = g.dfs(0);
		for(int i=0; i<reachable.length;i++) {
			if(reachable[i]) {
				System.out.println("Node " + (i+1) + " is reachable");
			} else {
				System.out.println("Node " + (i+1) + " is NOT reachable");
			}
			
		}
		int from = 2;
		int[] dist = g.dijkstraDenseGraph(from);
		for(int i=0; i<dist.length;i++) {
			System.out.println("Shortest-Path from " + (from+1) + " to " + (i+1) + " is: " + dist[i]);
		}
		int from2 = 2;
		int[] dist2 = g.dijkstra(from2);
		for(int i=0; i<dist2.length;i++) {
			System.out.println("Shortest-Path from " + (from+1) + " to " + (i+1) + " is: " + dist[i]);
		}
	}
}
