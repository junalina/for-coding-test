package javaforcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * N명의 학생 정보
 * 각 학생의 이름과 성적 정보가 주어졌을 때 성적이 낮은 순서대로 학생의 이름을 출력
 */
public class 성적이낮은순서로학생출력하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        // N을 입력받기
        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> map = new HashMap<>();

        // map에 데이터 추가
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int score = Integer.parseInt(st.nextToken());
            map.put(name, score);
        }

        // HashMap 오름차순 정렬
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        // Key만 출력
        for (Map.Entry<String, Integer> entry : entryList) {
            sb.append(entry.getKey()).append(" ");
        }
        System.out.println(sb);
    }
}
