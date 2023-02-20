package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15686
 *
 * forcodingtest.치킨배달
 * N x N 도시, 1 x 1 크기의 칸으로 나누어짐
 * 빈 칸, 치킨집, 집
 * 치킨 거리 : 집과 가장 가까운 치킨집 사이의 거리
 * 도시의 치킨 거리 : 모든 집의 치킨 거리의 합
 * |r1-r2| + |c1-c2|
 * 0은 빈 칸, 1은 집, 2는 치킨집
 * 프랜차이즈 본사에서 일부 치킨집을 폐업시키려고 함
 * 가장 수익을 많이 낼 수 있는 치킨집의 개수 최대 M
 * 도시에서 M개를 고르고 나머지 모두 폐업
 * 폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력
 *
 * 풀이
 * 1. 조합으로 치킨집 M개 선택
 * 2. 선택된 조합으로 집마다 치킨집 최솟값들 더함
 * 3. 최솟값을 구해서 결과 갱신
 */

public class 치킨배달 {

    static int N, M, homeSize, chickenSize, ans;
    static int[] isSelected;

    static ArrayList<Point> home = new ArrayList<>();
    static ArrayList<Point> chicken = new ArrayList<>();

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, M 공백 구분하여 입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int n = Integer.parseInt(st.nextToken());
                // 집 추가
                if (n == 1) home.add(new Point(r, c));
                    // 치킨집 추가
                else if (n == 2) chicken.add(new Point(r, c));
            }
        }

        // 집의 개수
        homeSize = home.size();
        // 치킨집의 개수
        chickenSize = chicken.size();
        // 선택된 치킨집 배열
        isSelected = new int[M];
        // 도시의 치킨 거리의 최솟값
        ans = Integer.MAX_VALUE;

        comb(0, 0);

        System.out.println(ans);
    }

    private static void comb(int cnt, int start) {
        if(cnt == M) {
            int res = calDistance();
            ans = Math.min(ans, res);
            return;
        }
        for (int i = start; i < chickenSize; i++) {
            isSelected[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    private static int calDistance() {
        int sum = 0;

        // 폐업시키지 않을 치킨집 M개
        ArrayList<Point> newChicken = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            newChicken.add(chicken.get(isSelected[i]));
        }

        for (int i = 0; i < homeSize; i++) {
            int min = Integer.MAX_VALUE;
            // 집
            Point h = home.get(i);
            int hr = h.r;
            int hc = h.c;
            // 치킨집들
            for (int j = 0; j < newChicken.size(); j++) {
                Point c = newChicken.get(j);
                int cr = c.r;
                int cc = c.c;
                int dis = Math.abs(hr - cr) + Math.abs(hc - cc);
                min = Math.min(min, dis);
            }
            sum += min;
        }
        return sum;
    }
}
