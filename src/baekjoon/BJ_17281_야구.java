package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/17281
 * <p>
 * 야구
 * 9명으로 이루어진 공수 번갈아 하는 게임
 * N이닝 동안 게임 진행
 * 한 이닝에 3아웃 발생하면 종료, 두 팀이 공수 교체
 * 두 팀은 경기 시작 전 타순을 정해야하고 경기 중에 변경 불가
 * 타순은 이닝이 변경되어도 순서 유지
 * 감독 아인타는 타순을 정하려고 함
 * 각 선수가 각 이닝에서 어떤 결과를 얻는지 미리 알고 있다.
 * 얻을 수 있는 최대 점수 출력
 * <p>
 * 풀이
 * 타순을 순열로 만들고 경기 결과 완전 탐색
 */

public class BJ_17281_야구 {
    static int N, max;
    static int[][] result;
    static int[] player;
    static boolean[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 이닝 수
        N = Integer.parseInt(br.readLine());

        result = new int[N + 1][10];

        // 각 선수가 각 이닝에서 얻는 결과
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 타순
        player = new int[10];
        selected = new boolean[10];
        // 1번 선수를 4번 타자로 미리 결정
        player[4] = 1;
        selected[4] = true;

        // 최대 점수
        max = 0;

        // 순열로 타순 만들기,
        perm(2);

        System.out.println(max);
    }

    private static void perm(int num) {
        // 타순을 만들었으면
        if (num == 10) {
            // 만들어진 타순으로 점수 계산
            int score = getScore();
            max = Math.max(max, score);
        }
        for (int i = 1; i <= 9; i++) {
            if (!selected[i]) {
                // 방문처리
                selected[i] = true;
                player[i] = num;
                perm(num + 1);
                // 원상복구
                selected[i] = false;
            }
        }
    }

    private static int getScore() {
        // 점수
        int score = 0;
        // 1번 선수부터 시작
        int now = 1;
        // N이닝 동안 게임 진행
        for (int i = 1; i <= N; i++) {
            // 아웃 수
            int out = 0;
            // 진루 상태
            boolean[] base = new boolean[4];

            // 한 이닝에 3아웃이 발생하면 이닝 종료
            while (out < 3) {
                switch (result[i][player[now]]) {
                    // 아웃
                    case 0:
                        out++;
                        break;
                    // 1루타
                    case 1:
                        if (base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            base[3] = true;
                            base[2] = false;
                        }
                        if (base[1]) {
                            base[2] = true;
                        }
                        base[1] = true;
                        break;
                    // 2루타
                    case 2:
                        if (base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            score++;
                        }
                        if (base[1]) {
                            base[3] = true;
                            base[1] = false;
                        }
                        base[2] = true;
                        break;
                    // 3루타
                    case 3:
                        if (base[3]) {
                            score++;
                        }
                        if (base[2]) {
                            score++;
                            base[2] = false;
                        }
                        if (base[1]) {
                            score++;
                            base[1] = false;
                        }
                        base[3] = true;
                        break;
                    // 홈런
                    case 4:
                        if (base[3]) {
                            score++;
                            base[3] = false;
                        }
                        if (base[2]) {
                            score++;
                            base[2] = false;
                        }
                        if (base[1]) {
                            score++;
                            base[1] = false;
                        }
                        score++;
                        break;
                }
                // 다음 타자
                now++;

                // 9번 타자가 공을 쳤다면 다음 타자는 1번
                if (now > 9) now = 1;
            }
        }
        return score;
    }
}
