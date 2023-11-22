/*
  Point
    1.큐에 좌표와 함께 여태까지 왔던 경로의 길이를 함께 저장해 주는것.
    1. visitedMap의 boolean 배열을 두어 방문했던 곳을 기록하여 재방문을 막아 시간을 단축한다. 
 */

import java.beans.Visibility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    static class Pos {
        int y;
        int x;
        int distance;

        public Pos(int y, int x, int distance) {
            this.y = y;
            this.x = x;
            this.distance = distance;
        }
    }

    public static void solution(int[][] map) {
        int maxY = map.length;
        int maxX = map[0].length;
        boolean[][] visited = new boolean[maxY][maxX];
//       int answer=0;
        Queue<Pos> que = new LinkedList<>();
        que.add(new Pos(0, 0, 1));
        while (!que.isEmpty()) {
            Pos p = que.poll();
            int y = p.y;
            int x = p.x;
            int distance = p.distance;
            if (y == maxY - 1 && x == maxX - 1) {
                System.out.println(distance);
                break;
            }
            if (maxY > y && maxX > x && x >= 0 && y >= 0)
                if (map[y][x] == 1 && ! visited[y][x]) {
                    visited[y][x] = true;
                    que.add(new Pos(y + dy[0], x + dx[0], distance + 1));
                    que.add(new Pos(y + dy[1], x + dx[1], distance + 1));
                    que.add(new Pos(y + dy[2], x + dx[2], distance + 1));
                    que.add(new Pos(y + dy[3], x + dx[3], distance + 1));
                }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++)
            map[i] = Arrays.stream(br.readLine().strip().split("")).mapToInt(Integer::parseInt).toArray();
        int answer = 0;
        solution(map);

    }
}