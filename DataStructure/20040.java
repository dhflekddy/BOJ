import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Testing3 {
    public static class Edge {
        int from;
        int to;
        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    public static class Union_find {
        int[] parent;
        int[] size;
        public Union_find(int numOfNodes) {
            parent = new int[numOfNodes];
            for (int i = 0; i < numOfNodes; ++i)
                parent[i] = i;
            size=new int[numOfNodes];
            Arrays.fill(size, 0);
        }
        int find(int x) {
            if (x == parent[x]) return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }
        void union(int parentOfX, int parentOfY) {
            if (size[parentOfX] < size[parentOfY]) {
                size[parentOfY] += size[parentOfX];
                size[parentOfX] = 0;
                parent[parentOfX] = parentOfY;
            } else {
                size[parentOfX] += size[parentOfY];
                size[parentOfY] = 0;
                parent[parentOfY] = parentOfX;
            }
        }
    }

    public static void solution(int n, int m, Edge[] edges) {
        Union_find un = new Union_find(n);
        for (int i = 0; i < m; ++i) {
            int parentOfFrom = un.find(edges[i].from);
            int parentOfTo = un.find(edges[i].to);
            if (parentOfFrom != parentOfTo) {
                un.union(parentOfTo, parentOfFrom);
            } else {
                System.out.println(i + 1);
                return;
            }
        }
        System.out.println(0);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Edge[] edges = new Edge[m];
        for (int k = 0; k < m; ++k) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            edges[k] = new Edge(from, to);
        }
        solution(n, m, edges);
    }
}