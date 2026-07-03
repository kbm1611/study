package programmers.level1.s_33.spoGuard;

import java.util.*;

public class SpoGaurd {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String message = "this is test sample yeah";
        int[][] spoiler_ranges = { {0, 3}, {5,6}, {8,11}, {13, 18}, {20,23} };
        int result = sol.solution(message, spoiler_ranges);
        System.out.println( "답: " + result );
    }
}

// 클릭했을 때만 공개되는 스포 방지 기능 제공
// 왼쪽 -> 오른쪽 순서로 스포 방지 구간을 하나씩 클릭해 공개되는 단어들 중, 중요한 단어가 몇 개인지 확인하려 합니다.
// 단어는 공백으로 구분되며, 알파벳 소문자와 숫자로만 구성된 연속된 문자열
// 단어를 구성하는 문자들의 인덱스 중 하나 이상이 스포 방지 구간에 포함될 경우, 해당 단어는 스포일러 방지 단어로 간주함.
// 한 단어가 여러 개의 스포 방지 구간에 걸쳐 있을 수 있으며, 하나의 스포 방지 구간에 여러 단어 포함 가능.
// 스포 방지 구간을 클릭해 단어의 모든 문자가 공개되었을 때, 그 단어가 아래 조건을 모두 만족하면 중요한 단어.
// 1. 스포 방지 단어이어야 함.
// 2. 메시지의 스포 방지 구간이 아닌 구간(= 어떤 스포 방지 구간에도 속하지 않는 모든 구간: 각 구간의 앞, 사이, 뒤 포함)에 등장한 적이 없어야 함.
// 3. 이전에 공개된 스포 방지 단어와 중복되지 않아야 합니다.
// 4. 여러 단어가 동시에 공개된 경우, 왼쪽부터 순서대로 하나씩 중요한 단어인지 판단.
// 중요한 판단을 해야하는 부분
// 모두 스포일러 처리 될 때, 모두 스포일러 처리가 안될 때, 한 단어가 2개의 스포일러 구간에 존재할 때, 여러 단어가 1개의 스포일러 구간에 존재할 때, 모두 중복없이 1번만 등장할 때, 모든 스포방지 구간이 단어의 끝과 끝을 가리킬 때
class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;

        int[] spoiler_array = new int[message.length()];

        // 스포일러가 들어간 구간 1로 마킹
        for(int[] spo : spoiler_ranges){
            int start = spo[0];
            int end = spo[1];

            for(int j = start; j <= end; j++){
                spoiler_array[j] = 1;
            }
        }
        System.out.println(Arrays.toString(spoiler_array));

        // 이 로직의 핵심. 어떻게 스포일러 방지 구간에 단어와 연결시킬 것인가?
        // STEP1. 먼저 스포일러 구간에 해당하는 단어들이 무엇인지 확인. 그 단어들을 단어 배열에서 빼서 중요후보 단어배열에 넣음
        // STEP2. 중요후보 단어들 중 중요단어 배열에 안들어가 있으면서, 기본 단어장에 없는 단어가 중요단어가 됨.
        // 구간에 걸친 단어들을 어떻게 알 것인가?

        // 일단 단어들을 분리함. 일반 단어 배열
        List<String> words = new ArrayList<>(List.of(message.split(" "))); // 전체 단어장
        List<String> commonWords = new ArrayList<>(); // 비스포 단어장
        List<String> spolierWords = new ArrayList<>(); // 스포 단어장

        for(String word : words){
            System.out.print( word + " ");
        }
        System.out.println();

        // 일반 단어 배열과 1로 마킹한 스포일러 배열을 이용해 스포일러에 해당하는 단어들의 배열을 만듬.
        // -> 일반 단어 배열에서 제거함.
        int curPos = 0; // 현재 단어의 길이 시작점
        int start = 0;
        // 단어 배열을 순회하며 마킹한 스포일러 배열에 걸치는 지 확인.
        boolean isSpolier; // 스포 구간에 해당하는 단어인지 체크
        for(int i = 0; i < words.size(); i++ ){
            isSpolier = false;
            if( i != 0 ){ // 공백 1칸 + 한 칸 다음
                start = curPos + 2;
            }
            int end = start + words.get(i).length() - 1;

            System.out.println( "word = " + words.get(i) );
            System.out.println("word.size = " + words.size() );
            System.out.println("start = " + start + " end = " + end );

            for(int j = start; j <= end; j++){
                if (spoiler_array[j] == 1){ // 체킹되면
                    isSpolier = true;
                    System.out.println("체크!");
                    break; // 체크되면 바로 다음 단어 확인
                }
            }

            if(isSpolier){
                spolierWords.add( words.get(i) );
            }else{
                commonWords.add( words.get(i) );
            }

            curPos = end; // 현재 위치를 끝으로 이동

            System.out.println("curPos = " + curPos);
            System.out.println();
        }

        System.out.println("일반단어 후보 리스트");
        System.out.println(commonWords);

        System.out.println("중요단어 후보 리스트");
        System.out.println(spolierWords);

        // 후보 단어 배열을 확인하며 단어가 일반단어배열(words)과 중요한 단어(importWords)에 없다면 중요한 단어에 추가 아니면 패스
        // 중요 단어에 추가된다며 더이상 그 단어가 중복될 수는 없음.
        List<String> importWords = new ArrayList<>();
        // Set을 쓰니깐 20, 21, 73이 실패 start를 밖으로 빼니깐 11, 72이 실패 ==> start를 밖으로 빼고 curPos != 0 조건문을 i != 0 으로 1번만 실행하게 함. ?? 뭐지 단어가 보여야 curPos가 움직이는 거 아닌가?? 맨 처음에 띄어쓰기 있을 때 문제가 되나?

        // 후보 단어 리스트들을 확인
        for(String word : spolierWords){
            if( !commonWords.contains( word ) && !importWords.contains( word ) ){
                importWords.add( word );
            }
        }

        System.out.println("최종 중요 단어 리스트");
        System.out.println( importWords );

        answer = importWords.size();

        return answer;
    }
}