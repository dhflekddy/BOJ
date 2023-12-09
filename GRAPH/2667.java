import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Testing3 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int size;

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static boolean isValid(int[][] graph, boolean[][] visited, int i, int j) {
        return (i >= 0 && i < size && j >= 0 && j < size && !visited[i][j] && graph[i][j] == 1);
    }

    public static int bfs(int[][] graph, boolean[][] visited, Point p, List<Integer> answer) {
        int result = 0;
        Queue<Point> que = new LinkedList<>();
        que.add(p);
        ++result;
        visited[p.y][p.x]=true;
        while (!que.isEmpty()) {
            Point cur = que.poll();
            for (int i = 0; i < 4; ++i) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (isValid(graph, visited, ny, nx)) {
                    que.add(new Point(ny, nx));
//                    System.out.println("더해지는 좌표:"+ny+", "+nx);
                    ++result;
                    visited[ny][nx] = true;
                }
            }
        }
        return result;
    }

    public static void solution(int[][] graph) {
        List<Integer> answer = new ArrayList<>();
        size = graph.length;
        boolean[][] visited = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    Point p = new Point(i, j);
                    answer.add(bfs(graph, visited, p, answer));
                }
            }
        }
        Collections.sort(answer);
        System.out.println(answer.size());
        answer.stream().forEach(System.out::println);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][n];
        char[] arr;
        for (int i = 0; i < n; i++) {
            arr = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                graph[i][j] = arr[j] - '0';
            }
        }
        solution(graph);
    }
}