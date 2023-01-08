import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 숫자로 된 문자열 S
 * 왼쪽부터 오른쪽으로 하나씩 모든 숫자 확인하며 x, + 연산자를 넣어 만들 수 있는 가장 큰 수 출력
 * input1
 * 02984
 * output1
 * 576
 * input2
 * 567
 * output2
 * 210
 */
public class 곱하기혹은더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        // 첫번째 문자를 숫자로 변경한 값을 대입
        long res = S.charAt(0) - '0';
        for (int i = 1; i < S.length(); i++) {
            // 두 수 중에서 하나라도 0이나 1인 경우 더하기 수행
            int num = S.charAt(i) - '0';
            if(res <= 1 || num <= 1) res += num;
            else res *= num;
        }
        System.out.println(res);
    }
}
