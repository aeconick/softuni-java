import java.util.*;

class BitcoinGroups {
    static List<List<Integer>> graph;
    static boolean[] visited;
    static List<Integer> currentGroup;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int w = scanner.nextInt(); // Number of wallets
        int t = scanner.nextInt(); // Number of transactions

        graph = new ArrayList<>();
        for (int i = 0; i < w; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < t; i++) {
            int sender = scanner.nextInt();
            int receiver = scanner.nextInt();
            graph.get(sender).add(receiver);
        }

        visited = new boolean[w];
        List<List<Integer>> groups = new ArrayList<>();

        for (int i = 0; i < w; i++) {
            if (!visited[i]) {
                currentGroup = new ArrayList<>();
                dfs(i);
                groups.add(currentGroup);
            }
        }

        int maxGroupSize = -1;
        List<Integer> mostActiveGroup = null;

        for (List<Integer> group : groups) {
            if (group.size() > maxGroupSize) {
                maxGroupSize = group.size();
                mostActiveGroup = group;
            }
        }

        Set<String> transactions = new HashSet<>();
        for (int sender : mostActiveGroup) {
            for (int receiver : graph.get(sender)) {
                if (mostActiveGroup.contains(receiver)) {
                    transactions.add(sender + " -> " + receiver);
                }
            }
        }

        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    static void dfs(int node) {
        visited[node] = true;
        currentGroup.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor);
            }
        }
    }
}