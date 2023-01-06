import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N가지 종류의 화폐
 * 화폐들의 개수를 최소한으로 이용해서 가치의 합이 M이 되도록
 * 최소한의 화폐 개수를 구하라
 * input
 * 2 15
 * 2
 * 3
 * output
 * 5
 * input
 * 3 4
 * 3
 * 5
 * 7
 * output
 * -1
 */

public class 효율적인화폐구성 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N, M 공백을 구분하여 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // N개의 줄에 각 화폐의 가치 입력
        int[] value = new int[N];
        for (int i = 0; i < N; i++) {
            value[i] = Integer.parseInt(br.readLine());
        }

        // 한 번 계산된 결과를 저장하기 위한 DP 테이블 초기화
        int[] DP = new int[M + 1];
        for (int i = 1; i <= M; i++) {
            DP[i] = 10001;
        }

        // 다이나믹 프로그래밍(보텀업)
        for (int i = 0; i < N; i++) {
            for (int j = value[i]; j <= M; j++) {
                if(DP[j - value[i]] != 10001) DP[j] = Math.min(DP[j], DP[j - value[i]] + 1);
            }
        }

        // M원을 만드는 방법이 없는 경우
        if(DP[M] == 10001) System.out.println(-1);
        else System.out.println(DP[M]);
    }
}
