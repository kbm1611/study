package programmers.level1.s_32.yellowlight;

import java.util.Arrays;

public class yellowlight {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int result = sol.solution(new int[][]{{2,1,2}, {5,1,1}}); // 기댓값 13
        System.out.println( result );
    }
}

// 차량 신호등 n개, 모든 신호등은 항상 초록불 -> 노란불 -> 빨간불 순서로 반복되며  각 신호의 지속 시간은 신호등마다 다름.
// 시간은 1초부터 시작하며, 각 신호등은 처음에는 초록불로 시작.
// 가끔 정전이 일어남. 모든 신호등이 모두 노란불이 되면 정전이 발생함.
// 모든 신호등이 노란불이 되는 가장 빠른 시각을 return 하도록 하는 solution 함수 완성.
// 만약 모든 신호등이 노란불이 되는 경우가 존재하지 않는다면 -1 return
// 이문제에서 일단 노란불 / 그 외의 불 지속시간 이렇게 2가지로 나뉘어서 확인하면 된다.
// 초록불이나 빨간불일 때는 0을 노란불일 때는 1을 저장시키도록하는 함수가 있게 하고 1초가 지날때마다 매번 전부 1인지를 판단?
// -> 너무 많은 계산이 들어가지는 않는지?
class Solution {
    public int solution(int[][] signals) {
        int answer = 0;

        int signalNum = signals.length;

        int[] startPoint = new int[signalNum]; // 시작 지점
        int[] yellow = new int[signalNum]; // 지속시간
        int[] sum = new int[signalNum]; // 지속시간 전체 합

        // 각 배열 지속 시간 넣기
        for( int i = 0; i < signalNum; i++ ){
            startPoint[i] = signals[i][0] + 1; // 시작 지점
            yellow[i] = signals[i][1]; // 노란불 길이 1
            sum[i] = signals[i][0] + signals[i][1] + signals[i][2]; // 초록불 + 노란불 + 빨간불
        }

        System.out.println("시작 지점");
        System.out.println( Arrays.toString( startPoint ) );
        System.out.println("\n노란불 지속시간");
        System.out.println( Arrays.toString( yellow ) );
        System.out.println("\n전체 지속시간 합");
        System.out.println( Arrays.toString( sum ) );

        // 시작지점 부터 노란불 지속 시간 - 그 외 지속시간 - 노란불 - 그 외 반복
        // -1를 반환하는 기준: 신호등 sum 배열 중 같은 배열이 존재하고 그 두 배열이 서로 첫번째 루프때 겹치지 않는다면.
        // 최소공배수를 찾는 로직을 도입하여 계산.
        int gcd = getGCD( sum );

        // 서로소 인 경우(1)
        if( gcd == 1 ){
            for (int num : sum) {
                gcd *= num;
            }
        }

        int startPos = 0; // 시작위치

        // 시작위치가 gcd(최소공배수)를 넘길 경우 -1 return
        while( startPos < gcd ){

        }


        return answer;
    }

    // 최대 공약수 구하는 함수
    public int GCD(int num1, int num2){
        if( num2 == 0 ) return num1;
        return GCD(num2, num1 % num2);
    }

    // 최소 공약수 구하는 함수
    public int LCM(int num1, int num2){
        return num1 * num2 / GCD( num1, num2 );
    }

    public int getGCD(int[] numbers){
        if(numbers == null || numbers.length == 0) return 0;

        // 배열의 첫 번째 값으로 시작
        int first = numbers[0];

        // 두번째 값부터 순차적으로 누적하여 GCD 계산
        for( int i = 1; i < numbers.length; i++ ){
            first = GCD( first, numbers[i] );

            // 중간에 최대공약수가 1이 되면 더 이상 계산할 필요없이 서로소임.
            if( first == 1 ) return 1;
        }

        return first;
    }

}