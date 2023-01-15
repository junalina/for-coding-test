import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/3190
 * 뱀
 * Dummy 도스게임
 * 뱀이 기어다니는데, 사과를 먹으면 뱀 길이가 늘어남
 * 뱀이 벽이나 자기자신의 몸과 부딪히면 게임 끝
 * N x N 정사각 보드
 * 보드 상하좌우 끝에 벽
 * 게임이 시작할 때 뱀은 맨위 맨좌측, 길이는 1, 오른쪽 방향
 * 뱀 규칙
 * 뱀은 몸 길이를 늘려 머리를 다음칸에 위치
 * 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
 * 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
 * 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산
 */

public class 뱀 {

    // 우하좌상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N, K 입력
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        // 사과의 위치 입력
        int[][] map = new int[101][101];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 4; // 사과 놓기
        }

        // 뱀의 방향 변환 횟수 L
        int L = Integer.parseInt(br.readLine());
        // 뱀의 방향 변환 정보
        HashMap<Integer, Character> dirInfo = new HashMap<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            char C = st.nextToken().charAt(0);
            dirInfo.put(X, C);
        }

        // 처음 뱀의 위치
        int headR = 1;
        int headC = 1;
        map[headR][headC] = 1;
        // 처음 뱀의 방향, 오른쪽
        int d = 0;
        // 뱀이 차지하고 있는 위치 정보(꼬리가 앞쪽)
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(headR, headC));

        // 게임이 몇 초에 끝나는 지
        int time = 0;

        while (true) {
            // 게임 시간 추가
            time++;

            // 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치
            headR += dr[d];
            headC += dc[d];
            
            // 벽 또는 자기자신의 몸과 부딪히면 게임 끝
            // 맵 범위 안에 있고 뱀이 아닌 위치라면
            if (headR >= 1 && headC >= 1 && headR <= N && headC <= N && map[headR][headC] != 1) {
                // 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
                if (map[headR][headC] == 4) {
                    map[headR][headC] = 1;
                    q.offer(new Point(headR, headC));
                } else { // 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
                    map[headR][headC] = 1;
                    q.offer(new Point(headR, headC));
                    // 꼬리가 위치한 칸을 비워주기
                    Point p = q.poll();
                    // 꼬리 위치 바꾸기
                    map[p.r][p.c] = 0;
                }
            } else break;

            // time초가 끝난 뒤에 방향 회전
            // 뱀의 방향 변환 정보 확인
            if (dirInfo.containsKey(time)) {
                // 반시계 방향 90도 회전
                if (dirInfo.get(time) == 'L') {
                    if (d == 0) d = 3;
                    else d -= 1;
                } else { // 시계 방향 90도 회전
                    d = (d + 1) % 4;
                }
            }
        }
        System.out.println(time);
    }
}
