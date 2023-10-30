import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static class Edge {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }

    public static class Union_find {
        private int[] size;
        private int[] parent;

        public Union_find(int n) {
            size = new int[n + 1];
            parent = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                size[i] = 1;
                parent[i] = i;
            }
        }

        public int find(int id) {
            if (id == parent[id]) return id;
            parent[id] = find(parent[id]);
            return parent[id];
        }

        public boolean union(int id1, int id2) {
            int u = find(id1);
            int v = find(id2);
            if (u != v) {
                if (size[u] >= size[v]) {
                    size[u] += size[v];
                    size[v] = 0;
                    parent[v] = u;
                } else {
                    size[v] += size[u];
                    size[u] = 0;
                    parent[u] = v;
                }
                return true;
            }
            return false;
        }
    }

    public static int solution(int n, List<Edge> edges) {
        Union_find uni = new Union_find(n);
        int answer = 0;
        int edgeCount = 0;
        edges.sort(Comparator.comparing(Edge::getWeight));
        Iterator<Edge> it = edges.iterator();

        while (edgeCount < n - 1) {
            Edge e = it.next();
            if (uni.union(e.getFrom(), e.getTo())) {
                edgeCount += 1;
                answer += e.getWeight();
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int connections = Integer.parseInt(br.readLine());
        StringTokenizer st;
        List<Edge> edges = new LinkedList<>();
        for (int i = 0; i < connections; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight));
        }
        System.out.println(solution(n, edges));
    }
}