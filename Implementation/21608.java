import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

class Seat implements Comparable<Seat> {
    int y;
    int x;
    int adjacentBestFriends;
    int adjacentVacantSeat;

    public Seat(int y, int x, int adjacentBestFriends, int adjacentVacantSeat) {
        this.y = y;
        this.x = x;
        this.adjacentBestFriends = adjacentBestFriends;
        this.adjacentVacantSeat = adjacentVacantSeat;
    }

    @Override
    public int compareTo(Seat o) {
        if (adjacentBestFriends != o.adjacentBestFriends)
            return -(adjacentBestFriends - o.adjacentBestFriends);
        if (adjacentVacantSeat != o.adjacentVacantSeat)
            return -(adjacentVacantSeat - o.adjacentVacantSeat);
        if (y != o.y)
            return y - o.y;
        return x - o.x;
    }
}

public class Main {
    public static Seat findSeat(int[][] result, int student, Map<Integer, Set<Integer>> preferencies, int[] yDirection, int[] xDirection) {
        int length = result[0].length;
        Seat seat = null;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (result[i][j] > 0)
                    continue;
                Seat cur = new Seat(i, j, getAdjacentBF(i, j, student, preferencies, yDirection, xDirection, result), getAdjacentES(i, j, result, yDirection, xDirection));
                if (seat == null) {
                    seat = cur;
                    continue;
                }
                if (seat.compareTo(cur) > 0) {
                    seat = cur;
                }
            }
        }
        return seat;
    }

    public static int getAdjacentBF(int i, int j, int student, Map<Integer, Set<Integer>> preferencies, int[] yDirection, int[] xDirection, int[][] result) {
        int length = result[0].length;
        int output = 0;
        for (int k = 0; k < 4; k++) {
            int ny = i + yDirection[k];
            int nx = j + xDirection[k];
            if (ny < 0 || ny >= length || nx < 0 || nx >= length)
                continue;
            if (preferencies.get(student).contains(result[ny][nx]))
                output++;
        }
        return output;
    }

    public static int getAdjacentES(int i, int j, int[][] result, int[] yDirection, int[] xDirection) {
        int length = result.length;
        int output = 0;
        for (int k = 0; k < 4; k++) {
            int ny = i + yDirection[k];
            int nx = j + xDirection[k];
            if (ny < 0 || ny >= length || nx < 0 || nx >= length)
                continue;
            if (result[ny][nx] == 0)
                output++;
        }
        return output;
    }

    public static void solution(int n, int[] students, Map<Integer, Set<Integer>> preferencies) {
        int[][] result = new int[n][n];
        int[] yDirection = {-1, 1, 0, 0};
        int[] xDirection = {0, 0, -1, 1};

        for (int i = 0; i < students.length; i++) {

            Seat seat = findSeat(result, students[i], preferencies, yDirection, xDirection);
            result[seat.y][seat.x] = students[i];
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int value = getAdjacentBF(i, j, result[i][j], preferencies, yDirection, xDirection, result);
                if (value > 0)
                    answer += (int) Math.pow(10, value - 1);
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Map<Integer, Set<Integer>> preferencies = new HashMap<>();
        int[] students = new int[num * num];
        StringTokenizer st;
        for (int i = 0; i < num * num; i++) {
            st = new StringTokenizer(br.readLine());
            students[i] = Integer.parseInt(st.nextToken());
            preferencies.put(students[i], new HashSet<Integer>());
            for (int j = 0; j < 4; j++)
                preferencies.get(students[i]).add(Integer.parseInt(st.nextToken()));
        }
        solution(num, students, preferencies);
    }
}
