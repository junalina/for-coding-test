import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 큰수의법칙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N, M, K 공백으로 구분하여 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] num = new int[N];

        // N개의 수를 공백으로 구분하여 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        } // 입력 끝

        Arrays.sort(num); // 배열 정렬

        int sum = 0;

        while (true) {
            for (int i = 0; i < K; i++) { // 가장 큰 수를 K번 더하기
                if(M == 0) break; // M이 0이라면 반복문 탈출
                sum += num[N-1];
                M--; // 더할 때마다 1씩 빼기
            }
            if(M == 0) break; // M이 0이라면 반복문 탈출
            sum += num[N-2]; // 두 번째로 큰 수를 한 번 더하기
            M--; // 더할 때마다 1씩 빼기
        }

        System.out.println(sum);

    }
}