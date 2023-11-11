import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
DP단원에 속해있지만 문제를 보고나서 가장 먼저 떠오른건 탐욕적 알고리즘이었다. 이후 DP로 풀려했지만 시중에도 그렇고 깔끔한 풀이가
존재하지 않았다...
 */
public class Main{

    public static int solution(int n){
        int a;
        if(n==0)
            return 0;

        if(n<3)
            return -1;
        if(n>=5) {
            a = solution(n - 5);
            if(a>=0)
                return a+1;
        }
        if(n>=3){
            a=solution(n-3);
            if(a>=0)
                return a+1;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        System.out.println(solution(n));
    }
}