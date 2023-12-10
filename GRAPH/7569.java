import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Testing3 {
    public static int m;//x
    public static int n;//y
    public static int h;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dz = {-1, 1};

    static class Point {
        int h;
        int n;
        int m;
        int day;
        Point(int h, int n, int m, int day) {
            this.h = h;
            this.n = n;
            this.m = m;
            this.day = day;
        }
    }

    public static boolean isValid(int[][][] arr, Point p) {
        return (p.n >= 0 && p.n < n && p.m >= 0 && p.m < m && p.h >= 0 && p.h < h && arr[p.h][p.n][p.m] == 0);
    }

    public static void solution(int[][][] arr, Queue<Point> que) {//bfs이용
        while (!que.isEmpty()) {
            Point cur = que.poll();
            for (int a = 0; a < 4; a++) {
                int ny = cur.n + dy[a];
                int nx = cur.m + dx[a];
                Point next = new Point(cur.h, ny, nx, cur.day + 1);
                if (isValid(arr, next)) {
                    que.add(next);
                    arr[next.h][next.n][next.m] = cur.day + 1;
                }
            }
            for (int i = 0; i < 2; i++) {
                int nz = cur.h + dz[i];
                Point next = new Point(nz, cur.n, cur.m, cur.day + 1);
                if (isValid(arr, next)) {
                    que.add(next);
                    arr[next.h][next.n][next.m] = cur.day + 1;
                }
            }
        }

        int answer = 0;
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; j++) {
                    if (arr[k][i][j] == 0) {
                        System.out.println(-1);
                        return;
                    } else if (arr[k][i][j] > answer)
                        answer = arr[k][i][j];
                }
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        int[][][] structure = new int[h][n][m];
        boolean init = false;
        Queue<Point> que = new LinkedList<>();
        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    structure[k][i][j] = Integer.parseInt(st.nextToken());
                    if (structure[k][i][j] == 0)
                        init = true;
                    if (structure[k][i][j] == 1)
                        que.add(new Point(k, i, j, 0));
                }
            }
        }
        if (!init) {
            System.out.println(0);
            return;
        }
        solution(structure, que);
    }
}