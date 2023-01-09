import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 알파벳 대문자와 숫자 0~9로만 구성된 문자열 입력
 * 모든 알파벳을 오름차순으로 정렬한 뒤에 그 뒤에 모든 숫자를 더한 값을 이어서 출력하라
 * input1
 * K1KA5CB7
 * output1
 * ABCKK13
 * input2
 * AJKDLSI412K4JSJ9D
 * output2
 * ADDIJJJKKLSS20
 */

public class 문자열재정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        // 알파벳 대문자와 숫자를 나누어 처리
        ArrayList<Character> left = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < S.length(); i++) {
            int n = S.charAt(i) - '0';
            if(n >= 0 && n <= 9) sum += n;
            else left.add(S.charAt(i));
        }
        
        // 오름차순 정렬
        Collections.sort(left);
        
        String res = "";
        for (int i = 0; i < left.size(); i++) {
            res += left.get(i);
        }
        res += sum;

        System.out.println(res);

    }
}
