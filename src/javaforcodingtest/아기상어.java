package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * https://www.acmicpc.net/problem/16236
 * <p>
 * 아기 상어
 * NxN 공간, 물고기 M마리, 아기 상어 1마리
 * 처음 아기 상어 크기 2
 * 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.
 * 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
 * 이동할지 결정하는 방법
 * 1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청
 * 2. 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 * 3. 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
 * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로 이동할 때, 지나야하는 칸의 개수의 최솟값이다.
 * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 * 아기 상어 이동은 1초, 물고기를 먹을 때마다 크기가 1 증가
 * 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력
 * <p>
 * 풀이
 * 구현
 */
public class 아기상어 {
    static int N;
    static int[][] map;

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Point implements Comparable<Point> {
        int r, c, dis;

        public Point(int r, int c, int dis) {
            this.r = r;
            this.c = c;
            this.dis = dis;
        }

        @Override
        public int compareTo(Point other) {
            // 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
            if (this.dis == other.dis) {
                if (this.r == other.r) return Integer.compare(this.c, other.c);
                return Integer.compare(this.r, other.r);
            }
            return Integer.compare(this.dis, other.dis);
        }
    }

    static class Shark {
        int r, c, size, eatCnt;

        public Shark(int r, int c, int size, int eatCnt) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.eatCnt = eatCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 공간의 크기
        N = Integer.parseInt(br.readLine());

        Shark shark = null;

        // 공간의 상태
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                // 상어라면 상어 위치 저장
                if (map[r][c] == 9) {
                    shark = new Shark(r, c, 2, 0);
                    map[r][c] = 0;
                }
            }
        }

        // 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간
        int total = 0;

        while (true) {
            // 먹을 수 있는 물고기 리스트
            ArrayList<Point> fishes = new ArrayList<>();
            // bfs
            Queue<Point> q = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            q.offer(new Point(shark.r, shark.c, 0));
            visited[shark.r][shark.c] = true;

            while (!q.isEmpty()) {
                Point p = q.poll();
                int r = p.r;
                int c = p.c;
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    // 배열 범위 밖이거나 방문했거나 물고기가 있는데 상어의 크기보다 크다면 무시
                    if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] > shark.size) continue;
                    q.offer(new Point(nr, nc, p.dis + 1));
                    visited[nr][nc] = true;
                    // 물고기이고 상어보다 크기가 작으면 먹을 물고기 리스트에 추가
                    if (map[nr][nc] != 0 && map[nr][nc] < shark.size) fishes.add(new Point(nr, nc, p.dis + 1));
                }
            }

            // 1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 끝
            if (fishes.size() == 0) break;
            // 물고기 리스트 정렬
            Collections.sort(fishes);
            // 먹을 물고기
            Point fish = fishes.get(0);
            map[fish.r][fish.c] = 0;
            total += fish.dis;
            // 물고기가 있던 위치로 상어 이동
            shark.r = fish.r;
            shark.c = fish.c;
            shark.eatCnt++;

            // 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가
            if (shark.eatCnt == shark.size) {
                shark.size++;
                // 먹은 물고기 수 초기화
                shark.eatCnt = 0;
            }
        }
        System.out.println(total);
    }

    private static boolean isIn(int nr, int nc) {
        return nr >= 0 && nr < N && nc >= 0 && nc < N;
    }
}
