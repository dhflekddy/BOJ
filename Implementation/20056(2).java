import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

    /*
  맵상에 존재하는 파이어볼정보, 각각의 격자의 위치로 이동한 파이어볼에 대한 정보를 유지하는 2차원 자료구조 배열 필요함(ArrayList<Fireball>[][]map).
    위의 2차원 구조체 배열과는 별개로 존재하는 모든 파이어볼에 대한 정보를 유지하는 자료구조 필요함(ArrayList<Fireball> fireballs).

    기본적으로 처음시작할때 map에는 아무것도없고 오직 파이어볼에 대한 정보를 알려주는 fireballs에만 파이어볼이 있다. 그 후 이동을 할시
    map에 파이어볼에 관한 정보가 생긴다. 이후 각map의 각 격자를 뒤져 2개이상이면 분할을 하는데 분할을 당하는 파이어볼은 fireballs, map두 자료구조에서 모두
    없어진다. 분할되어 새로 생성된 파이어볼은 fireballs에만 존재하고 아직 map에는 표시하지 않는다. 다음이동 시에 표시한다.
    당위적으로 생각해보자. 왜 fireballs라는 자료구조가 필요하나? 주인공이 파이어볼이므로 파이어볼을 관리할 자료구조가 필요하다고 생각할 수 있다. 그리고 그 자료구조는
    어떤 순서에 상관없이(파이어볼의 위치정보가 있지만 그것은 구조체 안에 들어가있는 정보다)보관되어도 되므로 Set을 쓸수 있지 않을까 생각할 수도 있지만 fireballs자료구조안에 반복문을 통해
    하나하나 꺼내는 과정이 들어가 있으므로 리스트 자료구조를 사용하는 것이 자연습럽다.

    왜 2차원 구조체 배열 자료구조가 필요할까? 파이어볼이 분열하고 흩어지고 난리부르스 치는 이동하는 파이어볼을 관리해야 하므로.

    ==> 이후 난리부르스치는 파이어볼을 어떻게 자료구조에 차곡차곡 관리할지는 구현력에 해당하는 것이다. 절차를 잘 구현해야 한다.
    이동한다(이동한 격자에맞는 map의 인덱스에 파이어볼이 담긴다) -> 분열한다(격자에 있는 파이어볼중 그 갯수가 1개면 map자료구조에서는 없어진다. 왜? map은 오직 이동하는 파이어볼만 관리하므로.
     2개면 각각의 파이어볼이 갖는 세부값들을 합산하여 계산하면서 map자료구조에서 없앤다. 왜? 마찬가지로 map은 오직 이동하는 파이어볼만 관리하므로 이동에 필요한 알맹이만 챙기고
    map은 다시 새것처럼 보존시켜 주어야 하기 때문이다.그리고 fireballs에서도 없앤다. 왜? 분열하여 없어진것과 같으므로. 대신 새롭게 분열되어 생성된 파이어볼을 fireballs에 담는다.
    매번 이중반복문을 돌면서 map에 있는 파이어볼은 모두 삭제 되지만 fireballs에서는 그대로 남는 것과 삭제되는 것이있다. fireballs에서는 오직 융합하여 분열이 가능한 융합에 사용된 파이어볼만 삭제된다.
    이렇게 새롭게 분열하여 생성된 파이어볼은 fireballs에만 존재하고 map에는 아직 존재하지 않는상태가 된다. 즉 분열이 모두 끝나면 map은 비어있다.)

    단순하게 보면 위처럼 이동, 분열과정을 반복하는 것이다. 내가 익힐것은 객체의 이동에 주안점을 둔 문제에서 주인공객체가 이동하면 그 이동을 관리하는 자료구조(map)를 따로 만들고 이동과 별개로 전체 객체를
    관리하는 자료구조를 따로 두어야 한다는 것이다.
    */


public class Testing3 {
    static class Meteor {
        int r;
        int c;
        int m;
        int s;
        int d;

        public Meteor(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

    }

    public static List<Meteor>[][] map;
    public static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        List<Meteor> meteors = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int mm = Integer.parseInt(st.nextToken());
            int ss = Integer.parseInt(st.nextToken());
            int dd = Integer.parseInt(st.nextToken());
            meteors.add(new Meteor(r, c, mm, ss, dd));
        }
        for (int i = 0; i < k; i++) {
            moveMeteor(meteors, n);
            fireMeteor(meteors, n);
        }
        int answer = getTotalWeight(meteors);
        System.out.println(answer);
    }

    //r,c,m,s,d
    public static void moveMeteor(List<Meteor> meteors, int n) {
        for (var meteor : meteors) {
            int nr = (meteor.r + n + (dy[meteor.d] * meteor.s)% n ) % n;
            int nc = (meteor.c + n + (dx[meteor.d] * meteor.s% n)% n ) % n;
            meteor.r = nr;
            meteor.c = nc;
            map[nr][nc].add(meteor);
        }
    }

    public static void fireMeteor(List<Meteor> meteors, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int size = map[i][j].size();
                //1개의 파이어볼을 가지고 있는 좌표는 그 파이어볼을 좌표에서는 삭제하지만 파이어볼리스트에는 남겨 놓아야 다음 움직임에서
                //좌표로 던져질 수 있다.
                if (size < 2) {
                    map[i][j].clear();
                    continue;
                }
                int mSum = 0, sSum = 0, oddCount = 0, evenCount = 0;
                //융합의 for문
                for (var fireball : map[i][j]) {
                    mSum += fireball.m;
                    sSum += fireball.s;
                    if (fireball.d % 2 == 0)
                        ++evenCount;
                    else
                        ++oddCount;
                    //융합에서는 기존의 파이어볼이 없어지는 것으로 좌표,파이어볼 리스트 둘 모두에서 삭제되어야 한다.
                    meteors.remove(fireball);
                }
                map[i][j].clear();
                int nm = mSum / 5;
                if (nm == 0)
                    continue;
                int ns = sSum / size;
                //구조화 사고가 정말 중요하다. 세부적이고 첨예한 과정에서 어떠한 자료구조에는 데이터를 남기고 어떠한 자료구조에서는 데이터를
                //삭제할 것인지, 또 어떠한 자료구조에 데이터를 추가해 줄 것인지를 전체 흐름을 염두하면서 코딩해야 한다.
                if (evenCount == size || oddCount == size) {
                    for (int k = 0; k <= 6; k += 2)
                        //다음 움직임에서 뿌려지기 위해 새로이 생겨나는 파이어볼은 파이어볼 리스트에 추가해 주고 다음 움직임에서 좌표로 뿌려진다.
                        meteors.add(new Meteor(i, j, nm, ns, k));

                } else
                    for (int k = 1; k <= 7; k += 2)
                        meteors.add(new Meteor(i, j, nm, ns, k));
            }
        }
    }

    public static int getTotalWeight(List<Meteor> meteors) {
        int sum = 0;
        for (var meteor : meteors)
            sum += meteor.m;
        return sum;
    }
}