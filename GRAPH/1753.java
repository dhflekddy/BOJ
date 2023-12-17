/*
 * 이 문제는 다익스트라 알고리즘을 사용해야 하는 문제입니다. 이 알고리즘에 대해 들어보지 못했다면 먼저 찾아서 공부한 뒤에 푸시는 것을 추천드립니다.
벨만 포드, 플로이드 알고리즘 등은 사용할 수 없습니다. 알고리즘 문제는 단순히 답을 구하는 것만이 목적이 아니고, 그 답을 시간/메모리 측면에 대해 효율적으로 구하는 것까지도 포함합니다. 시간복잡도를 생각해 보세요. 벨만 포드는 O(VE), 플로이드는 O(V^3)으로 각각 60억, 8조에 해당하고, 컴퓨터가 초당 약 10억 회의 연산을 한다고 가정하면 각각 6초, 8000초가 소요됩니다. 또한 평균적으로 O(E)에 동작한다고 알려져 있는 SPFA 알고리즘 역시 최악의 경우는 O(VE)로 이전에 저격되었습니다. 우리는 단순하게 구현했을 때 O((V+E)logE)가 되는 다익스트라 알고리즘을 사용해야 합니다.
간선 정보는 인접 행렬이 아니라 인접 리스트로 저장해야 합니다. 인접 행렬을 사용하게 되면 2만 개의 정점에 대해 2만 개 정점으로 가는 간선들을 저장해야 하는데, 간선 하나당 1바이트에 저장한다고 하더라도 4억 바이트, 약 400MB의 메모리를 쓰게 될 뿐 아니라 시간복잡도 역시 O(V^2)으로 좋지 못하게 됩니다. 어떻게든 압축하면 통과는 시킬 수 있을지도  모르겠지만, 사서 고생입니다. 현재 정점에 연결된 간선들의 번호만 리스트로 저장해서 사용하는 것이 좋습니다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.util.*;

public class Testing3 {
    static final int INF = 987654321;

    static class Point {
        int v;
        int weight;

        Point(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        int getWeight() {
            return weight;
        }
    }

    public static void solution(List<List<Point>> graph, int start) {
        int size = graph.size();
        boolean[] visited = new boolean[size];
        int[] dist = new int[size];
        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(Point::getWeight));
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.add(new Point(start, 0));

        while (!pq.isEmpty()) {
            Point cur = pq.poll();
            if (!visited[cur.v]) {
                visited[cur.v] = true;

                for (var nextV : graph.get(cur.v)) {
                    if (dist[cur.v] + nextV.getWeight() < dist[nextV.v]) {
                        dist[nextV.v] = dist[cur.v] + nextV.getWeight();
                        pq.add(new Point(nextV.v, dist[nextV.v]));
                    }
                }
            }
        }
        for (int i = 1; i < size; i++) {
            if (dist[i] != INF)
                System.out.println(dist[i]);
            else
                System.out.println("INF");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        List<List<Point>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Point(v, w));
        }
        solution(graph, start);

    }
}
