package programmers.hash;

import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/42577

전화번호 목록
전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인
접두어인 경우가 있으면 false, 그렇지 않으면 true return

풀이
String
*/

class PG_42577_전화번호목록 {
    public boolean solution(String[] phone_book) {
        int length = phone_book.length;

        // 오름차순 정렬
        Arrays.sort(phone_book);

        for (int i = 0; i < length - 1; i++) {
            String str1 = phone_book[i];
            String str2 = phone_book[i + 1];
            if (str2.startsWith(str1)) return false;
        }

        return true;
    }
}
