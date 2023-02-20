package forcodingtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/10825
 *
 * forcodingtest.국영수
 *
 * 도현이네 반 N명의 국어, 영어, 수학 점수가 주어짐
 * 학생의 성적을 정렬
 * 1. 국어 점수가 감소하는 순서로 (국어점수 내림차순)
 * 2. 국어 점수가 같으면 영어 점수가 증가하는 순서로 (영어점수 오름차순)
 * 3. 국어 점수와 영어 점수가 같으면 수학 점수가 감소하는 순서로 (수학점수 내림차순)
 * 4. 모든 점수가 같으면 이름이 사전 순으로 증가하는 순서로 (단, 아스키 코드에서 대문자는 소문자보다 작으므로 사전순으로 앞에 온다.) (이름 오름차순)
 */
public class 국영수 {
    static class Score implements Comparable<Score>{
        String name;
        int korean, english, math;

        public Score(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Score o) {
            if (this.korean == o.korean && this.english == o.english && this.math == o.math) return this.name.compareTo(o.name); // 이름 오름차순
            else if (this.korean == o.korean && this.english == o.english) return o.math - this.math; // 수학점수 내림차순
            else if (this.korean == o.korean) return this.english - o.english; // 영어점수 오름차순
            else return o.korean - this.korean; // 국어점수 내림차순
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        // 도현이네 반의 학생의 수 N
        int N = Integer.parseInt(br.readLine());
        ArrayList<Score> scores = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            scores.add(new Score(name, korean, english, math));
        }

        Collections.sort(scores);

        for (Score score : scores) {
            sb.append(score.name).append("\n");
        }

        System.out.println(sb);
    }
}
