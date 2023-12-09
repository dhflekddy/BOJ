import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Testing3{
    public static int n;
    public static int m;
    public static int sy;
    public static int sx;
    public static int[]dy={-1,1,0,0};
    public static int[]dx={0,0,-1,1};

    static class Point{
        int y;
        int x;
        Point(int y, int x){
            this.y=y;
            this.x=x;
        }
    }
    public static void solution(int[][]board){
        Queue<Point>que=new LinkedList<>();
        boolean[][]visited=new boolean[n][m];
        que.add(new Point(sy,sx));
        board[sy][sx]=0;
//        System.out.println("que's size:"+que.size());
//        System.out.println("visited's length="+visited[0].length);
//        System.out.println("sy="+sy+", sx="+sx);

        visited[sy][sx]=true;
        while(!que.isEmpty()){
            Point p=que.poll();
            for(int i=0;i<4;i++){
                int ny=p.y+dy[i];
                int nx=p.x+dx[i];
                if(isValid(board, visited, ny, nx)){
                    visited[ny][nx]=true;
                    board[ny][nx]=board[p.y][p.x]+1;
                    que.add(new Point(ny, nx));
                }
            }
        }
    }
    public static boolean isValid(int[][]board, boolean[][]visited, int y, int x){
        return (y>=0&&y<n &&x>=0 &&x<m && !visited[y][x] &&board[y][x]!=0);
    }
    public static void main(String[] argS) throws  Exception{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        int[][]board=new int[n][m];
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                board[i][j]=Integer.parseInt(st.nextToken());
                if(board[i][j]==2){
                    sy=i;
                    sx=j;
                }
                //애초에 목적지로 갈수없는 정점은 -1로 초기화 해주는 게 필요한 센스
                if(board[i][j]==1)
                    board[i][j]=-1;
            }
        }
        solution(board);
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++)
                sb.append(board[i][j]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
        }