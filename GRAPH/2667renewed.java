//   구현 포인트!
//   1.  visited 방문배열 생성
//   2.  Collection class에 요소로 배열을 넣고 그 각각의 배열에 y,x좌표를 넣는 방식이 익숙치 않았음.
//   3.  방문한좌표는 두번다시 방문하지 못하므로 어떤 점에 처음방문하게 되면 그것이 최단거리가 되고 그 점은 방문처리가 되어 불필요한 좌표값의 증가를 막는다

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void bfs(int[][] map, int y, int x) {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        int height = map.length;
        int width = map[0].length;
        boolean[][] visited = new boolean[height][width];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{y, x});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int curY = cur[0];
            int curX = cur[1];
            for (int i = 0; i < 4; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];
                if (nextY < 0 || nextY >= height || nextX < 0 || nextX >= width)
                    continue;
                if (map[nextY][nextX] == 0 || visited[nextY][nextX])//방문한좌표는 두번다시 방문하지 못하므로
                    //어떤 점에 처음방문하게 되면 그것이 최단거리가 되고 그 점은 방문처리가 되어 불필요한 좌표값의 증가를 막는다.
                    continue;
                visited[nextY][nextX] = true;
                map[nextY][nextX] = map[curY][curX] + 1;
                que.add(new int[]{nextY,nextX});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        bfs(map, 0, 0);
        System.out.println(map[n - 1][m - 1]);
    }

}
