package programmers.level1.bandagewarapping;

public class BandWarp {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int result = sol.solution(new int[]{5, 1, 5}, 30, new int[][]{ {2, 10}, {9, 15}, {10, 5}, {11, 5}} );
        System.out.println( "결과:" + result );
    }
}

// bandage는 시전 시간, 초당 회복량, 추가 회복량
// attacks는 공격 시간, 피해량으로 이루어진 2차원 배열
// health는 체력 1~1000
// 0이하면 죽음, 최대체력이상 회복 불가능
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int FullTime = attacks[attacks.length-1][0]; // 마지막 공격 시점
        int band_time = bandage[0]; // 붕대 감는 시간
        int maxHp = health; // 최대체력 설정
        // 공격 시점 전까진 붕대를 감는다.
        // 공격받으면 시간 초기화
        int count = 0;
        int idx = 0; // 공격 배열 포인터
        for(int time = 1; time <= FullTime; time++){

            // 공격 시점 연속 성공 초기화 *우선순위 공격이 높음 회복보다*
            if( attacks[idx][0] == time ){
                health -= attacks[idx][1]; // 체력깎기
                idx++; // 다음 공격시간으로 포인터 옮기기
                if( health <= 0 ) return -1; // 체력이 0이하로 떨어지면 -1 반환
                count = 0; // 연속성공 초기화
            } else{
                // 공격을 안 받았다면 기본 회복
                health += bandage[1]; // 회복
                count++; // 연속성공 1 증가

                // band_time == count가 될 경우 추가회복 후 연속성공 초기화
                if( band_time == count ){
                    health += bandage[2];
                    count = 0;
                }
                if( health > maxHp ){ // 최대체력 이상이면 최대체력으로 설정
                    health = maxHp;
                }
            }
        }
        return health;
    }
}
