package programmers.level2.s_1.desertIsland;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deserted_Island {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] maps = {"X591X","X1X5X","X231X", "1XXX1"};
        int[] result = sol.solution( maps );
        System.out.println();
        System.out.println("result\n" + Arrays.toString(result));
    }
}

// 지도에는 바다와 무인도들에 대한 정보가 표시돼 있습니다. 지도는 1x1 크기의 사각형들로 이루어진 직사각형 격자 형태
// X 또는 1~9 자연수, X는 바다, 숫자는 무인도를 나타냄. 이때 상화좌우로 연결되는 땅들은 하나의 무인도를 이룸.
// 지도의 각 칸에 적힌 숫자는 식량을 나타내는데, 상하좌우로 연결되는 칸에 적힌 숫자를 모두 합한 값은 해당 무인도에서 최대 며칠동안 머물 수 있는 지를 나타냅니다.
// 최대 며칠씩 머물 수 있는지 알아 본 후 놀러갈 섬을 결정하려 함.
// 오름차순으로 정렬하여 배열에 담음
// 만약 지낼 수 있는 무인도가 없다면 -1을 배열에 담아 return
class Solution {
    public int[] solution(String[] maps) {
        int[] answer = {};

        // 솔루션: 어떤 방법으로 무인도를 특정할 것인가?(조건: 상하좌우에 숫자로 연결되어 있다면 무인도임.)
        // 일단 직사각형에서 벗어나거나 X이면 무인도임.(숫자인 것 중에)
        // 길찾기 알고리즘이 들어가겠구나! 한 좌표에서 모든 연결된 좌표를 지나가며 해당 좌표값들을 더하면 되겠네. --> 그니깐 이걸 어떻게 하냐고? 어렵네
        // 이미 지나간 곳 좌표를 저장하는 배열을 만들고 확인하며 지나가거나, 확인하였으면 무시한다.
        // 확인할 때는 확인 배열을 만들어 체킹하고 체킹이 성공적으로 마치면 지나간 곳 좌표를 저장하는 배열에 넘겨준다.
        // 처음에 좌표 1개가 잡혔을 때 임시배열에 저장하고 상하좌우에 숫자가 있는지 체크, 있는 것들의 좌표를 임시 배열에 저장.
        // 임시배열을 계속확인하면서 임시배열의 끝값까지 도달하였다면( 끝값을 넘겨야함. ) 임시 배열에 있는 좌표들의 값들을 더하여 저장.
        // 그 배열들을 이미 확인한 좌표들이므로 확인한 좌표들 배열에 추가. ( 처음으로 돌아가 좌표에서 해당 배열 좌표가 있다면 continue )
        //[X, 5, 9, 1, X]       (0,1), (0,2), (0,3)
        //[X, 1, X, 5, X]       (1,1),        (1,3)
        //[X, 2, 3, 1, X]       (2,1), (2,2), (2,3)
        //[1, X, X, X, 1]  (3,0),                  (3,4)

        String[][] allMaps = new String[maps.length][maps[0].length()];
        for(int i = 0; i < maps.length; i++){
            String[] values = maps[i].split("");
            for(int j = 0; j < values.length; j++){
                allMaps[i][j] = values[j];
            }
        }

        // 맵 생성 된 거 확인
        for(int i = 0; i < allMaps.length; i++){
            System.out.println(Arrays.toString(allMaps[i]));
        }

        // 해당 맵을 기준으로 로직 수행.
        List<Integer[]> checkMaps= new ArrayList<>(); // 체크 리스트 ( 확인한 좌표 저장 )

        // 전체 사이즈보다 배열의 크기가 커지면 탈출
        int mapSize = maps.length * maps[0].length();

        int curPosX = 0;
        int curPosY = 0;
        for(int i = 0; i < allMaps.length; i++){
            for(int j = 0; j < allMaps[i].length; j++){

            }
        }

        // 좌표 0,0부터 시작
        int row = 0;
        int col = 0;
        while(checkMaps.size() > mapSize){
            // 바다 일 경우 바로 체크 하고 다음번으로
            if( allMaps[col][row].equals("X")){
                checkMaps.add( new Integer[]{col, row} ); // 바다 좌표 체크 리스트에 저장 후 진행
                row++;
                if(row == allMaps[0].length){
                    row = 0;
                    col++;
                }
            }else{

            }
        }





        return answer;
    }
}