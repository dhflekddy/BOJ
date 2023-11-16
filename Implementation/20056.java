import java.sql.Array;
import java.util.*;
import java.io.*;

public class Main {
    //파이어볼 정보 클래스
    static class meteor{
        int r, c, m, s, d;
        public meteor(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};	//방향 r값 변경값
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};	//방향 c값 변경값
    static ArrayList<meteor>[][] map;	//맵상에 존재하는 파이어볼정보. 각각의 격자의 위치로 이동한 파이어볼에 대한 정보를 유지
    static ArrayList<meteor> meteors = new ArrayList<>();	//존재하는 모든 파이어볼에 대한 정보
    //기본적으로 처음시작할때 map에는 아무것도없고 오직 파이어볼에 대한 정보를 알려주는 meteors에만 파이어볼이 있다. 그 후 이동을 할시
    //map에 파이어볼에 관한 정보가 생긴다. 이후 각map의 각 격자를 뒤져 2개이상이면 분할을 하는데 분할을 당하는 파이어볼은 meteors, map두 자료구조에서 모두
    //없어진다. 분할되어 새로 생성된 파이어볼은 meteors에만 존재하고 아직 map에는 표시하지 않는다. 다음이동 시에 표시한다.

    /*
  맵상에 존재하는 파이어볼정보, 각각의 격자의 위치로 이동한 파이어볼에 대한 정보를 유지하는 2차원 구조체 배열 필요함(ArrayList<Fireball>[][]map).
    위의 2차원 구조체 배열과는 별개로 존재하는 모든 파이어볼에 대한 정보를 유지하는 자료구조 필요함(ArrayList<Fireball> fireballs).

    기본적으로 처음시작할때 map에는 아무것도없고 오직 파이어볼에 대한 정보를 알려주는 fireballs에만 파이어볼이 있다. 그 후 이동을 할시
    map에 파이어볼에 관한 정보가 생긴다. 이후 각map의 각 격자를 뒤져 2개이상이면 분할을 하는데 분할을 당하는 파이어볼은 fireballs, map두 자료구조에서 모두
    없어진다. 분할되어 새로 생성된 파이어볼은 fireballs에만 존재하고 아직 map에는 표시하지 않는다. 다음이동 시에 표시한다.
    당위적으로 생각해보자. 왜 fireballs라는 자료구조가 필요하나? 주인공이 파이어볼이므로 파이어볼을 관리할 자료구조가 필요하다고 생각할 수 있다. 그리고 그 자료구조는
    어떤 순서에 상관없이(파이어볼의 위치정보가 있지만 그것은 구조체 안에 들어가있는 정보다)보관되어도 되므로 Set을 쓸수 있다(답지는 ArrayList를 사용했으니 내가 직접Set을 사용해 볼것).

    왜 2차원 구조체 배열 자료구조가 필요할까? 파이어볼이 분열하고 흩어지고 난리부르스 치는 이동하는 파이어볼을 관리해야 하므로.

    ==> 이후 난리부르스치는 파이어볼을 어떻게 자료구조에 차곡차곡 관리할지는 구현력에 해당하는 것이다. 절차를 잘 구현해야 한다.
    이동한다(이동한 격자에맞는 map의 인덱스에 파이어볼이 담긴다) -> 분열한다(격자에 있는 파이어볼중 그갯수가 1개면 map자료구조에서는 없어진다. 왜? map은 오직 이동하는 파이어볼만 관리하므로.
     2개면 각각의 파이어볼이 갖는 세부값들을 합산하여 계산하면서 map자료구조에서 없앤다. 왜? 마찬가지로 map은 오직 이동하는 파이어볼만 관리하므로 이동에 필요한 알맹이만 챙기고
    map은 다시 새것처럼 보존시켜 주어야 하기 때문이다.그리고 fireballs에서도 없앤다. 왜? 분열하여 없어진것과 같으므로. 대신 새롭게 분열되어 생성된 파이어볼을 fireballs에 담는다.
    이렇게 새롭게 분열하여 생성된 파이어볼은 fireballs에만 존재하고 map에는 아직 존재하지 않는상태가 된다. 즉 분열이 모두 끝나면 map은 비어있다.)

    단순하게 보면 위처럼 이동, 분열과정을 반복하는 것이다. 내가 익힐것은 객체의 이동에 주안점을 둔 문제에서 주인공객체가 이동하면 그 이동을 관리하는 자료구조(map)를 따로 만들고 이동과 별개로 전체 객체를
    관리하는 자료구조를 따로 두어야 한다는 것이다.
    */

    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                map[i][j] = new ArrayList<>();
        }
        //입력되는 파이어볼 정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            meteors.add(new meteor(r, c, m, s, d));
        }
        //K번 이동명령 진행
        for (int i = 0; i < K; i++) {
            meteorMove(N);
            meteorFire(N);
        }
        bw.write(meteorCal() + "");	//메테오 질량의 합 BufferedWriter 저장
        bw.flush();		//결과 출력
        bw.close();
        br.close();
    }
    //파이어볼 이동시키는 함수
    static void meteorMove(int N) {
        for (meteor cur : meteors) {
            //r, c값 변경
            // +N을 하는 이유는 이동하였을 때 음수가 나올 수 있기 때문입니다.
            int tempR = (cur.r + N + dr[cur.d] * (cur.s%N)) % N;
            int tempC = (cur.c + N + dc[cur.d] * (cur.s%N)) % N;
            cur.r = tempR;
            cur.c = tempC;
            //이동한 파이어볼 저장
            map[cur.r][cur.c].add(cur);
        }
    }
    //파이어볼 분열 진행
    static void meteorFire(int N){
        for(int r = 0; r<N;r++){
            for(int c = 0; c<N;c++) {
                //파이어볼 개수가 2개 미만일 때
                if(map[r][c].size() < 2){
                    map[r][c].clear();//어차피 모든 파이어볼에 대한 정보는 meteors에 저장되므로 여기서 map을 clear해도 상관없음.
                    continue;
                }
                //파이어볼 2개 이상일 때
                int mSum = 0, sSum = 0, oddCount = 0, evenCount = 0;
                int size = map[r][c].size();
                //분열 진행!
                for(meteor cur : map[r][c]){
                    mSum += cur.m;	//질량 더하기
                    sSum += cur.s;	//속도 더하기
                    if(cur.d % 2 == 1)	//방향 홀수일 때
                        oddCount++;
                    else		//방향 짝수일 때
                        evenCount++;
                    meteors.remove(cur);	//분열될 파이어볼 제거!
                }
                map[r][c].clear();
                mSum /= 5;	//분열될 질량 구하기
                if(mSum == 0)	//분열될 질량이 0이면 분열 실패!
                    continue;
                sSum /= size;	//분열될 속도 구하기
                //모든 파이어볼 방향이 짝수이거나 홀수일 때
                if(oddCount == size || evenCount == size){
                    for(int i=0;i<8;i+=2)	//{0, 2, 4, 6} 방향 분열
                        meteors.add(new meteor(r,c,mSum, sSum, i));
                }else{
                    for(int i=1;i<8;i+=2)	//{1, 3, 5, 7} 방향 분열
                        meteors.add(new meteor(r,c,mSum, sSum, i));
                }
            }
        }
    }
    //파이어볼 질량의 합 구하는 함수
    static int meteorCal(){
        int result = 0;
        //모든 질량 더하기!
        for(meteor cur : meteors)
            result += cur.m;
        return result;
    }
}