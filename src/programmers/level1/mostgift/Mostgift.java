package programmers.level1.mostgift;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Mostgift {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] friends = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
        int result = sol.solution(friends, gifts);

        System.out.println("result = " + result);
    }
}

// 이번 달 기록을 바탕으로 (데이터) 다음 달에 누가 선물을 많이 받을 지 예측(결과)
// 조건1. 두 사람이 선물을 주고 받았다면 이번 달까지 두 사람 사이에 더 많은 선물을 준 사람이 다음 달에 선물 하나 받음.
// 조건2. 두 사람이 선물을 주고받은 기록이 하나도 없거나, 주고 받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물 하나를 받음.
// 선물 지수는 이번 달까지 자신이 친구들에게 준 선물의 수에서 받은 선물의 수를 뺀 값.
// 만약 선물 지수까지 같다면 다음 달에 선물을 주고 받지 않습니다.
// friends => 친구들, gifts => A B  A -> B 선물 준 것.
// 해결방안
// STEP 1. 선물지수를 구하기 위해 선물을 준 경우 카운트, 선물을 받은 경우 카운트
// STEP 2. friend 기준으로 본인을 제외한 나머지의 관계 파악
// STEP 2.1 3가지중 하나. 내가 더 많이 선물을 받았거나, 줬거나, 같거나 or 없거나
// STEP 3 STEP2.1 에 대해 다음 달에 받을 선물 카운트!
// 예시 4명이라고 하면  1 -> 2,3,4    2 -> 3,4    3 -> 4 총 3번 관계 파악
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        // 이름, 길이2인 배열 선언 및 초기화
        Map<String, Integer> giftIndex = new HashMap<>();

        // 관계
        Map< String, Map<String, Integer> > relationship = new HashMap<>();

        for( String friend : friends ){
            relationship.put( friend, new HashMap<>());
        }

        // 선물지수 저장
        for( String gift : gifts ){
            String sender = gift.split(" ")[0];
            String receiver = gift.split(" ")[1];

            Map<String, Integer> giveCounter = relationship.get(sender);
            giveCounter.put(receiver, giveCounter.getOrDefault(receiver, 0) + 1);

            giftIndex.put(sender, giftIndex.getOrDefault(sender, 0) + 1);
            giftIndex.put(receiver, giftIndex.getOrDefault(receiver, 0) + -1);
        }

        // 선물 지수 확인
        for( String user : giftIndex.keySet() ){
            System.out.println( "user: " + user + "  score: " + giftIndex.get(user) );
        }
        System.out.println("--------------------------------------");

        // 선물 주고 받은 내역 확인
        for(String sender : relationship.keySet() ){
            Map<String, Integer> giveCounter = relationship.get(sender);
            System.out.println( "sender: " + sender );
            for(String receiver : giveCounter.keySet() ){
                System.out.println( receiver + " : " + giveCounter.get( receiver ) );
            }
        }

        // ryan, muzi, neo, frodo
        // 0 ,   1,    2,   3
        // 이제 하나씩 비교하면서 체크
        for(int i = 0; i < friends.length; i++){
            int count = 0;
            String sender = friends[i];
            Map<String, Integer> giveCount = relationship.get(friends[i]);

            for(int j = i+1; j < friends.length; j++){
                String receiver = friends[j];


            }
        }



        return answer;
    }
}
