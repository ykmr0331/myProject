#  FIFA Portal

<p align="center">
  <img src="https://github.com/ykmr0331/myProject/assets/117189519/7f62dc91-917d-4527-ab1d-74c8facb7bb4">
</p>


<br><br>
## 프로젝트 목적

- Spring Boot와 JPA를 활용하여 Itwill 교육과정에서 진행한 파이널 프로젝트의 전반적인 과정을 이해 및 구현하기 위한 목적


<br><br>
## 프로젝트 개요

  
- FIFA에 실제 소속된 선수 및 클럽 검색 및 생성 가능

- 관리자 로그인 시 회원 게시판 보기 및 삭제 가능

- 관리자 권한으로 선수, 클럽, 이적 생성 가능
  
- 메인 페이지에 동적으로 표시된 영상 실시간 재생 가능  


<br><br>
## 개발 기간

- **2023.12.10~2024.1.15**


<br><br>
## 기술

 
- ﻿Back-End : Java, Spring Boot, JPA
 
- ﻿Front-End : JavaScript, jQuery, Fetch API

- ﻿View Template : Thymeleaf , Bootstrap

- ﻿DB: Oracle SQL

- ﻿API: Kakao Login

<br><br>

# 상세보기


<details>
<summary>프로젝트 상세보기</summary>

## 1. 메인 페이지


![스크린샷 2024-01-30 232615](https://github.com/ykmr0331/myProject/assets/117189519/42c674df-9dac-4ef0-b1d0-3db96fe0ce6a)


﻿- FIFA Portal의 메인 화면

﻿- Fetch API를 사용하여 동적 비디오 콘텐츠를 비동기적으로 제공

﻿- Thymeleaf를 사용하여 공통구조를 가진 웹 페이지 구현
       
<br><br><br>


## 2. 로그인 페이지


![로그인 페이지 화면](https://github.com/ykmr0331/myProject/assets/117189519/0607f988-c0a2-4df1-a34c-8983c097a2ed)



﻿- FIFA Portal의 로그인 화면

﻿- 로그인 시 jQuery Ajax로 서버와 비동기 통신

﻿- Restful API를 기반으로 로그인 처리

﻿- Kakao API를 이용하여 통합 로그인 기능을 구현
       
<br><br><br>



## 3. 선수 목록 페이지


![로그인 페이지 화면](https://github.com/ykmr0331/myProject/assets/117189519/0607f988-c0a2-4df1-a34c-8983c097a2ed)



﻿- FIFA Portal의 선수 목록 페이지

﻿- Model과 Thymeleaf로 동적 HTML 생성

﻿- Spring Data JPA Query Method로 선수 검색 구현

﻿- Spring Web 인터페이스를 이용해 관리자 권한으로 선수 이미지 업로
       
<br><br><br>




## 4. 클럽 목록 페이지


![클럽 목록 페이지](https://github.com/ykmr0331/myProject/assets/117189519/ad557840-a302-4323-8e2a-a7972e47189c)


﻿- FIFA Portal의 클럽 목록 페이지

﻿- Model과 Thymeleaf로 동적 HTML 생성

﻿- Spring Data JPA Query Method로 선수 검색 구현

﻿- Spring Web 인터페이스를 이용해 관리자 권한으로 클럽 로고 이미지 업로드
       
<br><br><br>



## 5. 이적 목록 페이지


![이적시장 목록 페이지](https://github.com/ykmr0331/myProject/assets/117189519/2c6ed359-db41-4f53-9f94-52f73e0635c3)


﻿- FIFA Portal의 이적 목록 페이지

﻿- Model과 Thymeleaf로 동적 HTML 생성

﻿- Thymeleaf로 날짜 및 숫자를 사용자 친화적 형식으로 표시

       
<br><br><br>


## 6. 게시판 목록 페이지


![게시판 목록 페이지](https://github.com/ykmr0331/myProject/assets/117189519/8a7f798d-91cd-40a4-9635-5bcea0c63e2d)


﻿- FIFA Portal의 게시판  목록 페이지

﻿- Pageable 인터페이스로 게시판 목록 구현

﻿- Model과 Thymeleaf로 동적 HTML 생성

﻿- Spring Data JPA의 Query Method를 활용하여 게시글을 검색
       
<br><br><br>


## 7. 게시글 작성 페이지


![게시글 작성 페이지](https://github.com/ykmr0331/myProject/assets/117189519/1fdf2b42-b5a5-4364-9269-5d5bbd7cc7b0)


﻿- FIFA Portal의 게시글 작성 페이지

﻿- 로그인시 게시판 목록에서 접근 가능

﻿- Dto와 Session을 이용해 게시글의 제목과 내용을 서버로 가져옴

﻿- Annotation을 사용해 비밀글 여부의 기본값을 공개 지정
       
<br><br><br>


## 8. 관리자  페이지


![관리자 페이지](https://github.com/ykmr0331/myProject/assets/117189519/cdf4e0a9-f5a7-4aa4-b16a-552cd65a571e)

﻿- FIFA Portal의 관리자 메인 페이지
 
﻿- JPQL을 이용해 선수, 클럽, 이적시장 순위를 메인페이지에 구현

﻿- 왼쪽 사이드바에서 관리 메뉴로 이동 가능
       
<br><br><br>


## 9. 생성  페이지(관리자 권한)


![관리자 생성관련 페이지](https://github.com/ykmr0331/myProject/assets/117189519/475682b8-cb6c-439e-90de-10e1ca201c78)

﻿- 선수, 클럽, 이적 생성 페이지는 관리자 페이지로만 접근 가능(세션 활용)
 
﻿- Thymeleaf와 Ajax를 활용하여 Restful API로 정보를 전송후 생성 

﻿- MultipartHttpServletRequest를 이용하여 선수, 클럽 생성지 이미지 파일 첨부
       
<br><br><br>


</details>
















  


