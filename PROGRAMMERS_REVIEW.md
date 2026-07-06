# Programmers 풀이 검토 기록

검토일: 2026-07-06

## 검토 기준

- 프로그래머스 공식 문제 설명, 제한사항, 입출력 예와 비교했다.
- 공식 제한사항에서 음수 입력이 주어지지 않는 문제는 음수 미고려를 별도 문제로 보지 않았다.
- "정답은 맞을 수 있음"과 "제출용 코드로 깔끔함"을 분리해서 봤다.
- 현재 저장소 전체 Java 소스는 `javac -encoding UTF-8 -d out ...` 기준으로 컴파일 가능함을 확인했다.

## 수정이 필요한 풀이

### 1. 무인도 여행

- 파일: `src/programmers/level2/s_1/desertIsland/Deserted_Island.java`
- 공식 문제: https://school.programmers.co.kr/learn/courses/30/lessons/154540
- 공식 핵심 조건:
  - `maps`는 3 이상 100 이하 크기의 직사각형 문자열 배열이다.
  - `X`는 바다, `1`~`9`는 땅과 식량이다.
  - 상하좌우로 연결된 땅을 하나의 섬으로 보고 식량 합을 구한다.
  - 섬별 합을 오름차순으로 반환하고, 섬이 없으면 `[-1]`을 반환한다.
- 현재 상태:
  - 공식 예제 `["X591X","X1X5X","X231X","1XXX1"]`의 기대값은 `[1, 1, 27]`이다.
  - 현재 실행 결과는 `[]`이다.
  - `while(checkMaps.size() > mapSize)` 조건이 처음부터 false라 탐색이 실행되지 않는다.
  - BFS/DFS로 연결된 섬을 찾는 핵심 로직이 아직 구현되지 않았다.
- 수정 방향:
  - `visited` 배열을 만들고 모든 좌표를 순회한다.
  - 방문하지 않은 숫자 칸에서 BFS 또는 DFS를 시작한다.
  - 연결된 숫자 칸의 합을 리스트에 넣는다.
  - 리스트가 비어 있으면 `new int[]{-1}`, 아니면 정렬 후 배열로 반환한다.

### 2. 개인정보 수집 유효기간

- 파일: `src/programmers/level1/s_5/edopi/EDOPI.java`
- 공식 문제: https://school.programmers.co.kr/learn/courses/30/lessons/150370
- 공식 핵심 조건:
  - 모든 달은 28일까지 있다고 가정한다.
  - 유효기간 마지막 날까지는 보관 가능하다.
  - 유효기간이 지난 개인정보 번호만 반환한다.
- 현재 문제:
  - `if(todays >= ValidDate[i])`로 비교하고 있다.
  - 공식 설명상 오늘이 보관 가능 마지막 날과 같으면 아직 파기 대상이 아니다.
  - 따라서 `today > validDate`일 때만 파기해야 한다.
- 확인한 예:
  - `2022.02.20 C`, 약관 `C 3`, 오늘 `2022.05.19`는 `2022.05.19`까지 보관 가능하므로 파기 대상이 아니다.
  - 현재 코드는 이를 파기 대상으로 포함한다.
- 수정 방향:
  - 최소 수정은 `todays >= ValidDate[i]`를 `todays > ValidDate[i]`로 변경하는 것이다.
  - 더 안정적인 방식은 날짜를 `year * 12 * 28 + month * 28 + day` 형태의 총 일수로 변환해서 비교하는 것이다.

### 3. NumberBaseball

- 파일: `src/programmers/level3/NumberBaseball.java`
- 현재 상태:
  - 현재 활성화된 `Solution`은 1000부터 9999까지 순회하며 `submit`을 호출한다.
  - 0이 포함된 숫자, 중복 숫자도 후보로 제출한다.
  - 피드백을 활용해 후보를 줄이는 로직이 없다.
  - 아래쪽 주석 처리된 코드의 0/중복 필터링 방향이 더 적절하다.
- 주의:
  - 이 문제는 현재 코드의 `Function<Integer, String> submit` 시그니처와 정확히 일치하는 공식 프로그래머스 문제 페이지를 특정하지 못했다.
  - 다만 코드 주석의 조건이 "1~9 사이 서로 다른 숫자 4개"라면, 현재 활성 코드에는 조건 누락이 있다.
- 수정 방향:
  - 0 포함 후보와 중복 숫자 후보는 `submit` 호출 전에 제외한다.
  - 이전 질의 결과를 저장하고, 새 후보가 모든 이전 결과와 모순 없는지 검사한 뒤 제출한다.

## 정답 가능성은 높지만 정리하면 좋은 풀이

### 가장 많이 받은 선물

- 파일: `src/programmers/level1/s_17/mostgift/Mostgift.java`
- 공식 문제: https://school.programmers.co.kr/learn/courses/30/lessons/258712
- 공식 조건:
  - 친구 수는 2 이상 50 이하이다.
  - 선물 기록은 1 이상 10,000 이하이다.
  - 선물 지수는 `준 선물 수 - 받은 선물 수`라서 음수가 될 수 있다.
- 검토 결과:
  - 현재 코드는 선물 지수를 `int`로 처리하므로 음수 지수도 문제 없다.
  - 친구 수가 최대 50이라 `O(n^2)` 쌍 비교는 충분하다.
  - 다만 `solution` 내부의 디버그 출력은 제출 전 제거하는 편이 좋다.

### 유연근무제

- 파일: `src/programmers/level1/s_7/flexiblework/FlexibleWork.java`
- 공식 문제: https://school.programmers.co.kr/learn/courses/30/lessons/388351
- 공식 조건:
  - `schedules[i]`는 700 이상 1100 이하이다.
  - 실제 출근 시각은 600 이상 2359 이하이다.
  - 시각의 분 부분은 59 이하이다.
  - 토요일, 일요일은 이벤트 판정에서 제외한다.
