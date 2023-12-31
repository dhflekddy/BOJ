import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Testing3 {
    static class Brick implements Comparable<Brick> {
        public Brick(int n, int s, int h, int w) {
            this.n = n;
            this.s = s;
            this.h = h;
            this.w = w;
        }

        int n;
        int s;
        int h;
        int w;

        @Override
        public int compareTo(Brick o) {
            return this.w - o.w;
        }
    }

    /*
    dp[i]는 i번째 벽돌을 가장 아래두었을 때의 높이. 어떻게 이런 의미를 가질 수 있나? 우선은 bricks배열이 무게를 기준으로
    정렬되었다. 그리고 밑넓이 또한 bricks[i].s>bricks[j].s 를 만족해야 하므로 어디에도 i번째 벽돌을 가장 아래에 두게하는
    코드가 없지만 위와같은 정렬과 조건문을 통해 i번째 벽돌이 가장 아래에 두어진다고 한들 아무런 모순이 생기지 않기 때문에
    dp[i]가 i번째 벽돌을 가장 아래에 둔다는 것을 의미할 수도 있는 것이다. 사실은 오름차순으로 정렬되었다는것이 곧 가장
    아래에 둔다는 의미를 만들어 준다.
     */
    public static void solution(int n, Brick[] bricks) {
        Arrays.sort(bricks);
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (bricks[i].s > bricks[j].s) {
                    dp[i] = Math.max(dp[i], dp[j] + bricks[i].h);
                }
            }
        }

        int maxValue = -1;
        int i = n;

        while (i != 0) {
            if (dp[i] > maxValue) maxValue = dp[i];
            --i;
        }

        List<Integer> answer = new ArrayList<>();
        int index = n;
        while (index != 0) {
            if (maxValue == dp[index]) {
                answer.add(bricks[index].n);
                maxValue -= bricks[index].h;
            }
            --index;
        }

        int size=answer.size();
        System.out.println(size);
        for(int k=size-1;k>=0;--k)
            System.out.println(answer.get(k));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Brick[] arr = new Brick[n + 1];
        arr[0] = new Brick(0, 0, 0, 0);
        StringTokenizer st;
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            arr[i] = new Brick(i, s, h, w);
        }
        solution(n, arr);
    }
}