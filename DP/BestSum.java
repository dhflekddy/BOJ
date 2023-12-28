import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Testing3 {
    public static List<Integer> solve(int m, int[] nums){
        List<List<Integer>> table
                = new ArrayList<>(Collections.nCopies(m + 1, null));
        table.set(0, new ArrayList<Integer>());
        for(int i = 0; i < m; ++i){
            List<Integer> curr = table.get(i);
            if(curr != null){
                for(var n: nums){
                    if(i + n <= m &&
                            (table.get(i + n) == null || table.get(i + n).size() > curr.size() + 1)) {
                        List<Integer> next = new ArrayList<>(curr);
                        next.add(n);
                        table.set(i + n, next);
                    }
                }
            }
        }
        return table.get(m);
    }

    public static void main(String[] args) {
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(System.in))
        ){
            int T = Integer.parseInt(in.readLine());
            for(int t = 0; t < T; ++t) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                int M = Integer.parseInt(st.nextToken());
                int[] nums = Arrays.stream(in.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                List<Integer> ans = solve(M,nums);
                if(ans == null) System.out.println(-1);
                else {
                    System.out.print(ans.size()+" ");
                    System.out.println(ans.stream().map(n -> n.toString())
                            .collect(Collectors.joining(" ")));
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}