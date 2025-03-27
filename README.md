# 📩 Webhook 

## 📌 프로젝트 개요
+ 메일이 수신시 **자동으로 읽음 처리**하는 기능을 제공
+ **H2 데이터베이스**를 사용
+ 로컬 환경시, 외부에서 접근할 수 있도록 **SSL URL로 포워딩 필요** 본 프로젝트에선 ngrok을 활용하였음 (https://ngrok.com/docs/getting-started/)
---  
1. 환경 변수 설정 - application.properties에서 관리
2. 서버 실행 후 URL 접속
http://localhost:8080/swagger-ui/index.html

<img width="1215" alt="image" src="https://github.com/user-attachments/assets/f9805992-c91f-4078-8b58-dd8754fab519" />

- send api 호출하여 body에 발송할 이메일 주소 작성

<img width="908" alt="image" src="https://github.com/user-attachments/assets/69db8031-8272-400d-9322-b5a41dc6bdf4" />

- 메일을 클릭하면 img 태그가 callback url인 read api를 호출하여 읽음 처리

###### 삽질
###### 메일 발송 html 파일에서 javascript로 POST 호출할려고 했으나, 역시나 보안 이슈 때문에 코드 실행이 안되었다.<br>img 태그로 시도해보니 http 이슈가 있어서 ngrok 이라는 서비스로 localhost 주소를 ssl 주소로 포트포워딩할 수 있었다.<br>보안 이슈 부분은 처리했지만 그럼에도 불구하고 read api call을 안했는데 img 태그를 display:none 처리하면 아예 주소 호출이 되지 않았다..<br>해당 style을 빼니 잘 동작한다.

