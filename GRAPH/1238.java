import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Testing3 {
    static final int INF = 987654321;

    static class Point implements Comparable<Point> {
        int point;
        int weight;

        Point(int point, int weight) {
            this.point = point;
            this.weight = weight;
        }

        @Override
        public int compareTo(Point p) {
            return weight - p.weight;
        }
    }

    public static int[] dijkstra(List<List<Point>> graph, int start) {
        int size = graph.size();
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(Point::getWeight));
        int[] dist = new int[size];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean[] visited = new boolean[size];
        pq.add(new Point(start, 0));
        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (!visited[cur.point]) {
                visited[cur.point] = true;
                for (var nextV : graph.get(cur.point)) {
                    if (dist[cur.point] + nextV.weight < dist[nextV.point]) {
                        dist[nextV.point] = dist[cur.point] + nextV.weight;
                        pq.add(new Point(nextV.point, dist[nextV.point]));
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        List<List<Point>> graph = new ArrayList<>(n + 1);
        List<List<Point>> reversedGraph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reversedGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Point(end, weight));
            reversedGraph.get(end).add(new Point(start, weight));
        }
        int[] dist = dijkstra(reversedGraph, x);
        int[] dist2 = dijkstra(graph, x);
        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            answer = Math.max(answer, dist[i] + dist2[i]);
        }
        System.out.println(answer);
    }
}