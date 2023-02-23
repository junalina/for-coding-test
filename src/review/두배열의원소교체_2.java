package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두배열의원소교체_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        int[] B = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            Arrays.sort(A);
            Arrays.sort(B);
            if (A[0] < B[N - 1]) {
                int temp = A[0];
                A[0] = B[N - 1];
                B[N - 1] = temp;
            } else break;
        }

        int sum = 0;
        for (int a : A) {
            sum += a;
        }

        System.out.println(sum);

    }
}
