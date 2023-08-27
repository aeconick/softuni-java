import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class DataStreaming {

    static class Edge {
        int to, capacity;

        public Edge(int to, int capacity) {
            this.to = to;
            this.capacity = capacity;
        }
    }

    static List<List<Edge>> graph;
    static boolean[] visited;
    static int[] corrComput;
    static int src, dest;

    public static void main(String[] args) throws IOException {
        BufferedReader buffRead = new BufferedReader(new InputStreamReader(System.in));

        int numComp = Integer.parseInt(buffRead.readLine()); // Number of computers
        int numConn = Integer.parseInt(buffRead.readLine()); // Number of connections

        graph = new ArrayList<>();
        for (int i = 0; i < numComp; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < numConn; i++) {
            StringTokenizer strrr = new StringTokenizer(buffRead.readLine());
            int from = Integer.parseInt(strrr.nextToken());
            int to = Integer.parseInt(strrr.nextToken());
            int capp = Integer.parseInt(strrr.nextToken());
            graph.get(from).add(new Edge(to, capp));
        }

        String[] corruptStr = buffRead.readLine().split(",");
        corrComput = new int[corruptStr.length];
        for (int i = 0; i < corruptStr.length; i++) {
            corrComput[i] = Integer.parseInt(corruptStr[i]);
        }

        src = Integer.parseInt(buffRead.readLine()); // Source computer
        dest = Integer.parseInt(buffRead.readLine()); // Destination computer

        visited = new boolean[numComp];
        int maxData = 0;
        int flow;

        do {
            Arrays.fill(visited, false);
            flow = dfs(src, Integer.MAX_VALUE);
            maxData += flow;
        } while (flow > 0);

        System.out.println(maxData);
    }

    static int dfs(int current, int minCapacity) {
        if (current == dest) {
            return minCapacity;
        }

        visited[current] = true;

        for (Edge edge : graph.get(current)) {
            int to = edge.to;
            int capacity = edge.capacity;

            if (!visited[to] && Arrays.binarySearch(corrComput, to) < 0 && capacity > 0) {
                int newMinCapacity = Math.min(minCapacity, capacity);
                int flow = dfs(to, newMinCapacity);

                if (flow > 0) {
                    edge.capacity -= flow;
                    for (Edge reverseEdge : graph.get(to)) {
                        if (reverseEdge.to == current) {
                            reverseEdge.capacity += flow;
                            break;
                        }
                    }
                    return flow;
                }
            }
        }

        return 0;
    }
}
