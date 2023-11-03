/*
나는 처음에 이 문제를 bool배열만을 이용해 풀려고 했다. 강의실은 bool배열에 대응한다. 그리고 해당시간 index가 true이면 그것은 새로운 강의실이 필요한 것이고 이로인해 새로운 강의실 boolean배열이 생성된다. 그리고 그 모든 강의실배열은 List로 관리한다. 따라서 다음 새로운 강의가 기존의 강의실에서 이루어지는 것이 가능한지 알아보기 위해서는 기존의 List에 담겨있는 모든 강의실 bool배열을 훓어봐야 한다. ==> 비효율적이다. 뭔가 아님을 느낀다.

문제특성. 그리디 문제로 매 순간 최적의 것을 선택해 주기 위해 주워진 데이터들을 시작시각이 작은것부터 오름차순으로 정렬한다. 문제 자체의 특별한 점은 우선순위 큐라는 자료구조의 도움을 받는 다는 것이다. 생각해보자. 어떨 때 우선순위 큐가 사용될수 있나? 최대한 추상화 해보자면, 이전의 것과 현재의 것을 비교하여 중첩이 일어나면 큐에서 빼지 않는다. 만약 중첩이 일어나지 않으면 큐에서 빼낸다....그리고 나중에 큐의 크기가 답이 된다... 그냥 매번 사례를 공부하여 익숙히 하는 방법 밖에는 없는 거 같다.

Sol)
우선 각 강의를 시작시각을 기준으로 오름차순으로 정렬한다. 만약 시작시각이 같다면 종료시각을 기준으로 오름차순으로 정렬해야 하나? 아니다 그럴필요 없다. 왜냐하면 비교대상은 이전 작업의 종료시각과 현재 작업의 시작시각을 비교하는 것이기 때문이다. 즉 비교의 대상이 종료시각과 현재시각인 것이다. 그리고 어차피 우선순위 큐에 들어가면 내부적으로 오름차순으로 정렬된다.

그후 가장 처음강의의 종료시각을 우선순위큐에 넣는다 그리고 반복문으로 다음강의부터 우선순위 큐와 비교대상으로 삼아 우선순위 큐의 이전강의 종료시각과 반복문으로 돌아가는 현재강의의 시작시각을 비교하여 이전강의의 종료시각이 현재강의의 시작시각보다 작으면 우선순위큐에서 빼낸후 현재강의의 종료시각을 우선순위 큐에 넣는다. 우선순위 큐에 넣으면 내부적으로 알아서 정렬이 일어나 다음강의의 시작시각과 이전강의중 종료시각이 가장 빠른 강의와 비교가 일어나고 만약 종료시각이 더 빠른강의가 있다면 그 강의를 큐에서 빼낸다(엄밀히는 종료시각을 빼내는 것이지만 개념적으로는 그 강의 자체를 빼는것이다. 그래서 답도 우선순위 큐에 남아있는 데이터의 수가 답인것이다). 이 과정을 반복하면 큐에 남아있는 데이터의 개수가 답이 된다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Class{
    private int start;
    private int end;
    public Class(int start, int end){
        this.start=start;
        this.end=end;
    }
    public int getStart(){
        return this.start;
    }
    public int getEnd(){
        return this.end;
    }
}
public class Testing3{
    public static void solution(Class[]works){
        PriorityQueue<Integer>pq=new PriorityQueue<>();
        pq.add(works[0].getEnd());
        for(int i=1;i< works.length;i++){
            // 우선순위 큐에서 가장 작은 종료 시간과
            // 현재 lectures[i]의 시작 시간을 비교한다.
            if(pq.peek()<=works[i].getStart())
                pq.poll();
            pq.add(works[i].getEnd());
        }
        // 현재 우선순위 큐에 남아있는 요소의 개수가 최소한으로 필요한 강의실 개수
        System.out.println(pq.size());
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        StringTokenizer st;
        Class[]classes=new Class[n];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            classes[i]=new Class(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        //시작 시간을 기준으로 오름차순 정렬
        Arrays.sort(classes, Comparator.comparing(Class::getStart));
        solution(classes);
        br.close();
    }
}