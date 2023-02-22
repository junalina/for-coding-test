package review;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class 문자열재정렬_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        ArrayList<Character> characters = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c >= 'A' && c <= 'Z') characters.add(c);
            else sum += (c - '0');
        }

        Collections.sort(characters);

        String answer = "";
        for (int i = 0; i < characters.size(); i++) {
            answer += characters.get(i);
        }
        System.out.println(answer + sum);
    }
}
