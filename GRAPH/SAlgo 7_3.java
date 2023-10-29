import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static final int INF=987564321;
    public static class Node{
        private int node;
        private int weight;
        public Node(int node, int weight){
            this.node=node;
            this.weight=weight;
        }
        public int getWeight(){
            return weight;
        }
    }
    public static int[] solve(List<List<Node>>graph, int start, int[]des){
        int[] answer=new int[des.length];
        int []result=dijkstra(graph, start);
        for(int i=0;i<des.length;i++){
            if(result[des[i]]==INF)
                answer[i]=-1;
            else
                answer[i]=result[des[i]];
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int[] graphInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int V = graphInfo[0];
            int E = graphInfo[1];
            int S = graphInfo[2];
            int N = graphInfo[3];
            int[] dest = new int[N];
            System.arraycopy(graphInfo, 4, dest, 0, N);
            List<List<Node>> graph = new ArrayList<>(V);
            for (int i = 0; i < V; i++)
                graph.add(new ArrayList<Node>());
            int[] edgeInfo = null;
            if (E == 0) br.readLine();
            else edgeInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int j = 0;
            for (int i = 0; i < E; i++) {
                int from = edgeInfo[j++];
                int to = edgeInfo[j++];
                int weight = edgeInfo[j++];

                graph.get(from).add(new Node(to, weight));
            }
            int[] ret = solve(graph, S, dest);
            System.out.println(Arrays.stream(ret).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
    public static int[] dijkstra(List<List<Node>> graph, int start){
        PriorityQueue<Node>que=new PriorityQueue<>(Comparator.comparingInt(Node::getWeight));
        int size=graph.size();
        boolean[]visited=new boolean[size];
        int[]distance=new int[size];
        Arrays.fill(distance,INF);
        distance[start]=0;
        que.add(new Node(start, 0));
        for(int i=0;i<size;i++){
            while(!que.isEmpty()){
                Node cur=que.poll();
                if(visited[cur.node])continue;
                visited[cur.node]=true;
                for(var nextN:graph.get(cur.node)){
                    if(!visited[nextN.node] && distance[cur.node]+nextN.getWeight()<distance[nextN.node]){
                        distance[nextN.node]=distance[cur.node]+nextN.getWeight();
                        que.add(new Node(nextN.node, distance[nextN.node]));
                    }
                }
                break;
            }
        }
        return distance;
    }


}