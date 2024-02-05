import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Testing3{
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str=br.readLine();
        int[]arr=new int[26];
        Arrays.fill(arr, -1);
        for(int i=0;i<str.length();++i){
            int idx=str.charAt(i)-'a';
            if(arr[idx]==-1){
                arr[idx]=i;
            }
        }
        for(int i=0;i<26;++i){
            System.out.print(arr[i]+" ");
        }
    }
}