# 알고리즘 자주 나오는 내용 정리

코딩 테스트 문제를 풀 때 자주 만나는 패턴과 Java에서 바로 떠올리면 좋은 구현 포인트를 정리한 문서입니다.

## 문제 풀이 기본 순서

1. 입력 크기를 보고 시간복잡도 한계를 먼저 잡는다.
2. 완전탐색이 가능한지 확인한다.
3. 정렬, 해시, 투 포인터, BFS/DFS, DP 같은 대표 패턴으로 바꿀 수 있는지 생각한다.
4. 예외 케이스를 먼저 적어본다.
5. 작은 예시로 손으로 한 번 실행해본다.

## 시간복잡도 감각

| 입력 크기 | 자주 가능한 복잡도 |
| --- | --- |
| `N <= 10` | 순열, 조합, 백트래킹 |
| `N <= 20` | 비트마스킹, 완전탐색 일부 |
| `N <= 1,000` | `O(N^2)` |
| `N <= 100,000` | `O(N log N)`, `O(N)` |
| `N <= 1,000,000` | `O(N)` |

## 배열과 문자열

- 인덱스 범위 확인이 가장 중요하다.
- 문자열을 자주 수정해야 하면 `StringBuilder`를 사용한다.
- 문자 개수 세기는 알파벳이면 `int[26]`, 일반 값이면 `Map`을 고려한다.

```java
int[] count = new int[26];
for (char c : s.toCharArray()) {
    count[c - 'a']++;
}
```

## 해시

값의 존재 여부, 등장 횟수, 빠른 검색이 필요하면 우선 후보입니다.

- `HashSet`: 중복 제거, 존재 여부 확인
- `HashMap`: 개수 세기, 값 매핑

```java
Map<String, Integer> map = new HashMap<>();
map.put(name, map.getOrDefault(name, 0) + 1);
```

## 정렬

정렬 후에는 문제 구조가 단순해지는 경우가 많습니다.

- 최솟값/최댓값 조합
- 순서대로 처리
- 같은 값끼리 묶기
- 투 포인터와 함께 사용

```java
Arrays.sort(arr);

Arrays.sort(intervals, (a, b) -> {
    if (a[0] == b[0]) return a[1] - b[1];
    return a[0] - b[0];
});
```

## 스택과 큐

### 스택

최근에 들어온 값을 먼저 확인해야 할 때 사용합니다.

- 괄호 문제
- 뒤로가기
- 이전 값과 비교
- 탑, 주식 가격 같은 단조 스택

```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(value);
int top = stack.pop();
```

### 큐

먼저 들어온 값을 먼저 처리해야 할 때 사용합니다.

- BFS
- 작업 대기열
- 시뮬레이션

```java
Queue<int[]> queue = new ArrayDeque<>();
queue.offer(new int[] {0, 0});
int[] current = queue.poll();
```

## 투 포인터

정렬된 배열이나 연속 구간에서 두 개의 인덱스를 움직이며 답을 찾습니다.

- 두 수의 합
- 연속 부분합
- 구간 길이 최소/최대

```java
int left = 0;
int sum = 0;

for (int right = 0; right < arr.length; right++) {
    sum += arr[right];

    while (sum > target) {
        sum -= arr[left++];
    }
}
```

## 슬라이딩 윈도우

고정 길이 또는 조건을 만족하는 연속 구간을 빠르게 갱신할 때 사용합니다.

```java
int sum = 0;
for (int i = 0; i < k; i++) {
    sum += arr[i];
}

int answer = sum;
for (int i = k; i < arr.length; i++) {
    sum += arr[i] - arr[i - k];
    answer = Math.max(answer, sum);
}
```

## 누적합

구간 합을 여러 번 구해야 하면 누적합을 사용합니다.

```java
int[] prefix = new int[n + 1];
for (int i = 0; i < n; i++) {
    prefix[i + 1] = prefix[i] + arr[i];
}

int rangeSum = prefix[right + 1] - prefix[left];
```

## 이분 탐색

정렬된 데이터에서 값을 찾거나, 가능한 답의 범위를 줄일 때 사용합니다.

- 특정 값 찾기
- 최소 가능한 값 찾기
- 최대 가능한 값 찾기
- `가능한가?`를 기준으로 답 탐색

```java
int left = 0;
int right = max;
int answer = max;

while (left <= right) {
    int mid = left + (right - left) / 2;

    if (can(mid)) {
        answer = mid;
        right = mid - 1;
    } else {
        left = mid + 1;
    }
}
```

## DFS와 BFS

그래프, 격자, 연결 요소, 최단 거리 문제에서 자주 사용합니다.

### DFS

- 끝까지 탐색
- 재귀 또는 스택
- 백트래킹과 잘 어울림

```java
static void dfs(int node) {
    visited[node] = true;

    for (int next : graph[node]) {
        if (!visited[next]) {
            dfs(next);
        }
    }
}
```

### BFS

- 가까운 곳부터 탐색
- 가중치가 없는 최단 거리
- 큐 사용

