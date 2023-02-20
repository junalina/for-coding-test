package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 정렬된 배열에서 특정 수의 개수 구하기
 *
 * N개의 원소를 포함하고 있는 수열이 오름차순으로 정렬되어 있음
 * 이 수열에서 x가 등장하는 횟수를 계산
 * 시간 복잡도 O(logN)으로 설계
 *
 * input1
 * 7 2
 * 1 1 2 2 2 2 3
 * output1
 *
 * input2
 * 7 4
 * 1 1 2 2 2 2 3
 * output2
 * -1
 */
public class 정렬된배열에서특정수의개수구하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N, x 공백 구분하여 입력
        int N = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        // 리스트에 수열 담기
        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        // 등장하는 횟수
        int cnt = 0;
        while (true) {
            /* Collections.binarySearch(list, x)
            검색에 성공한 경우
            x와 일치하는 요소의 인덱스를 반환
            만약 일치하는 요소가 여러 개 있다면 무작위의 인덱스를 반환
            검색에 실패한 경우
            음수의 숫자를 반환
             */
            int index = Collections.binarySearch(list, x);
            if (index >= 0) {
                cnt++;
                list.remove(list.get(index));
            } else break;
        }

        if (cnt == 0) System.out.println(-1);
        else System.out.println(cnt);
    }
}
