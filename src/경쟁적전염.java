import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/18405
 *
 * 경쟁적 전염
 *
 * N x N 크기 시험관, 1 x 1 크기의 칸, 특정 위치 1 ~ K 바이러스 존재,
 * 바이러스 상하좌우 증식
 * 매 초마다 번호가 낮은 바이러스부터 증식
 * 증식 과정에 이미 바이러스가 존재하면, 바이러스 증식 X
 * 시험관의 크기와 바이러스의 위치 정보가 주어졌을 때,
 * S초가 지난 후에 (X,Y)에 존재하는 바이러스의 종류를 출력
 *
 * 풀이
 * BFS로 바이러스 증식
 */

public class 경쟁적전염 {

    static int N, K;
    static int[][] map;
    static ArrayList<Virus> list = new ArrayList<>();

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Virus implements Comparable<Virus> {
        int num, s, r, c;

        public Virus(int num, int s, int r, int c) {
            this.num = num;
            this.s = s;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Virus o) {
            return this.num - o.num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, K 공백 구분하여 입력
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        // 시험관의 정보
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                // 바이러스면 리스트에 추가
                if (map[r][c] != 0) {
                    list.add(new Virus(map[r][c], 0, r, c));
                }
            }
        }

        // 바이러스 번호에 따라 오름차순 정렬
        Collections.sort(list);

        Queue<Virus> q = new LinkedList<>();
        for (Virus v : list) {
            q.offer(v);
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        // BFS
        while (!q.isEmpty()) {
            Virus v = q.poll();
            int s = v.s;
            if (s == S) break;
            int r = v.r;
            int c = v.c;
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 시험관 안에 있다면
                if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                    // 아직 아무것도 없는 위치면 바이러스 증식
                    if (map[nr][nc] == 0) {
                        map[nr][nc] = v.num;
                        q.offer(new Virus(map[nr][nc], s + 1, nr, nc));
                    }
                }
            }
        }
        System.out.println(map[X - 1][Y - 1]);
    }
}
