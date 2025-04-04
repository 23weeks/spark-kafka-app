# Kafka-Spark Project

실시간 메시지 처리 시스템 구축 프로젝트입니다. Kafka를 통해 메시지를 수신하고, Apache Spark를 이용하여 데이터를 처리합니다.

---

## 📦 기술 스택

- Java 11
- Spring Boot 2.7.18
- Apache Kafka 2.8.11
- Apache Spark 3.2.4
- Maven
- EC2 (AWS)
- GitHub Actions (CI/CD)

## 🚀 실행 방법

1. Kafka 및 Zookeeper 실행
2. Spring Boot Application 실행
3. REST API를 통해 메시지 전송
4. Spark가 메시지를 처리 후 결과 로그 출력

## 📬 API 예시

http
POST /api/v1/messages
Content-Type: application/json

{
  "type": "LOG",
  "message": "this is a test log message"
}

🔄 CI/CD (예정)
GitHub Actions를 통한 빌드 & 테스트 자동화

EC2 배포 자동화 스크립트

로그 및 에러 모니터링 도구 연동 (추후)
