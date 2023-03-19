package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1759
 * <p>
 * 암호 만들기
 * 암호는 서로 다른 L개의 알파벳 소문자로 구성
 * 최소 한 개의 모음과 최소 두 개의 자음으로 구성
 * 문자열 오름차순 정렬
 * C개의 문자들이 주어졌을 때, 사전식으로 가능성 있는 암호들 모두 출력
 * <p>
 * 풀이
 * 조합
 */

public class BJ_1759_암호만들기 {
    static StringBuilder sb = new StringBuilder();
    static int L, C;
    static char[] alphabet, password;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // C개의 문자들 공백 구분하여 입력
        alphabet = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alphabet[i] = st.nextToken().charAt(0);
        }

        // 사전순으로 정렬
        Arrays.sort(alphabet);

        password = new char[L];
        comb(0, 0);

        System.out.println(sb);

    }

    private static void comb(int start, int cnt) {
        if (cnt == L) {
            // 암호에 최소 한 개의 모음과 최소 두 개의 자음이 있는지 확인
            checkPassword(password);
            return;
        } else {
            for (int i = start; i < C; i++) {
                password[cnt] = alphabet[i];
                comb(i + 1, cnt + 1);
            }
        }

    }

    private static void checkPassword(char[] password) {
        // 모음 개수
        int mo = 0;
        // 자음 개수
        int ja = 0;

        for (int i = 0; i < L; i++) {
            // 모음이라면 모음 개수 추가
            if (password[i] == 'a' || password[i] == 'e' || password[i] == 'i' || password[i] == 'o' || password[i] == 'u')
                mo++;
            // 자음이라면 자음 개수 추가
            else ja++;
        }
        // 암호에 최소 한 개의 모음과 최소 두 개의 자음이 있다면 암호 출력
        if (mo >= 1 && ja >= 2) {
            for (int i = 0; i < L; i++) {
                sb.append(password[i]);
            }
            sb.append("\n");
        }
    }
}
