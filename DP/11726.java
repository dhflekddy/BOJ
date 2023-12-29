import java.io.BufferedReader;
import java.io.InputStreamReader;
/*
최적해의 부분해가 또다시 그것의 부분문제의 최적해가 되는 점화식을 쉽게 찾을수 있어 하급문제다.

왜 이와 같은 점화식이 성립할까? 물론 일일히 다 그려가면 왜 가능한지 알수 있지만 글로 풀자면 애초부터(N=1, N=2일때)다른 모양의 타일을 쌓는데서 시작하므로 타일의 모형은 서로 겹치지 않게 되는 것이다.


이문제에 관련된 해설이 넘쳐나는데 그거 보고 있자면 혼동만 가중된다. 분명 나는 훗날에 이 문제를 다시 보고 다른 문제와 비교할 것이다. 그 때 문제들을 잘 정리하기 위해선 기준이 필요하다. 지금 생각하는 이 기준이 나중에도 맞다고 생각할지 모르겠지만 그래도 글로 남겨본다.

"부분해로 이루어지는 최적해의 점화식을 찾아야 한다. 그럼 부분해는 어떤, 어떤 수식으로 그려 질수 있는가?

이 모형이어야만 한다. 왜? 최적해의 부분해가 최적해로 만들어지는 블록이 1*2 아니면 2*1 두 가지의 경우만 가능하므로. 첫번째(1*2)는 n에서 2만큼적은 dp[n-2]에 1*2 타일을 위, 아래로 깐것이고 두번째((2*1)는 dp[n-1]에 2*1타일을 하나 깐것이다. dp[n-1]과 dp[n-2]는 그애초부터 다른 모양의 타일을 쌓는데서 시작하므로 결코 중복되지 않는다.
 */
public class Testing3{
    public static void solution(int n){
        int[]dp=new int[n+1];
        dp[1]=1;
        dp[2]=2;
        for(int i=3;i<=n;i++)
            dp[i]=(dp[i-1]+dp[i-2])%10007;
        System.out.println(dp[n]%10007);
    }
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        if(n<=2) {
            System.out.println(n);
            return;
        }
        solution(n);
    }
}