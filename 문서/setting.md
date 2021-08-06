#프로젝트 생성
스프링 부트 스타터(https://start.spring.io/)
사용 기능: web, thymeleaf, jpa, h2, lombok, validation
groupId: jpabook artifactId: jpashop

#롬복 적용
1. Prefrences plugin lombok 검색 실행 (재시작)
2. Prefrences Annotation Processors 검색 Enable annotation processing 체크 (재시작) 3. 임의의 테스트 클래스를 만들고 @Getter, @Setter 확인


#IntelliJ Gradle 대신에 자바 직접 실행

#H2 데이터베이스 설치
개발이나 테스트 용도로 가볍고 편리한 DB, 웹 화면 제공
윈도우 설치 버전: https://h2database.com/h2-setup-2019-10-14.exe 윈도우, 맥, 리눅스 실행 버전: https://h2database.com/h2-2019-10-14.zip
https://www.h2database.com
다운로드 및 설치 데이터베이스 파일 생성 방법
1. ./h2.sh 실행
2.jdbc:h2:~/jpashop (최소 한번)
3. ~/jpashop.mv.db 파일 생성 확인
이후 부터는 jdbc:h2:tlscp://localhost/~/jpashop 이렇게 접속

-메모리 db 접속 : http://localhost:8090/h2-console
 
   
04.26
css 적용안될때는 폴더에서 컴파일해보면 됨

# 빌드 및 실행
 - 실행시 테스트 모듈이 모두 정상이어야함
 - gradle build -x test  // 테스트 모듈 제외 
콘솔로 이동
1. ./gradlew build
2. cd build/libs
3. java -jar hello-spring-0.0.1-SNAPSHOT.jar
4. 실행확인

