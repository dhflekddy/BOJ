import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Testing2{
    public static int solution(int value, int answer){
        if(value<0)
            return -1;
        if(value==0)
            return answer;
        int result=solution(value-5, answer+1);
        if(result!=-1)
            return result;
        result=solution(value-2, answer+1);
        if(result!=-1)
            return result;
        return -1;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int answer=0;
        System.out.println(solution(n, answer));
    }
}