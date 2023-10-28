import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int dfs(List<Integer>[] graph, int me, int u, int answer, boolean[] visited){
        if(!visited[me]){
            if(me==u)
                return answer;
            visited[me]=true;
            for(var next:graph[me]){
                if(!visited[next]){
                    int value=dfs(graph, next, u,  answer+1, visited);
                    if(value!=-1)
                        return value;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int relations = Integer.parseInt(br.readLine());
//        int[][]graph=new int[total+1][total+1];
        List<Integer>[] graph = new LinkedList[total+1];
        for(int i=1;i<graph.length;i++)
            graph[i]=new LinkedList<>();



        for (int i = 0; i < relations; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }
        boolean[] visited = new boolean[total + 1];
        int answer=0;
        System.out.println(dfs(graph, start, end, answer, visited));
    }
}