package forcodingtest;

/*
https://school.programmers.co.kr/learn/courses/30/lessons/60059

비밀의 문
특이한 형태의 열쇠와 자물쇠

자물쇠 : N x N의 정사각 격자 형태
열쇠 : M x M의 정사각 격자 형태

자물쇠에는 홈, 열쇠에는 홈과 돌기
열쇠는 회전과 이동이 가능
열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열림
자물쇠 내에서 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 함
열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됨

열쇠로 자물쇠를 열 수 있으면 true, 없으면 false return
*/
class 자물쇠와열쇠 {
    public boolean solution(int[][] key, int[][] lock) {
        int kl = key.length;
        int ll = lock.length;
        int offset = kl - 1;

        for (int r = 0; r < offset + ll; r++) {
            for (int c = 0; c < offset + ll; c++) {
                for (int rot = 0; rot < 4; rot++) {
                    // 배열 중간에 자물쇠 배열 복사
                    int[][] arr = new int[58][58];
                    for (int i = 0; i < ll; i++) {
                        for (int j = 0; j < ll; j++) {
                            arr[offset + i][offset + j] = lock[i][j];
                        }
                    }
                    // 열쇠와 자물쇠 배열 합쳐보기
                    match(arr, key, rot, r, c);
                    // 자물쇠 배열의 값이 모두 1이면 열 수 있음
                    if(isOpen(arr, offset, ll)) return true;
                }
            }
        }
        return false;
    }

    void match(int[][] arr, int[][] key, int rot, int r, int c) {
        int l = key.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (rot == 0) {
                    arr[r + i][c + j] += key[i][j];
                } else if (rot == 1) { // 90도 회전
                    arr[r + i][c + j] += key[j][l - 1 - i];
                } else if (rot == 2) { // 180도 회전
                    arr[r + i][c + j] += key[l - 1 - i][l - 1 - j];
                } else { // 270도 회전
                    arr[r + i][c + j] += key[l - 1 - j][i];
                }
            }
        }
    }

    boolean isOpen(int[][] arr, int offset, int ll) {
        for (int i = offset; i < offset + ll; i++) {
            for (int j = offset; j < offset + ll; j++) {
                if(arr[i][j] != 1) return false;
            }
        }
        return true;
    }
}