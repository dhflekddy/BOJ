import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Testing3 {
    /*
    불변객체의 멤버변수는 기본적으로 private, final임. 그런데 내부클래스로 쓰이므로 접근제어자는 무시됨(복습. 내부클래스의 접근
     제어자는 별도의 클래스에서 접근할 때나 의미가 있음. 하지만 내부 클래스를 별도의 다른 클래스가 사용한다는 것은 적절치 못함).
    (복습. 불변클래스를 static으로 수식함. static 내부 클래스는 외부 클래스에 대한 포인터주소를 유지하지 않는다)

     그렇다면 왜 내부클래스로 불변데이터 클래스인 record를 사용했을까? 이 내용 질문해서 답변은 내부클래스로 record를 사용하면
     생성자생략, Object메서드생략, 불변객체를 만들수 있어서 그렇다는 답변을 들었다. 하지만 내가 record를 실제로 일반 클래스로
     바꾸어도 문제가 없었고 record를 사용했을때보다 불편했던점은 생성자를 따로 코딩해 주어야 했었던 점이다. 즉, 내 생각에는
     static 중첩 (일반)클래스를 사용해도 된다!
     */
    static record Location(int r, int c) {
        static int height = 0;
        static int width = 0;

        static boolean isValid(int r, int c) {
            return (r >= 0 && r < height && c >= 0 && c < width);
        }
    }


    public static int[][][] cover = {
            {{0, 0}, {0, 1}, {1, 1}}, // ㄱ
            {{0, 0}, {0, 1}, {1, 0}}, // ㄱ의 반대
            {{0, 0}, {1, 0}, {1, -1}}, // ㄴ의 반대
            {{0, 0}, {1, 0}, {1, 1}} // ㄴ
    };

    public static boolean put(boolean[][] board, int r, int c, int[][] cover) {
        for (int i = 0; i < 3; ++i) {
            int locR = r + cover[i][0];
            int locC = c + cover[i][1];
            if (!Location.isValid(locR, locC) || board[locR][locC])
                return false;
        }
        for (int i = 0; i < 3; ++i)
            board[r + cover[i][0]][c + cover[i][1]] = true;
        return true;
    }

    public static void remove(boolean[][] board, int r, int c, int[][] cover) {
        for (int i = 0; i < 3; ++i)
            board[r + cover[i][0]][c + cover[i][1]] = false;
    }

    public static Location getStartLoc(boolean[][] board, int r, int c) {
        while (r < Location.height) {
            while (c < Location.width) {
                if (!board[r][c]) return new Location(r, c);
                ++c;
            }
            c = 0;
            ++r;
        }
        return null;
    }

    //내가 파이썬으로 짠 코드는 매번 블록이 점유되어있는지를 1행 1열의 블럭부터 검사했다. 이것은 solve함수에 행, 열 정보를 알려주는
    //아래의 R,C를 매개변수로 데리고 다니면 해결되는 문제이다. 그리고 다음 블럭의 위치를 찾기위해 getStartLoc이라는 함수를 정의해주었다.
    public static int solve(boolean[][] board, int R, int C) {
        Location nextLoc = getStartLoc(board, R, C);
        if (nextLoc == null) return 1;
        int ret = 0;
        for (int t = 0; t < 4; ++t) {
            if (put(board, nextLoc.r, nextLoc.c, cover[t])) {
                ret += solve(board, nextLoc.r, nextLoc.c);
                remove(board, nextLoc.r, nextLoc.c, cover[t]);
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine().strip());
        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(in.readLine().strip());
            Location.height = Integer.parseInt(st.nextToken());
            Location.width = Integer.parseInt(st.nextToken());
            boolean[][] board = new boolean[Location.height][Location.width];
            int count = 0;
            for (int r = 0; r < Location.height; ++r) {
                char[] line = in.readLine().strip().toCharArray();
                for (int c = 0; c < Location.width; ++c) {
                    if (line[c] == '#') board[r][c] = true;
                    else ++count;
                }
            }
            if (count % 3 != 0) System.out.println(0);
            else System.out.println(solve(board, 0, 0));
        }
    }
}
