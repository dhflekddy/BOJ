import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
//
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<Job> jobs = new PriorityQueue<>(((o1, o2) -> o1.d - o2.d));

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            jobs.add(new Job(d, t));
        }

        int result = 0;
        int limit = 0;
        int workDay = 0;

        while (!jobs.isEmpty()) {

            Job job = jobs.poll();

            limit = job.d;
            limit -= (job.d / 7) * 2;  //평일

            if (job.d % 7 == 6) limit--;

            workDay += job.t;   //해야할 것추가

            if (workDay > limit) {    //해야할게 기간보다 많을 겨우
                result += workDay - limit;  //업무를 처리하고 남은 것들을 result에 추가함

                workDay = limit;    //처리한 것들은 workDay에 담아둬야함
            }

            if (result > job.d) { //마감기한보다 처리할게 더 많다면
                result = -1;
                break;
            }
        }
        System.out.println(result);
    }

    static class Job {
        int d; //마감기한
        int t; //걸리는시간

        public Job(int d, int t) {
            this.d = d;
            this.t = t;
        }
    }
}