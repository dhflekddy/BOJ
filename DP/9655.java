/*
DP쓸필요 없이 그냥 단순히 규칙만 찾으면 끝나는 문제다.
"

n = 1 : 돌이 1개가 있으면 상근이가 1개를 가져가면 끝나므로 상근이가 이긴다.

n = 2 : 처음에 상근이가 1개를 가져가고 창영이가 남은 1개를 가져가면 되므로 창영이가 이긴다.

n = 3 : 상근이가 처음에 돌 3개를 가져가면 끝나므로 상근이가 이긴다.

n = 4 : 상근이가 돌 1개를 가져가든, 3개를 가져가든 돌이 1개나 3개가 남으므로 창영이가 이긴다.

n = 5 : 상근이가 처음에 돌 1개를 가져갈 수 도 있고 3개를 가져갈 수도 있다.

→ 1개를 가져갔을 경우 : 4개가 남고 창영이가 1개를 가져가든 3개를 가져가든 1개나 3개가 남으므로 상근이가 이긴다.

→ 3개를 가져갔을 경우 : 2개가 남고 창영이가 1개만 가져갈 수 있으므로 상근이가 이긴다.

"
즉 돌이 홀수개면 상근이가 이기고 짤수개면 창영이가 이긴다.


DP문제는 우선은 어떻게든 규칙성을 찾으려 하여 점화식을 찾으려 해야하고 그 규칙이 간단하면 아래와 같이 DP테이블을 쓰지 않아도 된다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void solution(int n){
        if(n%2==0)
            System.out.println("CY");
        else
            System.out.println("SK");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        solution(n);



    }
}