
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//
/**
 * @author 김상진
 * 0, 1, 2, 3을 이용하여 구성된 격자 맵이 주어짐
 * 0: 통로
 * 1: 시작위치
 * 2: 보석
 * 3: 벽
 * 목적: 시작 위치에서 보석까지의 최단 경로 찾기
 * 이동은 상하좌우로만 할 수 있음
 * 맵에는 항상 보석이 하나 주어짐
 * 보석을 찾을 수 없으면 최단 경로의 길이는 -1을 출력해야 함
 * 주어진 해결책에서 코드 냄새를 찾아 리펙토링하세요.
 * 요구사항. 주석에 다음을 포함하여 주세요.
 * 1) 리펙토링한 순서
 * 2) 각 코드 냄새를 제거하기 위해 리펙토링한 방법
 * 강의 슬라이드에 제시된 코드 냄새와 정확한 매칭이 되지 않지만
 * 코드를 개선할 수 있는 부분을 발견하여 개선하면 그것도
 * 제시한 순서에 포함해야 함
 * <p>
 * <p>
 * 중요한 것은 한 번에 여러 냄새를 제거하는 것이 아니라
 * 냄새를 하나씩 제거하고 테스트하는 것을 반복해야 함
 * 냄새제거 순서와 방법 포함하기
 * @version 2023년도 2학기
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 */

////원본!!!!!!!!!!
//public class CodeSmell {
//    // BFS를 이용하여 시작 위치부터 보석을 찾는다.
//    public static int solve(int[][] map) {
//        // 시작 위치 탐색
//        int startR = 0;
//        int startC = 0;
//        FOUND:
//        for(int r = 0; r < map.length; ++r)
//            for(int c = 0; c < map[0].length; ++c) {
//                if(map[r][c] == 1) {
//                    startR = r;
//                    startC = c;
//                    break FOUND;
//                }
//            }
//        // 시작 위치부터 BFS 수행
//        int minLength = -1;
//        boolean[][] visited = new boolean[map.length][map[0].length];
//        Queue<Integer> rowQueue = new ArrayDeque<>();
//        Queue<Integer> colQueue = new ArrayDeque<>();
//        Queue<Integer> lengthQueue = new ArrayDeque<>();
//        rowQueue.add(startR);
//        colQueue.add(startC);
//        lengthQueue.add(0);
//        visited[startR][startC] = true;
//        while(!rowQueue.isEmpty()) {
//            int currR = rowQueue.poll();
//            int currC = colQueue.poll();
//            int length = lengthQueue.poll();
//            // 4개 방향으로 탐색 진행
//            if(currR + 1 < map.length && !visited[currR + 1][currC]) {
//                if(map[currR + 1][currC] == 2) {
//                    minLength = length+1;
//                    break;
//                }
//                else if(map[currR + 1][currC] == 0) {
//                    visited[currR + 1][currC] = true;
//                    rowQueue.add(currR + 1);
//                    colQueue.add(currC);
//                    lengthQueue.add(length+1);
//                }
//            }
//            if(currR - 1 >= 0 && !visited[currR - 1][currC]) {
//                if(map[currR-1][currC] == 2) {
//                    minLength = length+1;
//                    break;
//                }
//                else if(map[currR - 1][currC] == 0) {
//                    visited[currR - 1][currC] = true;
//                    rowQueue.add(currR - 1);
//                    colQueue.add(currC);
//                    lengthQueue.add(length + 1);
//                }
//            }
//            if(currC + 1 < map[0].length && !visited[currR][currC + 1]) {
//                if(map[currR][currC + 1] == 2) {
//                    minLength = length + 1;
//                    break;
//                }
//                else if(map[currR][currC + 1] == 0) {
//                    visited[currR][currC + 1] = true;
//                    rowQueue.add(currR);
//                    colQueue.add(currC + 1);
//                    lengthQueue.add(length + 1);
//                }
//            }
//            if(currC - 1 >= 0 && !visited[currR][currC - 1]) {
//                if(map[currR][currC - 1] == 2) {
//                    minLength = length + 1;
//                    break;
//                }
//                else if(map[currR][currC - 1] == 0) {
//                    visited[currR][currC - 1] = true;
//                    rowQueue.add(currR);
//                    colQueue.add(currC - 1);
//                    lengthQueue.add(length + 1);
//                }
//            }
//        }
//        return minLength;
//    }
//
//    public static void main(String[] args) {
//        int[][] map = {
//                {3, 0, 3, 0, 3, 1, 3},
//                {3, 0, 0, 0, 3, 0, 3},
//                {3, 0, 3, 0, 0, 0, 3},
//                {3, 0, 3, 3, 3, 0, 3},
//                {3, 0, 0, 2, 3, 0, 0},
//                {3, 3, 3, 3, 3, 3, 3}
//        };
//        System.out.println(solve(map));
//        map = new int[][]{
//                {3, 3, 3, 0, 3, 0, 3},
//                {3, 0, 0, 0, 3, 0, 3},
//                {1, 0, 3, 0, 3, 0, 3},
//                {3, 0, 3, 3, 3, 0, 3},
//                {3, 0, 3, 2, 0, 0, 0},
//                {3, 3, 3, 3, 3, 3, 3}
//        };
//        System.out.println(solve(map));
//        map = new int[][]{
//                {3, 3, 3, 0, 3, 3, 3},
//                {3, 0, 0, 0, 3, 2, 3},
//                {1, 0, 3, 0, 0, 0, 3},
//                {3, 0, 3, 3, 3, 0, 3}
//        };
//        System.out.println(solve(map));
//    }
//}

