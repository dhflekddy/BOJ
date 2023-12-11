/*
(그래프 탐색 문제는 아닌거 같다. 그냥 문제 이해력과 큐의 효용성을 강조하는 문제같음)

짝수시간에 채우고 홀수시간에 터뜨린다. 즉 짝수시간때는 채우는 함수를 호출하고 홀수시간때는 터뜨리는 함수를 호출한다.

채우는 함수에서는 채우는 좌표의 점을 큐에 넣는다.

-터뜨리는 함수에서는 큐에 있는 모든것을 반환하여 각 좌표와 그좌표의 4방향의 좌표를 모두 0만든다.

-채우는 함수에서는  0으로되있는 좌표를 모두 1로 만들면서 기존에 1인좌표는 모두 큐에 담는다.


-채운후 폭발한다


3초전에 설치된 폭탄을 폭발하라고 하지만 사실은 그 이전에 폭발이 한번일어났으므로 그 폭발로부터 남은 폭탄을
터뜨리면 되는 것이고 그것은 2초전에 폭발로 부터 남아있는 폭탄들이다. (문제 자체에 대한 이해력)

와... 큐의 효율성을 제대로 말해주는 문제다. 터지는 과정에서 주변께 터지면 정작 자신도 터져야 하는데 주변것이 먼
저 터지는 바람에 0으로 한끝 먼저 바뀌어버려서 자신은 정작터질수 없게 된다. 하지만 터뜨리기 이전에 큐에 담아놓으면 주변깨
터져도 큐에서 자신을 빼낸것은 무조건 터져야 하는 당위성을 가지므로 좌표에 구애받지 않고 비록 그 자리가 0이더라도
터질수 있는 것이다. (큐의 효용성에 대한 이해)
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Testing3 {
    static int r;
    static int c;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void fill(int[][] map, Queue<Point> que) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 0)
                    map[i][j] = 1;
                else
                    que.add(new Point(i, j));
            }
        }
    }

    public static boolean isValidForBombardment(Point p) {
        return (p.y >= 0 && p.y < r && p.x >= 0 && p.x < c);
    }

    public static void bombard(int[][] map, Queue<Point> que) {
        while (!que.isEmpty()) {
            Point p = que.poll();
            map[p.y][p.x] = 0;
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dy[i];
                int nx = p.x + dx[i];
                Point tempP = new Point(ny, nx);
                if (isValidForBombardment(tempP))
                    map[tempP.y][tempP.x] = 0;
            }
        }

    }

    public static void solution(int[][] map, int s) {
        Queue<Point> que = new LinkedList<>();
        while (s > 0) {
            fill(map, que);
            --s;
            if(s>0) {
                bombard(map, que);
                --s;
            }

        }
        while (s > 0) {
        }
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(map[i][j]==1)
                    sb.append('O');
                else
                    sb.append('.');
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }
//    public static void printMap(int[][]map){
//        for(int i=0;i<r;i++){
//            for(int j=0;j<c;j++){
//                System.out.print(map[i][j]+" ");
//            }
//            System.out.println();
//        }
//    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[][] map = new int[r][c];
        for (int i = 0; i < r; i++) {
            char[] arr = br.readLine().replace('.', '0').replace('O', '1').toCharArray();
            for (int j = 0; j < c; j++) {
                map[i][j] = arr[j] - '0';//폭탄은 1 빈칸은 0
            }
        }
        solution(map, s-1);
    }

}