- 검토 결과:
  - `희망 시각 + 10분` 계산에서 분이 50 이상이면 `+50`, 아니면 `+10` 하는 방식은 공식 조건 안에서 맞다.
  - 요일 계산도 `startday` 기준으로 토/일을 건너뛰고 있어 방향이 맞다.
  - `solution` 내부 출력은 제거하는 편이 좋다.

### 동영상 재생기

- 파일: `src/programmers/level1/videoplayer/VideoPlayer.java`
- 공식 문제: https://school.programmers.co.kr/learn/courses/30/lessons/340213
- 공식 조건:
  - 모든 시간은 `mm:ss` 형식이고 `0 <= ss <= 59`이다.
  - `prev`, `next`는 10초 이동이다.
  - 이동 전후 현재 위치가 오프닝 구간이면 `op_end`로 이동한다.
- 검토 결과:
  - 공식 예제 기준 동작 방향은 맞다.
  - 다만 `mmss` 정수로 시간을 계산하면 사람이 읽기 어렵고 실수 가능성이 있다.
  - 초 단위 정수로 변환해서 계산한 뒤 다시 `mm:ss`로 바꾸는 방식이 더 안전하다.
  - `solution` 내부 출력은 제거하는 편이 좋다.

### 공원

- 파일: `src/programmers/level1/s_22/park/Park.java`
- 공식 문제: https://school.programmers.co.kr/learn/courses/30/lessons/340198
- 공식 조건:
  - `mats` 길이는 최대 10, 각 원소는 1 이상 20 이하이다.
  - `park`는 최대 50 x 50이다.
  - 빈 자리는 `"-1"`, 사람이 있으면 알파벳 한 글자이다.
- 검토 결과:
  - 현재 방식은 가능한 정사각형 크기를 큰 값부터 검사하고, 보유한 돗자리 중 가능한 최대 크기를 고른다.
  - 공식 제한 범위에서는 완전탐색으로도 충분하다.
  - 다만 `mats`에 없는 크기까지 먼저 찾고 다시 맞추는 구조라, `mats`를 내림차순 정렬한 뒤 각 돗자리를 바로 검사하면 더 단순하다.
  - `solution` 내부 출력은 제거하는 편이 좋다.

### 붕대 감기

- 파일: `src/programmers/level1/bandagewarapping/BandWarp.java`
- 공식 문제: https://school.programmers.co.kr/learn/courses/30/lessons/250137
- 공식 조건:
  - 공격 시간은 오름차순이며 모두 다르다.
  - 공격 시간은 1 이상 1000 이하이다.
  - 공격당한 순간에는 회복하지 않는다.
- 검토 결과:
  - 마지막 공격 시간까지 1초씩 시뮬레이션하는 방식은 공식 제한에서 충분하다.
  - 공격 시 연속 성공 시간을 초기화하고, 사망 시 `-1`을 반환하는 흐름도 맞다.

### 달리기 경주

- 파일: `src/programmers/level1/s_24/race/Race.java`
- 공식 문제: https://school.programmers.co.kr/learn/courses/30/lessons/178871
- 공식 조건:
  - 선수 수는 최대 50,000명이다.
  - 호출 수는 최대 1,000,000번이다.
  - 경기 중 1등인 선수는 불리지 않는다.
- 검토 결과:
  - 이름에서 현재 인덱스를 찾는 `HashMap`을 사용해 호출마다 바로 앞 선수와 교환한다.
  - 공식 제한에서 필요한 `O(callings)` 방식이라 적절하다.
  - `calls` 배열은 만들어 놓고 사용하지 않으므로 제거해도 된다.

### 대충 만든 자판

- 파일: `src/programmers/level1/s_11/keymap/KeyMap.java`
- 공식 문제: https://school.programmers.co.kr/learn/courses/30/lessons/160586
- 공식 조건:
  - 키맵과 타깃은 알파벳 대문자로만 구성된다.
  - 만들 수 없는 타깃은 `-1`을 반환한다.
- 검토 결과:
  - 각 문자별 최소 입력 횟수를 `Map<Character, Integer>`에 저장하는 방향이 맞다.
  - 공식 제한에서 충분히 빠르다.
  - `solution` 내부 출력은 제거하는 편이 좋다.

## 공통 개선점

- `solution` 내부의 `System.out.println`, `System.out.printf`는 제출 전 제거한다.
- 프로그래머스 제출용 클래스에는 보통 `class Solution`만 남기고 `main` 테스트 코드는 분리한다.
- 시간 문제는 가능하면 `HHMM` 또는 `MMSS` 정수 대신 총 분/총 초로 바꿔 계산한다.
- 날짜 문제는 `YYYYMMDD` 정수 비교보다 "모든 달 28일" 같은 문제 전용 규칙에 맞춘 총 일수 변환이 안정적이다.
- 공식 제한사항에 없는 입력까지 억지로 방어할 필요는 없지만, 공식 제한 안의 경계값은 꼭 직접 테스트한다.

## 우선순위

1. `Deserted_Island.java`의 BFS/DFS 구현을 완료한다.
2. `EDOPI.java`의 파기일 비교 조건을 수정한다.
3. `NumberBaseball.java`의 후보 필터링과 피드백 활용 로직을 정리한다.
4. 제출용 코드에서 디버그 출력을 제거한다.
5. 각 문제별 공식 예제를 `main` 또는 별도 테스트로 남겨 회귀 확인이 가능하게 한다.
