package programmers.level1.takeoutbox;

import java.util.*;

public class TakeOutBox {
}

// n개의 상자를 아래부터 쌓는다. 가로의 넓이는 w
// 왼쪽부터 쌓고 오른쪽으로 쌓고 지그재그 형식으로 쌓는다.
// num에 해당하는 상자를 꺼낼려면 몇 개의 상자를 꺼내야하는지 return
// 13 , 3 , 6 인 경우
// STEP1. 꼭대기 층이 몇층인지 게산, 계산 후  홀수 층이면 왼쪽부터이고 짝수이면 오른쪽부터 쌓아짐.
// STEP2. 찾는 박스가 몇층 몇번째인지 계산해서 위로 몇개가 있는지 계산해서 반환.
// 배열을 만들때 13, 3 이면 -> 5 x 3  13 / 3
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;

        // 두 정수의 나눗셈 올림 방법
        int h = ( n - 1 ) / w + 1;

        // 너비와 높이를 가지고 2차원 박스 공간 배열을 생성
        int[][] boxArea = new int[h][w];

        int x = -1;
        int y = ( num - 1 ) / w + 1; // 타겟 y좌표

        // 타겟 y좌표가 짝수면 오른쪽부터 거꾸로 쓰여 있음
        if( y % 2 == 0 ){
            x =  num % w - 1;
        }else{
            x = w - (num % w) - 1;
        }

        return answer;
    }
}