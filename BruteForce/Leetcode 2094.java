import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Testing3 {
    public static void solution(int[] frequency, int num, int size, List<String> answer) {
        if (size == 3) {
            answer.add(String.valueOf(num));
            return;
        }
        int step = (size == 2) ? 2 : 1;//size가 2라는 것은 일의자리를 뽑는다는 것이다. 짝수이어야 하므로 일의자리를 뽑으면 step=2가 되고
        //일의자리가 아니면 step=1로써 아래 반복문에서 원래대로 1씩 증가하게 된다. 반복문안의 증감식이 저런형태로 온다는 것도 눈여겨 봐놓자.
        for (int i = (size == 0) ? 1 : 0; i < 10; i+=step) {//size가 0이라는 것은 백의 자리를 뽑는다는 것으로 0이 되면 안되므로 빈도수배열이
            //i는 1일때부터 시작하는 것으로 설정한 것이다.
            if(frequency[i]==0)continue;
            --frequency[i];
            solution(frequency, 10*num+i, size+1, answer);
            ++frequency[i];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; ++t) {
            int n = Integer.parseInt(br.readLine().strip());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] frequency = new int[10];
            for (var ele : arr) ++frequency[ele];
            List<String> answer = new ArrayList<>();
            solution(frequency, 0, 0, answer);
            System.out.println(answer.stream().collect(Collectors.joining(" ")));
        }
    }
}
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class Testing3 {
//    public static void solution(int[] freq, List<String> answer) {
//        int[]arr;
//        for(int i=100;i<999;i+=2){
//            arr=new int[10];
//            int temp=i;
//            for(int j=0;j<3;++j){
//                ++arr[temp%10];
//                temp=temp/10;
//            }
//            boolean discri=true;
//            for(int k=0;k<10;++k){
//                if(arr[k]>freq[k]){
//                    discri=false;
//                    break;
//                }
//            }
//            if(discri)answer.add(String.valueOf(i));
//        }
//        System.out.println(answer.stream().collect(Collectors.joining(" ")));
//    }
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int T = Integer.parseInt(br.readLine());
//        for (int t = 0; t < T; ++t) {
//            int n = Integer.parseInt(br.readLine().strip());
//            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//
//            int[] freq = new int[10];
//
//            for (var v : arr) ++freq[v];
//
//            List<String> answer = new LinkedList<>();
//            solution(freq,answer);
//        }
//    }
//}
//
