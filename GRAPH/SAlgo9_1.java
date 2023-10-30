import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Testing {

    public static class Edge{
        private int from;
        private int to;
        private int weight;
        public Edge(int from, int to, int weight){
            this.from=from;
            this.to=to;
            this.weight=weight;
        }

        public int getFrom(){
            return from;
        }
        public int getTo(){
            return to;
        }
        public int getWeight(){
            return weight;
        }
    }

    public static class UnionFind{
        private int[]size;
        private int[] parent;
        public UnionFind(int N){
            size=new int[N];
            parent=new int[N];

            for(int i=0;i<N;i++){
                size[i]=1;
                parent[i]=i;
            }
        }
        public int find(int id){
            if(id==parent[id])
                return id;
            parent[id]=find(parent[id]);
            return parent[id];
        }

        public boolean uninon(int id1, int id2){
            int u=find(id1);
            int v=find(id2);
            if(u!=v){
                if(size[u]>=size[v]){
                    size[u]+=size[v];
                    size[v]=0;
                    parent[v]=u;
                }
                else{
                    size[v]+=size[u];
                    size[u]=0;
                    parent[u]=v;
                }
                return true;
            }
            return false;
        }

    }
    public static int solution(int N, List<Edge>edges ){
        edges.sort(Comparator.comparing(Edge::getWeight));
        UnionFind uni=new UnionFind(N);
        int answer=0;
        int edgeCount=0;
        Iterator<Edge>it=edges.iterator();

        while(edgeCount<N-1){
            Edge e=it.next();
            if(uni.uninon(e.getFrom(), e.getTo())){
                answer+=e.getWeight();
                edgeCount+=1;
            }
        }
        return answer;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());
        StringTokenizer st=new StringTokenizer(br.readLine());
        int v=Integer.parseInt(st.nextToken());
        int e=Integer.parseInt(st.nextToken());
        int[]data= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Edge> edges=new LinkedList<>();
        int j=0;
        for(int i=0;i<e;i++)
            edges.add(new Edge(data[j++], data[j++], data[j++]));
        System.out.println(solution(v, edges));
    }

}