# 🐾 hi-look

<img width="300" alt="메인로고" src="https://github.com/jjaei/hi-look/assets/120344687/378f8e6d-8ec4-494e-b449-64e7a9b60aeb">

**[개인 프로젝트]** 유기동물 조회 및 입양 후기 커뮤니티 <br>
- 개발기간 : 2023. 05. 27 ~ 2023. 06. 25
- [프로젝트 시연 연상](https://youtu.be/3NLBhApIbWE)
- [블로그](https://jaeiva.tistory.com/180)
- 사용기술(수정중)
  - FE : HTML, CSS, Javascript, Thymeleaf
  - BE : JAVA17, SpringBoot, mariaDB 

<br>

## ☘️ 프로젝트 소개 
코로나로 인해 팬데믹 퍼피라는 신조어가 생겨날만큼 반려동물 입양의 수는 현저히 늘어났다는 기사를 보았다. 하지만 그만큼 유기동물의 수도 6배 이상 증가했다고 한다. 하이룩은 그런 유기동물들을 찾기 위해 유기동물을 편하게 조회할 수 있도록 만든 웹사이트이며, 전국에 있는 유기동물 보호소를 조회할 수 있다. 하이룩의 목표는 유기동물들에게 두 번째 기회를 제공하고, 원활한 입양을 돕기 위해 만든 유기동물 조회 웹 사이트이다.

<br>

## ☘️ 요구사항 정의서
### 🍀 메인 페이지
  - 로그인 상태에 따른 환영 문구 표시
  - 로그인을 한 경우, 로그아웃 위쪽에 아이디와 함께 환영하는 문구를 출력한다. (ex: 환영합니다! test님!)
  - 로그인을 하지 않은 경우 아무 메시지도 출력되지 않는다.

<br>

### 🍀 회원 가입 페이지

<img width="300" alt="회원가입" src="https://github.com/jjaei/hi-look/assets/120344687/bee6ff65-2c77-4d91-9b66-6858b3032c87">

  - 유효성 검사
    - 현재는 이름과 이메일에만 세부적인 유효성 검사 존재한다.(나머지 필드에 대한 유효성 검사는 추후 수정 반영 예정)
    - 이름은 2~15글자 이내여야 하며, 한글로 입력되어야 회원가입이 가능하다.
    - 이메일 형식에 맞지 않으면 회원가입 불가능하다. (@와 .는 필수로 들어가야 한다.)
  - 아이디 중복 확인
    - 사용자가 아이디를 입력 할 때마다 keyup 이벤트를 이용해 중복 아이디를 검사한다.
    - 중복된 아이디를 입력할 경우 가입할 수 없다는 메시지를 표시한다.
    - 중복되지 않은 아이디를 입력할 경우 사용 가능한 아이디라는 메시지를 표시한다.
  - 회원가입 완료
    - 회원가입을 완료한 후 회원가입이 성공적으로 이루어졌다는 알림창을 표시한다.
    - 로그인이 되지 않은 상태로 메인 페이지로 이동시킨다.

<br>

### 🍀 로그인 페이지

<img width="300" alt="로그인" src="https://github.com/jjaei/hi-look/assets/120344687/4fb59bcb-fe9a-4171-bf18-3045ba110834">

- 로그인을 하지 않은 경우 이용가능한 페이지
  - 메인 페이지
  - 회원가입 페이지
  - 로그인 페이지
  - 유기동물 조회 게시판
  - 보호소 조회 게시판
- 로그인 유효성
  - 아이디 또는 비밀번호가 일치하지 않을 경우 일치하지 않는다는 메시지가 출력된다.
- 로그인 완료
  - 로그인이 완료된 사용자에 한해 메인페이지로 이동한다.

<br>

### 🍀 유기동물 조회 페이지

![유기동물조회](https://github.com/jjaei/hi-look/assets/120344687/603ce7ed-7405-46dc-b69d-7a19d02eee22)

![유기동물 상세조회](https://github.com/jjaei/hi-look/assets/120344687/4b3ae60e-0d9c-41f0-9577-9e0ea1ec3003)


- 현재 페이지에 상태
  - 상태가 '보호중'인 동물의 수를 표시한다.
  - 로그인을 했을 경우 아이디와 함께 출력된다.
  - 로그인하지 않았을 경우 '당신'이라는 문구와 함께 출력된다.
  - 시도별, 품종별, 전체 조회 기능
  - 시도만 선택했을 경우 해당 시도에 맞는 유기동물이 조회된다.
  - 품종만 선택했을 경우 해당 품종에 맞는 유기동물이 조회된다.
  - 시도와 품종을 둘 다 선택했을 경우 해당 시도와 품종에 맞는 유기동물이 조회된다.
  - 아무것도 선택하지 않았을 경우 전체 유기동물이 조회된다.
  - 전체 페이지의 유기동물은 현재 상태, 성별, 공고 기간, 품종, 발견 장소의 정보만 제공된다.
- 상세 페이지 이동
  - 사용자는 카드 게시글을 클릭하면 해당 동물의 상세 페이지로 이동한다.
  - 상세 페이지에서는 유기동물의 상세 정보를 볼 수 있다.
  - 돌아가기 버튼을 누르면 다시 전체 페이지로 이동한다.

<br>

### 🍀 보호소 조회

![보호소조회](https://github.com/jjaei/hi-look/assets/120344687/85db88af-9d24-49c1-b1e3-eae03246bf40)

- 전국 보호소 정보 확인 가능
  - 사용자는 전국의 보호소를 확인할 수 있다.
- 보호소에 대한 다음 정보를 표시
  - 동물보호센터 이름
  - 보호센터 주소
  - 보호센터 전화번호
  - 구조 대상 동물에 대한 정보

<br>

### 🍀 커뮤니티 게시판

![게시판](https://github.com/jjaei/hi-look/assets/120344687/ef943184-4e51-454a-922c-bea0e0622ca4)
<img width="400" alt="게시글1" src="https://github.com/jjaei/hi-look/assets/120344687/178fd427-aec2-4e96-8b83-fba269d026f2">
<img width="400" alt="게시글2" src="https://github.com/jjaei/hi-look/assets/120344687/5847b7d8-6199-4eeb-90a1-2550ea701d0b">

- 게시판 목록
  - 게시판은 로그인 하지 않아도 입장이 가능하다.
  - 게시글은 최신 순으로 글 번호를 기준으로 내림차순 되어 있다.
  - 각 게시글은 게시글 번호, 글 제목, 작성자, 글 작성 시간, 글 수정 시간, 조회수 등의 정보가 표시된다.
  - 사용자는 페이징 기능을 통해 각 페이지마다 10개의 글을 확인할 수 있다.
  - 게시글 제목을 클릭하면 해당 게시글의 상세 페이지로 이동한다.
  - 로그인 하지 않은 사용자는 게시글 제목을 클릭했을 경우 로그인 요청 알림창이 표시된다.
- 게시글 작성
  - 로그인 한 사용자만 게시글을 작성할 수 있다.
  - 로그인하지 않은 사용자는 게시글 작성 버튼이 보이지 않는다.
  - 작성자 아이디는 로그인 한 유저의 아이디가 자동으로 입력되어 있다. (수정 불가능)
  - 게시글 작성 시 제목과 내용을 필수로 입력해야 한다.
  - 사용자는 첨부파일을 추가할 수 있다.
- 게시글 조회
  - 사용자는 게시판 목록에서 게시글 제목을 클릭하면 해당 게시글의 상세 페이지로 이동한다.
  - 로그인 하지 않은 사용자는 게시글 조회를 할 수 없다.
  - 조회 페이지에서는 글 번호, 작성자, 글 등록일자(글 수정일 기준), 글 제목, 첨부파일(이미지),  글 내용, 댓글 정보가 표시된다.
- 댓글 작성 및 댓글 조회
  - 해당 게시글에 작성된 댓글 내용이 조회된다.
  - 로그인 한 사용자가 댓글을 작성했을 경우에만 수정/삭제 버튼이 생성된다.
  - 댓글 작성자의 아이디와 작성일자, 댓글 내용이 표시된다.
- 게시글 수정 및 삭제
  - 게시글 작성자가 아니면 게시글 수정 및 삭제를 할 수 없다.
  - 게시글 수정 시 바뀌지 않은 내용은 이전 내용이 유지되며 변경된 내용은 수정된 정보로 업데이트 된다.
  - 삭제된 게시글은 목록에서 사라지고 상세페이지에 접근할 수 없다.

<br>

## ☘️ ERD 설계

<img width="400" alt="ERD" src="https://github.com/jjaei/hi-look/assets/120344687/29cd3ebd-0d44-4119-8952-1882c3bc679c">

- draw.io에서 작성
  - **user**  사용자 정보
  - **animal**  유기동물 정보(open API)
  - **shelter**  보호소 정보(open API)
  - **board**  게시판 정보
  - **reply**  댓글 정보

<br>

## ☘️ API 명세서 작성
<img width="400" alt="API1" src="https://github.com/jjaei/hi-look/assets/120344687/75086dd1-15f0-44e8-b571-c74ff7b251c5">
<img width="400" alt="API2" src="https://github.com/jjaei/hi-look/assets/120344687/71ed6247-9ab0-4263-9f9d-13a1f00806d3">

<br><br>

## ☘️ 6월 27일 추가
보호소 부분이 너무 휑한 거 같아서 검색창을 구현했다. 사실 2글자만 입력되어도 해당하는 보호소를 전부 출력하고 싶은데 api를 사용하다보니 해당 기능 구현이 어려웠다. 그래서 우선 풀네임을 쳤을 경우 해당하는 보호소가 출력하게만 구현했다.

<img width="400" alt="보호소조회1" src="https://github.com/jjaei/hi-look/assets/120344687/d57fe63c-36d7-448e-bbf9-8027f043e105">
<img width="400" alt="보호소조회2" src="https://github.com/jjaei/hi-look/assets/120344687/fa61269b-c50a-4390-aaec-1a8b5e8ab309">

<br><br>
## 7월 29일 추가
보호소 부분에 페이지 버튼을 추가했다. 기존에는 한 페이지에 모든 보호소가 보였다면 지금은 한 페이지에 10개의 보호소가 보이게 된다.
<img width="147" alt="스크린샷 2023-07-29 오후 11 54 21" src="https://github.com/jjaei/project_hi-look/assets/120344687/5948e39a-051c-4f37-b507-fe85c932bf3a">
