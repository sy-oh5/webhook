# 📩 Webhook 

## 📌 프로젝트 개요
해당 프로젝트는 메일이 수신되었을 때 **자동으로 읽음 처리**하는 기능을 제공하는 프로젝트입니다.  
이 프로젝트는 **H2 데이터베이스**를 사용하며, 외부에서 접근할 수 있도록 **ngrok을 활용하여 SSL URL로 포워딩**합니다.
---

## 🔥 기능 설명
- **메일 발송 및 저장**: 사용자가 특정 이메일로 메일을 전송하면, 해당 정보가 DB에 저장됩니다.
- **메일 읽음 처리**: 이메일을 열었을 때, 자동으로 서버에 요청이 보내져 **읽음 처리**됩니다.
- **ngrok을 통한 SSL URL 제공**: localhost에서 실행되는 서버를 외부에서 접근할 수 있도록 HTTPS 포워딩합니다.

---

<img width="869" alt="image" src="https://github.com/user-attachments/assets/6eda5f08-3c6c-4dd6-9dc8-a670d036295c" />
- 시퀀스 다이어그램
  
<img width="1215" alt="image" src="https://github.com/user-attachments/assets/f9805992-c91f-4078-8b58-dd8754fab519" />

- send api 호출하여 body에 발송할 이메일 주소 작성

<img width="908" alt="image" src="https://github.com/user-attachments/assets/69db8031-8272-400d-9322-b5a41dc6bdf4" />

- 메일을 클릭하면 img 태그가 callback url인 read api를 호출하여 읽음 처리

###### 삽질
###### 메일 발송 html 파일에서 javascript로 POST 호출할려고 했으나, 역시나 보안 이슈 때문에 코드 실행이 안되었다.<br>img 태그로 시도해보니 http 이슈가 있어서 ngrok 이라는 서비스로 localhost 주소를 ssl 주소로 포트포워딩할 수 있었다.<br>보안 이슈 부분은 처리했지만 그럼에도 불구하고 read api call을 안했는데 img 태그를 display:none 처리하면 아예 주소 호출이 되지 않았다..<br>해당 style을 빼니 잘 동작한다.

