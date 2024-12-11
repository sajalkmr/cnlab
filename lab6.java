import java.util.Scanner;

public class ShortestPathAlgorithms {
    public static void bellmanFord(int[][] graph, int nodesCount, int source) {
        int[] dist = new int[nodesCount];
        for (int i = 0; i < nodesCount; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;

        for (int i = 1; i < nodesCount; i++) {
            for (int u = 0; u < nodesCount; u++) {
                for (int v = 0; v < nodesCount; v++) {
                    if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        for (int u = 0; u < nodesCount; u++) {
            for (int v = 0; v < nodesCount; v++) {
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }

        System.out.println("\nBellman-Ford Algorithm (Shortest Paths from Source " + source + "):");
        for (int i = 0; i < nodesCount; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("Node " + i + ": INF");
            } else {
                System.out.println("Node " + i + ": " + dist[i]);
            }
        }
    }

    public static void pathVectorRouting(int[][] graph, int nodesCount) {
        int[][] routingTable = new int[nodesCount][nodesCount];
        for (int i = 0; i < nodesCount; i++) {
            for (int j = 0; j < nodesCount; j++) {
                if (i == j) {
                    routingTable[i][j] = 0;
                } else if (graph[i][j] != 0) {
                    routingTable[i][j] = graph[i][j];
                } else {
                    routingTable[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        boolean updated;
        do {
            updated = false;
            for (int i = 0; i < nodesCount; i++) {
                for (int j = 0; j < nodesCount; j++) {
                    if (i != j) {
                        for (int k = 0; k < nodesCount; k++) {
                            if (routingTable[i][k] != Integer.MAX_VALUE && routingTable[k][j] != Integer.MAX_VALUE) {
                                int newDist = routingTable[i][k] + routingTable[k][j];
                                if (newDist < routingTable[i][j]) {
                                    routingTable[i][j] = newDist;
                                    updated = true;
                                }
                            }
                        }
                    }
                }
            }
        } while (updated);

        System.out.println("\nPath Vector Routing (Final Routing Tables):");
        for (int i = 0; i < nodesCount; i++) {
            System.out.print("Routing Table for Node " + i + ": ");
            for (int j = 0; j < nodesCount; j++) {
                if (routingTable[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");
                } else {
                    System.out.print(routingTable[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of nodes: ");
        int nodesCount = sc.nextInt();
        int[][] graph = new int[nodesCount][nodesCount];
        System.out.print("Enter the number of edges: ");
        int edgesCount = sc.nextInt();
        System.out.println("Enter the edges in the format (u, v, weight):");

        for (int i = 0; i < edgesCount; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph[u][v] = weight;
        }

        System.out.print("\nEnter the source node for Bellman-Ford: ");
        int source = sc.nextInt();
        bellmanFord(graph, nodesCount, source);
        pathVectorRouting(graph, nodesCount);
        sc.close();
    }
}
