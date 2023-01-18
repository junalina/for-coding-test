import java.util.*;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/60061

기둥과 보 설치

기둥과 보를 이용하여 벽면 구조물을 세우는 로봇 개발
기둥과 보는 길이가 1인 선분

기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.

벽면의 크기 n, 기둥과 보를 설치하거나 삭제하는 작업이 순서대로 담긴 2차원 배열 build_frame이 매개변수로 주어질 때, 모든 명령어를 수행한 후 구조물의 상태를 return
*/

class Solution {

    static class Structure implements Comparable<Structure> {
        int x, y, a;

        public Structure (int x, int y, int a) {
            this.x = x;
            this.y = y;
            this.a = a;
        }

        @Override
        public int compareTo(Structure other) {
            if (this.x == other.x && this.y == other.y) return Integer.compare(this.a, other.a);
            else if (this.x == other.x) return Integer.compare(this.y, other.y);
            else return Integer.compare(this.x, other.x);
        }
    }

    // 현재 설치된 구조물이 '가능한' 구조물인지 확인하는 함수
    public boolean possible(ArrayList<ArrayList<Integer>> answer) {
        for (int i = 0; i < answer.size(); i++) {
            int x = answer.get(i).get(0);
            int y = answer.get(i).get(1);
            int stuff = answer.get(i).get(2);
            if (stuff == 0) { // 설치된 것이 '기둥'인 경우
                boolean check = false;
                // '바닥 위'라면 정상
                if (y == 0) check = true;
                // '보의 한 쪽 끝 부분 위' 혹은 '다른 기둥 위'라면 정상
                for (int j = 0; j < answer.size(); j++) {
                    if (x - 1 == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)) {
                        check = true;
                    }
                    if (x == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)) {
                        check = true;
                    }
                    if (x == answer.get(j).get(0) && y - 1 == answer.get(j).get(1) && 0 == answer.get(j).get(2)) {
                        check = true;
                    }
                }
                if (!check) return false; // 아니라면 거짓(False) 반환
            }
            else if (stuff == 1) { // 설치된 것이 '보'인 경우
                boolean check = false;
                boolean left = false;
                boolean right = false;
                // '한쪽 끝부분이 기둥 위' 혹은 '양쪽 끝부분이 다른 보와 동시에 연결'이라면 정상
                for (int j = 0; j < answer.size(); j++) {
                    if (x == answer.get(j).get(0) && y - 1 == answer.get(j).get(1) && 0 == answer.get(j).get(2)) {
                        check = true;
                    }
                    if (x + 1 == answer.get(j).get(0) && y - 1 == answer.get(j).get(1) && 0 == answer.get(j).get(2)) {
                        check = true;
                    }
                    if (x - 1 == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)) {
                        left = true;
                    }
                    if (x + 1 == answer.get(j).get(0) && y == answer.get(j).get(1) && 1 == answer.get(j).get(2)) {
                        right = true;
                    }
                }
                if (left && right) check = true;
                if (!check) return false; // 아니라면 거짓(False) 반환
            }
        }
        return true;
    }

    public int[][] solution(int n, int[][] build_frame) {
        ArrayList<ArrayList<Integer>> answer = new ArrayList<>();

        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0]; // 가로 좌표
            int y = build_frame[i][1]; // 세로 좌표
            int a = build_frame[i][2]; // 기둥(0) or 보(1)
            int b = build_frame[i][3]; // 삭제(0) or 설치(1)

            // 구조물을 삭제하는 경우
            if (b == 0) {
                int index = 0;
                for (int j = 0; j < answer.size(); j++) {
                    if (x == answer.get(j).get(0) && y == answer.get(j).get(1) && a == answer.get(j).get(2)) {
                        index = j;
                    }
                }
                ArrayList<Integer> erased = answer.get(index);
                answer.remove(index);
                // 설치 가능한 구조물인지 확인
                if(!possible(answer)) {
                    answer.add(erased); // 아니면 다시 추가
                }
            }
            // 구조물을 설치하는 경우
            else if (b == 1) {
                ArrayList<Integer> inserted = new ArrayList<>();
                inserted.add(x);
                inserted.add(y);
                inserted.add(a);
                // 일단 설치
                answer.add(inserted);
                // 설치 가능한 구조물인지 확인
                if(!possible(answer)) {
                    answer.remove(answer.size() - 1); // 아니면 다시 삭제
                }
            }
        }

        // 정렬
        ArrayList<Structure> ans = new ArrayList<>();
        for (int i = 0; i < answer.size(); i++) {
            ans.add(new Structure(answer.get(i).get(0), answer.get(i).get(1), answer.get(i).get(2)));
        }
        Collections.sort(ans);

        int[][] res = new int[ans.size()][3];
        for (int i = 0; i < ans.size(); i++) {
            res[i][0] = ans.get(i).x;
            res[i][1] = ans.get(i).y;
            res[i][2] = ans.get(i).a;
        }

        return res;
    }
}