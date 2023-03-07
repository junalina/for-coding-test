package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1620
 * <p>
 * 나는야 포켓몬 마스텅 ㅣ다솜
 * 포켓몬 마스터가 되기 위해 도감을 완성
 * 현재 가지고 있는 포켓몬 도감에서 포켓몬의 이름을 보면 포켓몬의 번호를 말하거나,
 * 포켓몬의 번호를 보면 포켓몬의 이름을 말하기
 * <p>
 * 풀이
 * 해시맵
 */

public class BJ_1620_나는야포켓몬마스터이다솜 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 포켓몬의 개수
        int M = Integer.parseInt(st.nextToken()); // 문제의 개수

        // 포켓몬의 이름 해시맵에 추가
        HashMap<String, Integer> strKeyMap = new HashMap<>();
        HashMap<Integer, String>  intKeyMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            strKeyMap.put(name, i);
            intKeyMap.put(i, name);
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            char firstName = input.charAt(0);
            // 이름이면
            if (firstName >= 'A' && firstName <= 'Z') {
                // 번호 출력
                sb.append(strKeyMap.get(input));
            }
            // 번호라면
            else {
                // 이름 출력
                sb.append(intKeyMap.get(Integer.parseInt(input)));
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
