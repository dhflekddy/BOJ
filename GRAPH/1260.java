//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.PriorityQueue;
//import java.util.StringTokenizer;
//
//
//class Process implements Comparable<Process>{
//    int deadline;
//    int time;
//    public Process(int deadline, int time){
//        this.deadline=deadline;
//        this.time=time;
//    }
//
//    @Override
//    public int compareTo(Process o) {
//        return this.deadline-o.deadline;
//    }
//}
//public class Main{
//    public static int solution(PriorityQueue<Process>pq){
//        int workHour=0;
//        int answer=0;
//        while(!pq.isEmpty()){
//            Process p=pq.poll();
//            int deadline=p.deadline;
//            int time=p.time;
//            int n=0;
//            int renewedDeadline=deadline;
//            if(deadline/7>0)
//                n+=(deadline/7)*2;
//            if(deadline%7==6)
//                n+=1;
//            renewedDeadline-=n;
//
//            workHour+=time;
//            if(workHour>renewedDeadline){
//                answer+=(workHour-renewedDeadline);
//                workHour=renewedDeadline;
//            }
//            if(answer>deadline)
//                return -1;
//        }
//        return answer;
//    }
//    public static void main(String[] args)throws IOException {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        int num=Integer.parseInt(br.readLine());
//        StringTokenizer st;
//        PriorityQueue<Process>pq=new PriorityQueue<>();
//        for(int i=0;i<num;i++){
//            st=new StringTokenizer(br.readLine());
//            pq.add(new Process(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
//        }
//        System.out.println(solution(pq));
//
//    }
//
//
//}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static void dfs(List<Integer>[] graph, boolean[] visited, int start) {
        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);
            if (!visited[next]) {
                visited[next] = true;
                System.out.printf("%d ", next);
                dfs(graph, visited, next);
            }
        }
    }

    static void bfs(List<Integer>[] graph, int start) {
        boolean[] visited = new boolean[graph.length + 1];
        visited[start] = true;
        System.out.printf("%d ", start);
        Queue<Integer> que = new LinkedList<>();
        que.add(start);
        while (!que.isEmpty()) {
            start = que.poll();
            for (int i = 0; i < graph[start].size(); i++) {
                int next = graph[start].get(i);
                if (!visited[next]) {
                    System.out.printf("%d ", next);
                    que.add(graph[start].get(i));
                    visited[next]=true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        //무방향 희소이므로 인접리스트이용.
//        List<List<Integer>> graph=new LinkedList<>();//이 방법으로는 감이 안옴.
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i < n + 1; i++)
            graph[i] = new LinkedList<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }
        for (int i = 1; i < n + 1; i++)
            Collections.sort(graph[i]);

        boolean[] visited = new boolean[n + 1];
        System.out.printf("%d ", start);
        visited[start] = true;
        dfs(graph, visited, start);
        System.out.println();
        bfs(graph, start);
    }
}