import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    static class Edge{
        Edge(int start, int end, int weight){
            this.start=start;
            this.end=end;
            this.weight=weight;
        }
        int start;
        int end;
        int weight;
    }
    static final int INF=987654321;
    private static boolean bellman_ford(int node, List<Edge> edges, int departPos) {
        int[]dist=new int[node+1];
        Arrays.fill(dist, INF);
        dist[departPos]=0;
        boolean updated=false;
        for(int i=1;i<=node;i++){
            updated=false;
            for(var edge: edges){
                if(dist[edge.start]!=INF){
                    if(dist[edge.start]+edge.weight<dist[edge.end]){
                        updated=true;
                        dist[edge.end]=dist[edge.start]+edge.weight;
                        if(i==node){
                            return true;
                        }
                    }
                }
            }
            if(!updated)
                break;
        }
        return false;
    }

    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int tc=Integer.parseInt(br.readLine());
        List<Edge>edges=new ArrayList<>();
        StringTokenizer st;
        for(int t=0;t<tc;t++){
            st=new StringTokenizer(br.readLine());
            //node, edge, warmholl
            int node=Integer.parseInt(st.nextToken());
            int edge=Integer.parseInt(st.nextToken());
            int warmholl=Integer.parseInt(st.nextToken());

            for(int i=0;i<edge;i++){
                st=new StringTokenizer(br.readLine());
                int u=Integer.parseInt(st.nextToken());
                int v=Integer.parseInt(st.nextToken());
                int w=Integer.parseInt(st.nextToken());
                edges.add(new Edge(u,v,w));
                edges.add(new Edge(v,u,w));
            }
            for(int i=0;i<warmholl;i++){
                st=new StringTokenizer(br.readLine());
                int start=Integer.parseInt(st.nextToken());
                int end=Integer.parseInt(st.nextToken());
                int weight=Integer.parseInt(st.nextToken());
                edges.add(new Edge(start, end, -weight));
            }
            boolean result=false;
            for(int i=1;i<=node;i++){
                result=bellman_ford(node, edges, i);
                if(result){
                    System.out.println("YES");
                    break;
                }
            }
            if(!result)
                System.out.println("NO");
            edges.clear();

        }

    }


}