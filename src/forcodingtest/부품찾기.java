package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 동빈이네 전자 매장, 부품 N개
 * 정수 형태의 고유한 번호
 * 손님이 M개 종류의 부품을 구매
 * 가게 안에 부품이 모두 있는지 확인
 * input
 * 5
 * 8 3 7 9 2
 * 3
 * 5 7 9
 * output
 * no yes yes
 */
public class 부품찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 가게 부품 개수 N 입력
        int N = Integer.parseInt(br.readLine());

        // 가게 부품 번호 입력
        int[] store = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            store[i] = Integer.parseInt(st.nextToken());
        }

        // 손님이 문의한 부품 개수 M 입력
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boolean has = false;
            // 필요한 부품의 번호 입력
            int need = Integer.parseInt(st.nextToken());
            for ( int s : store ) {
                // 만약 가게에 부품이 있으면 has = true
                if(s == need) has = true;
            }
            // 부품이 있으면 yes 없으면 no
            if(has) sb.append("yes").append(" ");
            else sb.append("no").append(" ");
        }
        System.out.println(sb);
    }
}