public class CodeSmell {
    public static class Point {
        private int y;
        private int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public void setX(int x) {
            this.x = x;
        }
    }

    public static Point findStartPoint(int[][] map) {
        int startR = 0;
        int startC = 0;
        FOUND:
        for (int r = 0; r < map.length; ++r)
            for (int c = 0; c < map[0].length; ++c) {
                if (map[r][c] == 1) {
                    startR = r;
                    startC = c;
                    break FOUND;
                }
            }
        return new Point(startR, startC);
    }

    public static Point findStopPoint(int[][] map) {
        int stopR = 0;
        int stopC = 0;
        FOUND:
        for (int r = 0; r < map.length; ++r)
            for (int c = 0; c < map[0].length; ++c) {
                if (map[r][c] == 2) {
                    stopR = r;
                    stopC = c;
                    break FOUND;
                }
            }
        return new Point(stopR, stopC);
    }

    // BFS를 이용하여 시작 위치부터 보석을 찾는다.
    public static int bfs(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] lengInfo = new int[n][m];


        for (int i = 0; i < n; i++)
            Arrays.fill(lengInfo[i], -1);

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};
        // 시작 위치 탐색
        Point start = findStartPoint(map);
        // 시작 위치부터 BFS 수행
        int minLength = -1;
        boolean[][] visited = new boolean[map.length][map[0].length];
//        Queue<Integer> rowQueue = new ArrayDeque<>();
//        Queue<Integer> colQueue = new ArrayDeque<>();
//        Queue<Integer> lengthQueue = new ArrayDeque<>();
        Queue<Point> que = new LinkedList<>();
        que.add(start);
//        rowQueue.add(startR);
//        colQueue.add(startC);
//        lengthQueue.add(0);
        visited[start.getY()][start.getX()] = true;
        int length = 0;
        while (!que.isEmpty()) {
//            int currR = rowQueue.poll();
//            int currC = colQueue.poll();
//            int length = lengthQueue.poll();
            Point loc = que.poll();
            int curY = loc.getY();
            int curX = loc.getX();

//            // 4개 방향으로 탐색 진행
//            if(currR + 1 < map.length && !visited[currR + 1][currC]) {
//                if(map[currR + 1][currC] == 2) {
//                    minLength = length+1;
//                    break;
//                }
//                else if(map[currR + 1][currC] == 0) {
//                    visited[currR + 1][currC] = true;
//                    rowQueue.add(currR + 1);
//                    colQueue.add(currC);
//                    lengthQueue.add(length+1);
//                }
//            }
//            if(currR - 1 >= 0 && !visited[currR - 1][currC]) {
//                if(map[currR-1][currC] == 2) {
//                    minLength = length+1;
//                    break;
//                }
//                else if(map[currR - 1][currC] == 0) {
//                    visited[currR - 1][currC] = true;
//                    rowQueue.add(currR - 1);
//                    colQueue.add(currC);
//                    lengthQueue.add(length + 1);
//                }
//            }
//            if(currC + 1 < map[0].length && !visited[currR][currC + 1]) {
//                if(map[currR][currC + 1] == 2) {
//                    minLength = length + 1;
//                    break;
//                }
//                else if(map[currR][currC + 1] == 0) {
//                    visited[currR][currC + 1] = true;
//                    rowQueue.add(currR);
//                    colQueue.add(currC + 1);
//                    lengthQueue.add(length + 1);
//                }
//            }
//            if(currC - 1 >= 0 && !visited[currR][currC - 1]) {
//                if(map[currR][currC - 1] == 2) {
//                    minLength = length + 1;
//                    break;
//                }
//                else if(map[currR][currC - 1] == 0) {
//                    visited[currR][currC - 1] = true;
//                    rowQueue.add(currR);
//                    colQueue.add(currC - 1);
//                    lengthQueue.add(length + 1);
//                }
//            }
            for (int i = 0; i < 4; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];
                if (nextY < 0 || nextX < 0 || nextY >= n || nextX >= m)
                    continue;
                if (visited[nextY][nextX] || map[nextY][nextX] == 3)
                    continue;
                if (map[nextY][nextX] == 2) {
                    lengInfo[nextY][nextX] = (lengInfo[curY][curX] + 2);
                    break;
                } else if (map[nextY][nextX] == 0) {
                    visited[nextY][nextX] = true;
                    que.add((new Point(nextY, nextX)));
                    lengInfo[nextY][nextX] = (lengInfo[curY][curX] + 1);

//                    lengthQueue.add(length + 1);
                }

            }
        }
        Point stop = findStopPoint(map);
        return lengInfo[stop.getY()][stop.getX()];
    }

    public static void main(String[] args) {
        int[][] map = {
                {3, 0, 3, 0, 3, 1, 3},
                {3, 0, 0, 0, 3, 0, 3},
                {3, 0, 3, 0, 0, 0, 3},
                {3, 0, 3, 3, 3, 0, 3},
                {3, 0, 0, 2, 3, 0, 0},
                {3, 3, 3, 3, 3, 3, 3}
        };
        System.out.println(bfs(map));
        map = new int[][]{
                {3, 3, 3, 0, 3, 0, 3},
                {3, 0, 0, 0, 3, 0, 3},
                {1, 0, 3, 0, 3, 0, 3},
                {3, 0, 3, 3, 3, 0, 3},
                {3, 0, 3, 2, 0, 0, 0},
                {3, 3, 3, 3, 3, 3, 3}
        };
        System.out.println(bfs(map));
        map = new int[][]{
                {3, 3, 3, 0, 3, 3, 3},
                {3, 0, 0, 0, 3, 2, 3},
                {1, 0, 3, 0, 0, 0, 3},
                {3, 0, 3, 3, 3, 0, 3}
        };
        System.out.println(bfs(map));
    }
}

