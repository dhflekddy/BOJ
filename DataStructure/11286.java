//////문제를 읽고 "어떻게 이 문제를 탐욕적 알고리즘과 연관시켜 멋지게 해결할 수 있을까?"라고 생각했다. 도저히 염두가 나지 않아 바로 답을 보았다. 해결의 키는 주워진 수의 조로 분할 한다는 것이고 이는 각각의 원생들 사이 중에서 어느곳에다 분별의 막대기를 둘지 선택하는 문제임을 인지하는데 있다.
//////        어디다 막대기를 두어야 하는가? 원생들은 서로 인접해야 한다고 했으므로(이게 정말 중요함) 원생들의 키차이가 가장 많은 곳에 막대기를 두어 다른조로 구분해 내면 된다. 그렇게 되면  그 큰 키차이가 없어진다.
//////
//////        즉, 원생들의 키의 차이를 모두 구한 자료구조 리스트를 구하고 그 리스트를 오름차순 정렬한다.그 후
//////z
//////        height.length-(groups-1)를 구한후 그보다 1더 작은 위치까지 모두 더해 주면 된다(아래코드 참고).
//////
//////        int until=height.length-(groups-1);
//////        for(int i=0;i<until-1;i++)
//////        answer+= gap.get(i);
//////
//////        K-1(K는 조의개수)개를 0으로 만들고 나머지 배열의 인자들을 모두 더하면 그것이 답인 것이다.
//////
//////        재밌는 것이 LinkedList를 쓰면 시간 초과가 나고 ArrayList를 스면 통과가 되었다. 왜 그럴까? 리스트를 대상으로한 get, sort때문일것이다. Array리스트같은 경우 get할때 한큐에 바로 가지만 Linked리스트 같은 경우 모두 통과해야 한다. sort또한 정렬하는데 있어 Array리스트가 훨씬 빠르다.
////
////
////
//////
//////import java.io.BufferedReader;
//////import java.io.IOException;
//////import java.io.InputStreamReader;
//////import java.util.*;
//////
//////public class Testing2{
//////
//////    public static void solution(int[] height, int groups){
//////        List<Integer> gap=new ArrayList<>();
//////        for(int i=1;i< height.length;i++)
//////            gap.add(height[i]-height[i-1]);
//////        Collections.sort(gap);
////////        int answer=0;
////////        int until=height.length-(groups-1);
////////        for(int i=0;i<until-1;i++)
////////            answer+= gap.get(i);
////////        System.out.println(answer);
//////        int until=groups-1;
//////        int last;
//////        while(until>0){
//////            last=gap.size();
//////            gap.remove(gap.size()-1);
//////            until-=1;
//////        }
//////        Optional<Integer>answer=gap.stream().reduce((a,b)->a+b);
//////        if(answer.isEmpty())
//////            System.out.println(0);
//////        else
//////            System.out.println(answer.get());
//////    }
//////    public static void main(String[] args) throws IOException {
//////        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//////        StringTokenizer st=new StringTokenizer(br.readLine());
//////        int n=Integer.parseInt(st.nextToken());
//////        int groups=Integer.parseInt(st.nextToken());
//////        //이미배열이 되어있는 상태라면 정렬하기 위해 stream라이브러리를 사용할 필요는 없다. 단,
//////        //배열을 만들어 주기 위해서 아래와같이 stream라이브러리를 사용하면 편리하게 한 줄에 한큐에 입력받은
//////        // 데이터를 가지고 배열을 만들수 있다.
//////        int[]height= Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//////        solution(height, groups);
//////    }
//////}
//////
////
////import java.io.BufferedReader;
////import java.io.InputStreamReader;
////import java.util.ArrayList;
////import java.util.Collections;
////import java.util.StringTokenizer;
////
////public class Testing3 {
////
////    public static void main(String[] args) throws Exception {
////        // TODO Auto-generated method stub
////        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////
////        int n = Integer.parseInt(br.readLine());
////
////        ArrayList<Brick> list = new ArrayList<>();
////        list.add(new Brick(0, 0, 0, 0));
////        for (int i = 0; i < n; i++) {
////            StringTokenizer st = new StringTokenizer(br.readLine());
////            int area = Integer.parseInt(st.nextToken());
////            int height = Integer.parseInt(st.nextToken());
////            int weight = Integer.parseInt(st.nextToken());
////
////            list.add(new Brick(i + 1, area, height, weight));
////        }
////
////        // 무게 기준으로 정렬하기
////        Collections.sort(list);
////
////        int[] dp = new int[n + 1];
////
////        for (int i = 1; i <= n; i++) {
////            for (int j = 0; j < i; j++) {
////                if (list.get(i).area > list.get(j).area)
////                    dp[i] = Math.max(dp[i], dp[j] + list.get(i).height);
////            }
////        }
////
////        int maxHeight = -1;//특정벽돌을 가장 아래 두었을 때의 최대높이
////
////        for (int i = 0; i <= n; i++) {
////            if (maxHeight < dp[i]) maxHeight = dp[i];
////        }
////
////        int index = n;
////        ArrayList<Integer> result = new ArrayList<>();
////
////        while (index != 0) {
////            if (maxHeight == dp[index]) {
////                result.add(list.get(index).id);
////                maxHeight -= list.get(index).height;
////            }
////            index--;
////        }
////
////        System.out.println(result.size());
////        for (int i = result.size() - 1; i >= 0; i--) {
////            System.out.println(result.get(i));
////        }
////    }
////}
////
////class Brick implements Comparable<Brick> {
////    int id;
////    int area;
////    int height;
////    int weight;
////
////    public Brick(int id, int area, int height, int weight) {
////        this.id = id;
////        this.area = area;
////        this.height = height;
////        this.weight = weight;
////    }
////
////    @Override
////    public int compareTo(Brick o) {
////        return this.weight - o.weight;
////    }
////}
//
//import java.util.ArrayList;
//import java.util.List;
//
//class Solution {
//    public:
//    void solve(vector<int>& digits, vector<int>& vis, map<int, int>& mp, int tmp){
//        if(tmp >= 100){
//            if((tmp&1) == 0) mp[tmp]++;
//            return;
//        }
//        for(int i=0; i<digits.size(); i++){
//            if(vis[i] == 0){
//                vis[i] = 1;
//                solve(digits, vis, mp, digits[i] + (tmp * 10));
//                vis[i] = 0;
//            }
//        }
//    }
//    vector<int> findEvenNumbers(vector<int>& digits) {
//        int cnt = 0;
//        for(auto i : digits) if((i&1) == 0) cnt++;
//        if(cnt == 0) return {};
//
//        vector<int> ans;
//        map<int, int> mp;
//        vector<int> vis(digits.size(), 0);
//        for(int i=0; i<digits.size(); i++){
//            vis[i] = 1;
//            if(digits[i] != 0) solve(digits, vis, mp, digits[i]);
//            vis[i] = 0;
//        }
//
//        for(auto i : mp) ans.push_back(i.first);
//
//        return ans;
//    }
//};


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Testing2{
    static class Element{
        int original;
        int absValue;
        public Element(int original){
            this.original=original;
            this.absValue=Math.abs(original);
        }
        public int getOriginal(){
            return original;
        }
        public  int getAbsValue(){
            return absValue;
        }
    }

    public static void solution(int[]arr, int n){
        PriorityQueue<Element>pq=new PriorityQueue<>(Comparator.comparing(Element::getAbsValue).thenComparing(Element::getOriginal));
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;++i){
            if(arr[i]!=0)
                pq.add(new Element(arr[i]));
            else{
                if(pq.isEmpty())
                    sb.append("0").append("\n");
                else
                    sb.append(pq.poll().original+"").append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args)throws Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());
        int[]arr=new int[n];
        for(int i=0;i<n;++i)
            arr[i]=Integer.parseInt(br.readLine());
        solution(arr,n);


    }
}