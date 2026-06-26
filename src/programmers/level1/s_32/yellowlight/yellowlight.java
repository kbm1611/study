package programmers.level1.s_32.yellowlight;

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

        // 노란불 지속시간 + 그 외의 지속시간
        int[] yellow = new int[signals.length];
        int[] etc = new int[signals.length];

        return answer;
    }
}