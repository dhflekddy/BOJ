import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{

    public static boolean canSum(int target, int[]arr){
        if(target==0)
            return true;
        if(target<0)
            return false;
        for(int i=0;i<arr.length;i++){
            if(canSum(target-arr[i], arr))
                return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        for(int i=0;i<t;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int target=Integer.parseInt(st.nextToken());
            int n=Integer.parseInt(st.nextToken());
            int[]arr=new int[n];
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++)
                arr[j]=Integer.parseInt(st.nextToken());
            System.out.println(canSum(target, arr));
        }
    }

}