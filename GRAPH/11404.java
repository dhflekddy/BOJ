//다익스트라=그래프 있어야 함. 벨만 포드: 그래프 없어도 됨. 플로이드. 그래프 있어야 함.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Testing3{
    static int[][]map;
    static final int INF=987654321;
    public static void floyd_warshall(int n){
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(i==j)
                        continue;
                    if(map[i][k]+map[k][j]<map[i][j])
                        map[i][j]=map[i][k]+map[k][j];
                }
            }
        }
    }
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        map=new int[n+1][n+1];
        for(int i=1;i<n+1;i++){
            Arrays.fill(map[i],INF);
        }

        for(int i=1;i<=m;i++){
            int[]arr=Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if(map[arr[0]][arr[1]]>arr[2])
                map[arr[0]][arr[1]]=arr[2];
        }
        floyd_warshall(n);
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(map[i][j]==INF)
                    sb.append(0+" ");
                else
                    sb.append(map[i][j]+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}