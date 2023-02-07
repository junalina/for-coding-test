import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/18353
 * <p>
 * 병사 배치하기
 * <p>
 * N명의 병사 무작위 나열, 각 병사는 특정한 값의 전투력 보유
 * 병사 배치시, 전투력이 높은 병사가 앞쪽에 오도록 내림차순 배치하고자 함
 * 배치 과정 중 특정한 위치에 있는 병사를 열외시키는 방법 이용
 * 남아있는 병사의 수가 최대가 되도록 하기 위해서 열외해야 하는 병사의 수 출력
 */
public class 병사배치하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 병사 전투력 공백 구분하여 입력
        List<Integer> soldier = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            soldier.add(Integer.parseInt(st.nextToken()));
        }

        // 최장 증가 부분 수열(LIS) 사용하기 위해 배열 정렬
        Collections.reverse(soldier);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = soldier.get(i);
        }

        int max = 1; // 최대 LIS 값
        int[] dp = new int[N];
        // LIS
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(max, dp[i]);
        }
        System.out.println(N - max);
    }
}
