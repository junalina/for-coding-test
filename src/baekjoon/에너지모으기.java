package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/16198
 * <p>
 * 에너지 모으기
 * 풀이
 * dfs
 */
public class 에너지모으기 {

    static int N, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 에너지 구슬의 개수
        N = Integer.parseInt(br.readLine());
        // 에너지 구슬의 무게
        ArrayList<Integer> weights = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            weights.add(Integer.parseInt(st.nextToken()));
        }

        dfs(weights, 0);

        System.out.println(max);

    }

    private static void dfs(ArrayList<Integer> weights, int sum) {
        if (weights.size() == 2) {
            if (max < sum) max = sum;
            return;
        }
        for (int i = 1; i < weights.size() - 1; i++) {
            int marble = weights.get(i);
            int energy = weights.get(i - 1) * weights.get(i + 1);
            weights.remove(i);
            dfs(weights, sum + energy);
            weights.add(i, marble);
        }
    }
}
