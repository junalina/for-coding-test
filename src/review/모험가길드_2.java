package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 모험가길드_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] traveler = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            traveler[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(traveler);

        int res = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt++;
            if (cnt >= traveler[i]) {
                res++;
                cnt = 0;
            }
        }

        System.out.println(res);
    }
}
