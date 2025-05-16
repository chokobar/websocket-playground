🔌 Spring Boot WebSocket 프로젝트
📖 개요
Spring Boot 기반으로 개발한 WebSocket 클라이언트 연습용 프로젝트입니다.
방 코드를 서버에 등록 요청하고, 응답받은 방 코드를 통해 WebSocket 서버에 자동 연결하여 실시간 메시지를 주고받을 수 있습니다.
로컬에서 서버와 클라이언트를 동시에 실행하여 WebSocket 통신 흐름을 확인할 수 있도록 구성하였습니다.

🛠 기술 스택
Java 17

Spring Boot 3.4

Spring WebSocket

Spring Web (RestTemplate)

Lombok

Gradle

🔍 주요 기능
방 생성 요청

병원 코드(hospitalCd) 응답 수신

응답받은 코드로 WebSocket 자동 연결

실시간 메시지 송수신

🧪 학습 포인트
Spring WebSocket 클라이언트 구성 방법

WebSocket 연결 및 메시지 송수신 로직 구현

JSON 직렬화 및 역직렬화 처리

로컬 테스트를 위한 환경 구성 및 흐름 테스트
