import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 고정점 : 수열의 원소 중에서 값이 인덱스와 동일한 원소
 * 하나의 수열이 N개의 서로 다른 원소를 포함하고 있으며 정렬되어 있다. 이 수열에서 고정점이 있다면 고정점을 출력
 * 고정점은 최대 1개만 존재
 * 없으면 -1 출력
 *
 * input1
 * 5
 * -15 -6 1 3 7
 * output1
 * 3
 *
 * input2
 * 7
 * -15 -4 2 8 9 13 15
 * output2
 * 2
 *
 * input3
 * 7
 * -15 -4 3 8 9 13 15
 * output3
 * -1
 */
public class 고정점찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 원소
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(binarySearch(arr, 0, N-1));
    }

    private static int binarySearch(int[] arr, int start, int end) {
        if (start > end) return -1;
        int mid = (start + end) / 2;
        // 고정점을 찾은 경우
        if (arr[mid] == mid) return mid;
        // 중간점의 값보다 중간점이 작은 경우 왼쪽 확인
        else if (arr[mid] > mid) return binarySearch(arr, start, mid - 1);
        // 중간점의 값보다 중간점이 큰 경우 오른쪽 확인
        else return binarySearch(arr, mid + 1, end);
    }

    public static void main2(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N개의 원소
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        int res = -1;

        // 리스트 돌면서 고정점 찾기
        for (int i : list) {
            int index = Collections.binarySearch(list, i);
            if (index == list.get(index)) {
                res = index;
                break;
            }
        }

        System.out.println(res);

    }
}