```java
int[] dx = {1, -1, 0, 0};
int[] dy = {0, 0, 1, -1};

Queue<int[]> queue = new ArrayDeque<>();
queue.offer(new int[] {0, 0});
visited[0][0] = true;

while (!queue.isEmpty()) {
    int[] cur = queue.poll();

    for (int d = 0; d < 4; d++) {
        int nx = cur[0] + dx[d];
        int ny = cur[1] + dy[d];

        if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
        if (visited[nx][ny]) continue;

        visited[nx][ny] = true;
        queue.offer(new int[] {nx, ny});
    }
}
```

## 백트래킹

모든 경우를 만들되, 불가능한 경우는 빠르게 버립니다.

- 순열
- 조합
- 부분집합
- 조건이 많은 완전탐색

```java
static void backtrack(int depth) {
    if (depth == targetDepth) {
        // answer update
        return;
    }

    for (int i = 0; i < n; i++) {
        if (visited[i]) continue;

        visited[i] = true;
        selected[depth] = i;
        backtrack(depth + 1);
        visited[i] = false;
    }
}
```

## 동적 계획법

큰 문제를 작은 문제의 답으로 표현할 수 있을 때 사용합니다.

생각 순서:

1. `dp[i]`가 무엇을 의미하는지 정한다.
2. 초기값을 정한다.
3. 점화식을 세운다.
4. 반복 순서를 정한다.

```java
int[] dp = new int[n + 1];
dp[1] = 1;

for (int i = 2; i <= n; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}
```

## 그리디

현재 가장 좋아 보이는 선택이 전체 최적해로 이어질 때 사용합니다.

- 정렬 기준이 중요하다.
- 왜 현재 선택이 최적인지 설명할 수 있어야 한다.
- 반례를 꼭 생각한다.

자주 나오는 예:

- 가장 빨리 끝나는 회의부터 선택
- 가장 작은 비용부터 선택
- 가장 큰 이득부터 선택

## 우선순위 큐

최솟값 또는 최댓값을 계속 꺼내야 할 때 사용합니다.

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```

객체 정렬:

```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
    if (a[1] == b[1]) return a[0] - b[0];
    return a[1] - b[1];
});
```

## 유니온 파인드

집합을 합치고, 같은 집합인지 빠르게 확인할 때 사용합니다.

- 연결 여부
- 사이클 판별
- 최소 신장 트리

```java
static int[] parent;

static int find(int x) {
    if (parent[x] == x) return x;
    return parent[x] = find(parent[x]);
}

static void union(int a, int b) {
    int rootA = find(a);
    int rootB = find(b);

    if (rootA != rootB) {
        parent[rootB] = rootA;
    }
}
```

## 비트마스킹

선택 여부를 비트로 표현합니다.

- 원소 수가 작을 때 부분집합 탐색
- 방문 상태 압축

```java
for (int mask = 0; mask < (1 << n); mask++) {
    for (int i = 0; i < n; i++) {
        if ((mask & (1 << i)) != 0) {
            // i selected
        }
    }
}
```

## 수학

### 최대공약수

```java
static int gcd(int a, int b) {
    while (b != 0) {
        int temp = a % b;
        a = b;
        b = temp;
    }
    return a;
}
```

### 최소공배수

```java
static int lcm(int a, int b) {
    return a / gcd(a, b) * b;
}
```

### 소수 판별

```java
static boolean isPrime(int n) {
    if (n < 2) return false;

    for (int i = 2; i * i <= n; i++) {
        if (n % i == 0) return false;
    }
    return true;
}
```

## Java 구현 팁

- 값 범위가 크면 `int` 대신 `long`을 고려한다.
- `ArrayList`는 조회가 빠르고, 중간 삭제가 많으면 불리하다.
- `LinkedList`보다 대부분 `ArrayDeque`가 큐/스택 용도로 좋다.
- 문자열 누적은 `StringBuilder`를 사용한다.
- 정렬 비교식에서 오버플로우가 걱정되면 `Integer.compare(a, b)`를 사용한다.

```java
Arrays.sort(arr, (a, b) -> Integer.compare(a.value, b.value));
```

## 자주 놓치는 예외 케이스

- 빈 배열 또는 길이 1
- 중복 값
- 모두 같은 값
- 음수 포함
- 최솟값과 최댓값
- 답이 없는 경우
- 시작점과 도착점이 같은 경우
- 인덱스가 0부터인지 1부터인지
- 정렬 후 원래 인덱스가 필요한 경우

## 패턴 선택 빠른 기준

| 문제 신호 | 떠올릴 패턴 |
| --- | --- |
| 존재 여부, 개수 세기 | 해시 |
| 연속 구간 | 투 포인터, 슬라이딩 윈도우, 누적합 |
| 최단 거리 | BFS |
| 모든 경우 탐색 | DFS, 백트래킹 |
| 최솟값/최댓값 계속 갱신 | 우선순위 큐 |
| 정렬 후 조건이 단순해짐 | 정렬, 그리디 |
| 큰 문제를 작은 문제로 나눔 | DP |
| 연결 여부 확인 | 유니온 파인드 |
| 답의 범위가 있고 가능 여부 판단 가능 | 이분 탐색 |

