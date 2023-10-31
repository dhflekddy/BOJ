/*
처음에는 뭔가 재귀적 설계를 생각하면서 복잡한 해결책을 생각했었다(이전에 풀었던 14916거스름돈 문제풀다가 바로 이문제 풀어서 그랬던것같다).
하지만 이 문제는 매우 단순한 문제이다. 왜냐하면 답에 해당하는 문자열을 ('AAAA'혹은 'BB'을 이용해서) 만들어가기만 하면 되는것이다.
즉 재귀적사고로 재귀트리를 내려갔다 올라갔다 하지 않고 그냥 한큐에 'AAAA'가 안되면 'BB'를 넣고 'BB'가 안되면 '.'을 넣으면 되기 때문이다.
즉 답에 해당하는 문자열을 순차적으로(빠꾸할 필요없이)완성해 나가면 되기 때문에 쉬운 문제다.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Testing {
    public static String a = "AAAA";
    public static String b = "BB";

    public static boolean safetyCheck1(char[] chars, int start) {
        int size = chars.length;
        if (start + 3 > size - 1)
            return false;
        for (int i = 0; i < 4; i++) {
            if (chars[start] == 'X') {
                start += 1;
                continue;
            } else
                return false;
        }
        return true;
    }

    public static boolean safetyCheck2(char[] chars, int start) {
        int size = chars.length;
        if (start + 1 > size - 1)
            return false;
        for (int i = 0; i < 2; i++) {
            if (chars[start] == 'X') {
                start += 1;
                continue;
            } else
                return false;
        }
        return true;
    }

    public static boolean safetyCheck3(char[] chars, int start) {
        int size = chars.length;
        if (start > size - 1)
            return false;
        if (chars[start] == '.')
            return true;
        return false;
    }

    public static String solution(char[] chars) {
        int start = 0;
        int size = chars.length;
        String answer = "";
        while (start != size) {
            if (safetyCheck1(chars, start)) {
                answer += "AAAA";
                start += 4;
            } else if (safetyCheck2(chars, start)) {
                answer += "BB";
                start += 2;
            } else if (safetyCheck3(chars, start)) {
                answer += ".";
                start += 1;
            } else
                return null;
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] chars = str.toCharArray();
        int length = chars.length;
        //연속된 x가 몇개인지부터 확인한다. AAAA~ 로 처음 시도해 보고 .혹은 더이상 칸이 없어서 안되면 BB를 시도해본다.
        String answer = solution(chars);
        if (answer == null)
            System.out.println(-1);
        else
            System.out.println(answer);
    }
}