package programmers.level1.videoplayer;

public class VideoPlayer {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String result = sol.solution("10:55", "00:05", "00:15", "06:55", new String[]{"prev", "next", "next"});
        System.out.println( "결과: " + result );
    }
}
// 동영상 재생기
// 10초 전으로 이동, 10초 후로 이동, 오프닝 건너뛰기 3가지 기능 지원
// 사용자가 prev 명령 입력 시 현재 위치에서 10초 전으로 이동, 10초 미만인 경우 처음위치(0분 0초)로
// 사용자가 next 명령 입력 시 현재 위치에서 10초 후로 남은 시간이 10초 미만이면 마지막 위치로
// 오프닝 건너뛰기: 현재 재생 위치가 오프닝 구간( op_start <= 현재 재생 위치 <= op_end )인 경우 자동으로 오프닝이 끝나는 위치로 이동.
// 동영상 길이, 기능이 수행되기 직전의 재생위치를 나타내는 문자열, 오프닝 시작 시각을 나타내는 문자열, 오프닝 끝나는 시각을 나타내는 문자열, 사용자의 입력을 나타내는 1차원 문자열 배열
// 사용자의 입력이 모두 끝난 후 동영상의 위치를 "mm:ss" 형식으로 return 하는 solution 함수 완성
class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        // 각 시간들을 정수로 변환
        int video_lens = Integer.parseInt(video_len.replace(":", ""));
        System.out.println("video_lens = " + video_lens);
        int poss = Integer.parseInt(pos.replace(":", ""));
        System.out.println("poss = " + poss);
        int op_starts = Integer.parseInt(op_start.replace(":", ""));
        System.out.println("op_starts = " + op_starts);
        int op_ends = Integer.parseInt(op_end.replace(":", ""));
        System.out.println("op_ends = " + op_ends);

        // 명령어 배열의 길이만큼 반복
        for( int i = 0; i < commands.length; i++){
            // 현재 위치가 사이면 끝나는 위치로 이동.
            if(op_starts <= poss && poss <= op_ends) poss = op_ends;

            // 10초 건너 뛰기
            // 고려사항: 영상 마지막 처리, 시간 처리
            if( commands[i].equals("prev") ){ // "prev"
                // 0보다 작아지는 지 확인
                if( (poss % 100) < 10 ) poss -= 50;
                else poss -= 10;

                if( poss < 0) poss = 0;
            }else{ // "next"
                // 마지막 영상시간보다 커지는 지 확인
                if( (poss % 100) >= 50 ) poss += 50;
                else poss += 10;

                if( poss  > video_lens ) poss = video_lens;
            }
            if(op_starts <= poss && poss <= op_ends) poss = op_ends;
            System.out.println((i+1) + "번째 사이클" + poss);
        }
        System.out.println( poss );

        // poss를 문자 형태로 다시 변환
        int min = poss / 100; //분
        int sec = poss % 100; //초

        String minute = min < 10 ? "0" + min : "" + min;
        String second = sec < 10 ? "0" + sec : "" + sec;

        answer = minute + ":" + second;
        return answer;
    }
}
