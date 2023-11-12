import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Process{
    private int deadline;
    private int duration;

    public Process(int deadline, int duration){
        this.deadline=deadline;
        this.duration=duration;
    }
    public int getDeadline() {
        return deadline;
    }

    public int getDuration() {
        return duration;
    }
}
public class Main{

    public static void deadlineScheduleWithDuration(PriorityQueue<Process>pq){
        //answer은 답인동시에 시간외 근무 일수
        int regularWork=0;
        int answer=0;
        while(!pq.isEmpty()){
            Process p=pq.poll();
            //어떻게 주말에는 정규근무가 가능하지 않은데 이렇게 코드를 짜서 통과가 될까?
            // deadline을 앞당김으로써 주말에도 정규근무가 가능하도록 한 것이다!!!

            int renewedDeadline=p.getDeadline();
            renewedDeadline-=(renewedDeadline/7)*2;
            if(p.getDeadline()%7==6)
                renewedDeadline--;
            regularWork+=p.getDuration();
            if(regularWork>renewedDeadline){
                answer+=(regularWork-renewedDeadline);
                regularWork=renewedDeadline;
            }
            if(answer>p.getDeadline()){
                answer=-1;
                break;
            }
        }
        System.out.println(answer);

    }

    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        int n=Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Process>pq=new PriorityQueue<>((a,b)->a.getDeadline()-b.getDeadline());
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int deadline=Integer.parseInt(st.nextToken());
            int duration=Integer.parseInt(st.nextToken());
            pq.add(new Process(deadline, duration));
        }
        deadlineScheduleWithDuration(pq);

    }

}

//위와 같이 Comparable 인터페이스를 구현하게 하지않고 PriorityQueue에 직접적으로 아래와 같이 정렬기준을 심어줄수 있다.
//        PriorityQueue<Process>pq=new PriorityQueue<>((a,b)->a.getDeadline()-b.getDeadline());