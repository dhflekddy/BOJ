/*
문제가 깔끔하지 않을수 있다. 여기서 깔끔하지 않다는 것은 어떠한 명확한 알고리즘을 묻는것도 아니고, 그 알고리즘을 감추어 놓은 것도 아니고, 알고리즘과 관련된 어떠한 참신한 생각을 떠올려야 풀리는 문제도 아닌 문제를 말한다. 하지만 이런 더러운 문제도 가치가 있다. 왜? 이러이러한 문제가 내가 알고있는 어떠한 해결법으로는 깔끔하게 풀리지 않는다는 것을 알려준다는 점에서 그러하다.

나는 이문제가 "최소 회의실 개수" 문제와 같이 우선순위 큐를 이용하여 풀릴줄 알았다. 그래서 그렇게 접근해 보았지만 예제 테스트를 모두 통과했지만 결국 오답이 나왔다. 코드는 상당히 지저분했다. 즉, 최소 회의실 문제는 PriorityQueue에 넣는 것도 회의객체이고 그 객체를 큐에서 빼서 비교하는 대상도 회의 객체이다. 하지만 이 문제는 다르다. 우선순위 큐를 사용한다면 우선순위 큐에 들어가는 것은 크래인이고 큐에 있는 크래인과 비교되는 것은 화물의 무개이다. 그러한 점에서 우선순위큐로는 이 문제를 깔끔하게 풀수 없는거같다.

아래는 우선순위큐를 이용한 오답코드이다. 예제는 모두 통과됨.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Testing3 {
    public static void solution(List<Integer> cranes, List<Integer> boxes) {
        int answer = 0;

        while (!boxes.isEmpty()) {
            int craneIdx = 0, boxIdx = 0;
            while (craneIdx < cranes.size()) {
                if (boxIdx == boxes.size())
                    break;
                else if (cranes.get(craneIdx) >= boxes.get(boxIdx)) {
                    boxes.remove(boxIdx);
                    ++craneIdx;
                } else
                    ++boxIdx;
            }
            ++answer;
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> crane = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            crane.add(Integer.parseInt(st.nextToken()));

        int M = Integer.parseInt(br.readLine());
        List<Integer> box = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            box.add(Integer.parseInt(st.nextToken()));

        //내림차순으로 정렬
        crane.sort(Collections.reverseOrder());
        box.sort(Collections.reverseOrder());

        if (crane.get(0) < box.get(0)) {
            System.out.println(-1);
            return;
        }
        solution(crane, box);
    }
}
