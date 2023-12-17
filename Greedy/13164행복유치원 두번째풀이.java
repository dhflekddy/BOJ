////방향 그래프인만큼 인접행렬로 해보기.
////visited배열 없이 해보기.
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.PipedInputStream;
//import java.util.*;
//
//public class Testing3 {
//    static final int INF = 987654321;
//
//    static class Point {
//        int v;
//        int weight;
//
//        Point(int v, int weight) {
//            this.v = v;
//            this.weight = weight;
//        }
//
//        int getWeight() {
//            return weight;
//        }
//    }
//
//    public static void solution(List<List<Point>> graph, int start) {
//        int size = graph.size();
////        boolean[] visited = new boolean[size + 1];
//        int[] dist = new int[size];
//        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(Point::getWeight));
//        Arrays.fill(dist, INF);
//        dist[start] = 0;
//        pq.add(new Point(start, 0));
////        !visited[nextV.v] &&
//
//        while (!pq.isEmpty()) {
//            Point cur = pq.poll();
////            if (!visited[cur.v]) {
////                visited[cur.v] = true;
//
//            for (var nextV : graph.get(cur.v)) {
//                if (dist[cur.v] + nextV.getWeight() < dist[nextV.v]) {
//                    dist[nextV.v] = dist[cur.v] + nextV.getWeight();
//                    pq.add(new Point(nextV.v, dist[nextV.v]));
//                }
//            }
////            }
//        }
//        for (int i = 1; i < size; i++) {
//            if (dist[i] != INF)
//                System.out.println(dist[i]);
//            else
//                System.out.println("INF");
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int e = Integer.parseInt(st.nextToken());
//        int start = Integer.parseInt(br.readLine());
//        List<List<Point>> graph = new ArrayList<>(n + 1);
//        for (int i = 0; i < n + 1; i++)
//            graph.add(new ArrayList<>());
//        for (int i = 0; i < e; i++) {
//            st = new StringTokenizer(br.readLine());
//            int u = Integer.parseInt(st.nextToken());
//            int v = Integer.parseInt(st.nextToken());
//            int w = Integer.parseInt(st.nextToken());
//            graph.get(u).add(new Point(v, w));
//        }
//        solution(graph, start);
//
//    }
//}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Testing3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        LinkedList<Integer> diff = new LinkedList<>();
        for (int i = 1; i < n; i++)
            diff.add(arr[i] - arr[i - 1]);
        Collections.sort(diff, Collections.reverseOrder());
        --k;
        while(k>0){
            diff.pollFirst();
            --k;
        }
        Optional<Integer>sum=diff.stream().reduce(Integer::sum);
        if(sum.isPresent())
            System.out.println(sum.get());
        else
            System.out.println(0);
    }
}