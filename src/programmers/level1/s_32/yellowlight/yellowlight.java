package programmers.level1.s_32.yellowlight;

import java.util.Arrays;

public class yellowlight {

    public static void main(String[] args) {
        Solution sol = new Solution();
        int result = sol.solution(new int[][]{{2,3,2}, {3,1,3}, {2, 1, 1}}); // 기댓값 13
        System.out.println( "답: " + result );
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

        int[][] signalTime = new int[signals.length][3];

        // 각 배열 지속 시간 넣기
        for( int i = 0; i < signalNum; i++ ){
            startPoint[i] = signals[i][0] + 1; // 시작 지점
            yellow[i] = signals[i][1]; // 노란불 길이 1
            sum[i] = signals[i][0] + signals[i][1] + signals[i][2]; // 초록불 + 노란불 + 빨간불

            signalTime[i][0] = signals[i][0];
            signalTime[i][1] = signals[i][0] + signals[i][1];
            signalTime[i][2] = signals[i][0] + signals[i][1] + signals[i][2];
        }

        System.out.println("시작 지점");
        System.out.println( Arrays.toString( startPoint ) );
        System.out.println("\n노란불 지속시간");
        System.out.println( Arrays.toString( yellow ) );
        System.out.println("\n전체 지속시간 합");
        for (int[] time : signalTime) {
            System.out.println(Arrays.toString(time));
        }


        // 시작지점 부터 노란불 지속 시간 - 그 외 지속시간 - 노란불 - 그 외 반복
        // -1를 반환하는 기준: 신호등 sum 배열 중 같은 배열이 존재하고 그 두 배열이 서로 첫번째 루프때 겹치지 않는다면.
        // 최소공배수를 찾는 로직을 도입하여 계산.
        int lcm = getLCM( sum );
        System.out.println( "최소공배수(lcm): " + lcm );

        int currentPos = 1; // 현재 위치

        // 현재위치가 lcm(최소공배수)를 넘길 경우 -1 return
        // 시작위치부터 하나씩 살펴보면서 각 배열에 노란불이 켜지는 지 확인.
        // 수학적으로 생각하자. currentPos % sum[i] ( 만약 2 1 2 이면 0~4까지 존재하게 되고 0,1 이 초록불, 2가 노란불, 3이 빨간불이 될 것이다. )
        // signals[i][0] 보다 작으면 초록불 signals[i][1] 보다 같거나 크고 signals[i][2] 보다 작으면 노란불 !!
        // 확인시 노란불이 배열의 크기만큼(3개의 신호등이면 3) 켜지면 그 값을 리턴
        while( currentPos < lcm ){
            int count = 0;
            for(int i = 0; i < signalTime.length; i++){
                int check = currentPos % sum[i];
                if( check > signalTime[i][0] && check <= signalTime[i][1] ) count++;
            }

            if( count == signalTime.length ) return currentPos;

            currentPos++;
        }


        return -1;
    }

    // 최대 공약수 구하는 함수
    public int GCD(int num1, int num2){
        if( num2 == 0 ) return num1;
        return GCD(num2, num1 % num2);
    }

    // 최소 공배수 구하는 함수
    public int LCM(int num1, int num2){
        return num1 / GCD( num1, num2 ) * num2; // 오버플로우 되지 않게
    }

    // 최소 공배수 배열 구하는 함수
    public int getLCM(int[] numbers){
        if(numbers == null || numbers.length == 0) return 0;

        // 배열의 첫 번째 값으로 시작
        int first = numbers[0];

        // 두번째 값부터 순차적으로 누적하여 LCM 계산
        for( int i = 1; i < numbers.length; i++ ){
            first = LCM( first, numbers[i] );
        }

        return first;
    }

}