//Step1. BFS를 이용하여 문제를 해결하고 있습니다. 하지만 solve함수 초반부에는 bfs본연의
// 기능이 아닌 시작점을 우선 찾고 있는 코드가 먼저 나오고 있습니다. 이는 하나의 함수를
// 너무 길게 하고 하나의 기능만 해야하는 함수의 성격에 벗어난다고 생각하여 시작점을
// 찾는 findStartPoint라는 함수로 분리하였습니다. 그리고 기존 함수의 이름인 solve는 bfs로 변경하였습니다.
//
//Step2. 또한 4개의 if문에서 항상 currR와 CurrC이 항상 함께 쓰인다는 것에 주목(데이터 덩어리)하여 이것을 하나의 데이터
// 클래스로 만들어 주면 가독성이 향상될 것이라고 생각하여 Point라는 클래스를 정의해 주었습니다. 또한 상하좌우 4방향에 대한
// 좌표를 대상으로 한 행위는 모두 같다는 것에서(주워진 맵의 column과 row를 넘지 않는지 검사하고 이미 방문이 이루어지지 않았나
// 검사하는 if문을 통과하면 이를 두고 목적지에 도달했을시에 이루어 지는 행위와 목적지가 아닌 과정중에 있을때 처리되는 일련의
// 코드가 모두 중복됨)이것을 데이터 클래스의 행위로 구현해 볼까도 생각해 보았지만 그렇게 되면 클래스의 맴버 변수로 graph, visited에
// 관한 정보까지 두어야 하고 이렇게 되면 클래스의 응집성이 떨어진다고 생각하여 메서드는 클래스안에 넣지 않게 되었습니다.
//이렇게 새로운 클래스(Point)를 만들어 줌으로써 코드의 중복을 없애고 긴 함수를 더 짧게 만들수 있었습니다.
//
//Step3. 위와 같이 새로운 클래스를 정의해 줌으로써 행에 관한정보와(y), 열에 관한정보(x)를 하나로 묶을 수 있습니다. 그리고 주워진
// 코드에는 rowQueue, colQueue, lengthQueue라는 Queue<Integer>변수가 모두 따로따로 주워지고 하나의 변수에 add가 일어날시 3개의 변
// 수 모두에 add가 일어나고 poll이 일어날시 3개의 변수에서 모두 poll해줍니다. 이것은 redundant한 것으로 행과 열정보를 모두 갖는 Queue<Point>
// 형 변수를 사용하면 이와같은 중복을 없앨 수 있습니다.
//
//Step4. 또한 if문이 반복되는 이유로 상하주우의 좌표를 각각 처리해 준다는 것인데 이러한 중복을 피하기 위해 방향정보를 알려줄수 있는 int형 배열 dy, dx를
// 사용하였습니다. 또한 길이정보와관련된 lengthQueue변수를 사용하여 매번 큐에서 넣다 빼주었다를 반복하는것이 아닌 2차원 배열인 lengthInfo변수를 만들고 2번으로 표
// 시된 좌표를 찾아(findStopPoint함수)그 좌표의 값을 반환합니다.