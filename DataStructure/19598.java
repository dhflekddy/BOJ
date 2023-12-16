
/*

문제를 다시 풀때마다 새로운 회의를 이미 강의실을 차지하고 있는 모든 회의들의 마감시간과 비교해야하는 것 아니냐는 게으른 사고에 빠지곤 한다!!!
아래는 이에 대한 답이다!

모든 것과 비교해야 하는 경우 이러한 번거로움을 없애줄 수 있는 것이 우선순위 큐이다. 왜 그런가? 사실은 모든 것과 비교할 필요가 없고
 그 중 가장 크거나 혹은 가장 작은 것과의 비교만 이루어 지면 되기때문이다.
배열에 시작시간이 가장 이른 순서로 배열을 정렬하고 그 배열의 첫번째 인자의 마감시간을 우선순위 큐에 넣는다. 그리고 두번째인자부터 그
시작시간을 우선순위큐의 인자와 비교만 하면 되는 것이다. 만약 우선순위 큐의 값이 더 작다면 pop하고 2번째 회의의
 마감시간을 큐에 넣으면 되고 만약 우선순위 큐의 값이 더 크다면 pop하지 않고 우선순위 큐에 넣으면 된다. 그러면 우선순위 큐는
  모든 값을 가지고 자동으로 마감시간이 가장 작은 값을 다음에 peek할때 반환해 주기 때문에 배열의 각 회의의
  시작시간을 기존의 우선순위 큐안에 들어있는 모든 회의의 마감시각과 비교하지 않아도 되는 것이다. 왜? 우선순위큐는 그 안에서 가장 작은 값을
   peek의 결과로 주며 사실은 모든 것과의 비교가 아닌 가장작은 값과의 비교만이 내가 필요로 하는 것이기 때문이다.
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Testing3{
    static class Meeting{
        int start;
        int end;
        Meeting(int start, int end){
            this.start=start;
            this.end=end;
        }
        int getStart(){
            return start;
        }
        int getEnd(){
            return end;
        }
    }
    public static void solution(Meeting[]meetings){
        int size=meetings.length;
        PriorityQueue<Integer>pq=new PriorityQueue<>();
        pq.add(meetings[0].getEnd());

        for(int i=1;i<size;i++){
            int st=meetings[i].getStart();
            if(pq.peek()<=st)
                pq.poll();
            pq.add(meetings[i].getEnd());
        }

        System.out.println(pq.size());
    }
    public static void main(String[] arg)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n=Integer.parseInt(br.readLine());
        Meeting[]meetings=new Meeting[n];
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(meetings, Comparator.comparingInt(Meeting::getStart));
        solution(meetings);
    }

}