package programmers.level1.report;

import java.util.*;

public class Report {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] result = sol.solution(new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"},
                2);

        System.out.println( Arrays.toString( result ) );
    }
}
// 불량 이용자 신고 처리하고 메일로 발송하는 시스템 개발
// 각 유저는 한 번에 한 명의 유저를 신고할 수 있다.
// 신고 횟수에 제한은 없다.
// 한 유저를 여러 번 신고할 수 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리된다.
// k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유지에게 정지 사실을 메일로 보낸다.
// 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록
// 셋으로 저장시키고 다 끝나면 모든 셋의 값들을 추가하도록
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Set<String> reportedList = new LinkedHashSet<>(Arrays.asList(report));

        Map<String, Integer> reportCountMap = new HashMap<>();
        Map<String, Integer> idCountMap = new HashMap<>();

        for(int i = 0; i < id_list.length; i++){
            idCountMap.put(id_list[i], i);
        }

        for(String s : reportedList){
            String reportedUser = s.split(" ")[1];

            reportCountMap.put(reportedUser, reportCountMap.getOrDefault(reportedUser, 0) + 1);
        }

        for(String s : reportedList){
            String reporter = s.split(" ")[0];
            String reportedUser = s.split(" ")[1];

            if(reportCountMap.getOrDefault(reportedUser, 0) >= k){
                int index = idCountMap.get(reporter);
                answer[index]++;
            }
        }

        return answer;
    }
}
