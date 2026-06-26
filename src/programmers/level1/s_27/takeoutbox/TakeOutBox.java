package programmers.level1.s_27.takeoutbox;

import java.util.*;

public class TakeOutBox {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int result = sol.solution(6, 2, 4);
        System.out.println( result );
    }
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

        // 두 정수의 나눗셈 올림 방법
        int h = ( n - 1 ) / w + 1;

        // 마지막 열에 대한 배열을 너비만큼만 생성
        int[] boxLastRow = new int[w];
        int startNum = n - (n % w) + 1; // 마지막열 시작 숫자
        System.out.println(startNum);
        int idx = h % 2 == 0 ? w - 1 : 0;
        while( startNum <= n ){
            if( h % 2 == 0 ){
                boxLastRow[idx] = startNum;
                startNum++;
                idx--;
            }else{
                boxLastRow[idx] = startNum;
                startNum++;
                idx++;
            }
        }
        System.out.println( Arrays.toString( boxLastRow ));

        int index = -1;
        int x = -1;
        int y = ( num - 1 ) / w; // 타겟 y좌표

        // 기본 인덱스와 y값에 따른 x 인덱스 변화
        index = num % w == 0 ? ( w - 1 ) : ( num % w - 1 );
        x = y % 2 == 0 ? index : ( w - 1 - index ); // 타겟 x좌표
        System.out.println("x : " + x + " y : " + y);

        if( n % w == 0 ) return h - y;

        if( boxLastRow[ x ] == 0) return h - y - 1;
        else return h - y;

    }
}
// 1  2  3
// 6  5  4
// 7  8  9
// 12 11 10
// 13
// 6 -> 이제 같은 x좌표에 얼마나 있는지 확인. 마지막 줄만 확인하면 됨.
