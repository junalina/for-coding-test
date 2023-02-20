package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * forcodingtest.탑승구
 * <p>
 * 1 ~ G번까지의 forcodingtest.탑승구
 * P개의 비행기 도착 예정
 * 다른 비행기가 도킹하지 않은 탑승구에만 도킹 가능
 * P개의 비행기를 순서대로 도킹하다 어떠한 탑승구에도 도킹할 수 없는 비행기가 나오는 경우 공항 운행 중지
 * 비행기를 최대 몇 대 도킹할 수 있는지 출력
 * input1
 * 4
 * 3
 * 4
 * 1
 * 1
 * output1
 * 2
 * input2
 * 4
 * 6
 * 2
 * 2
 * 3
 * 3
 * 4
 * 4
 * output2
 * 3
 */
public class 탑승구 {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 탑승구의 수
        int G = Integer.parseInt(br.readLine());
        // 비행기의 수
        int P = Integer.parseInt(br.readLine());

        // 부모 배열 초기화
        parent = new int[G + 1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        // 탑승구의 정보
        int result = 0;
        for (int i = 0; i < P; i++) {
            int x = Integer.parseInt(br.readLine());
            int root = findParent(x); // 현재 비행기의 탑승구의 루트 확인
            if (root == 0) break; // 현재 루트가 0이라면, 종료
            unionParent(root, root - 1); // 그렇지 않다면 바로 왼쪽의 집합과 합치기
            result++;
        }
        System.out.println(result);
    }

    private static void unionParent(int a, int b) {
        a = findParent(a);
        b = findParent(b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    private static int findParent(int x) {
        if (x == parent[x]) return x;
        else return parent[x] = findParent(parent[x]);
    }

}
