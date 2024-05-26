history 
- v1 : coupon-service
- v2 : coupon-service-v2

<br/>

v2 에서 추가할 내용들
- lua script 기반 메시지 대기열 구성
- 유일키 발급 컴포넌트 개발 및 적용
- jib 빌드 추가 (완료)
- docker-compose 기반 전환
- 테이블 정규화 + 내부 로직 전환
- Caffeine 대신 EVCache 도입 검토 
  - Redis 를 EVCache 로 도입할까 고민은 해본 결과 Redis 는 앞단의 트래픽 제어에 사용하고 EVCahce 는 로컬 캐시 계층으로 두어서 캐싱에 중점을 두기로 결정




참고

- [Project Overview/Redis 대기열 구조(opens in a new tab)](https://chagchagchag.github.io/docs-coupon-service/project-overview/redis-queue-format/)
- [개선이 필요한 점들](https://chagchagchag.github.io/docs-coupon-service/things-to-be-fixed/)
