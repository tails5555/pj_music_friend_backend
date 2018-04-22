# MUSIC FRIEND[음악 친구 Indie Project]
- 사용자와 음악의 관계를 그래프로 표현을 하여 시각화 된 관계를 제공을 하는 프로젝트입니다.
- 친구 Follow 기능과 음악 즐겨찾기 기능을 기반으로 계산을 하여 보기 좋게 정리를 해 줍니다.
- 현재 음악과 친구 관계에 대해서는 **1인칭 시점으로** 그래프로 표현하는 작업까지 완료하였습니다.
- 추후에 1주일 단위 별로 MUSIC FRIEND 관리자가 각 음악들에 대해서 평균과 표준 편차를 측정하고 난 뒤에 음악에 대한 데이터를 인기 순위로 효과적으로(예를 들어 변동 수치에 대해서 반영을 할 때) 정렬할 수 있도록 구현을 하겠습니다.

## 참여자 소개
- 201332001 강인성([tails5555](https://github.com/tails5555)) - Relational Database 구상 및 Mapping
- 201332028 장승훈([wkdtndgns](https://github.com/wkdtndgns)) - Front-End 담당 및 메뉴 Idea 구상
- 201332017 서종현([shouwn](https://github.com/shouwn)) - Relational Database에서 이용한 알고리즘 구상

## Relational Database
**데이터베이스는 향시에 변동이 될 수 있습니다.**

![music_friend_physical](/src/doc/music_friend_physical.png "music_friend_physical")

## 제작 기간
- JSP First Release : 2018-04-06 to 2018-04-17
- JSP Second Release : 정보처리기사 필기 끝나는 시점에서 추가할 예정.
- ReactJS Release : RabbitMQ 스터디 끝나는 시점에서 계속 할 예정.

## application.properties 설정
- src > main > resources > application.properties에 현존하는 설정을 아래와 같은 방식으로 작성해서 이용하시면 됩니다.

```
spring.mvc.view.prefix=[MVC에서 View 위치에 대한 설정]
spring.mvc.view.suffix=[MVC에서 View 확장자 설정]
spring.datasource.driver-class-name=[JDBC 이용 클래스 이름 입력]
spring.datasource.url=[JDBC와 연동하기 위한 URL]
spring.datasource.username=[DB 사용자 이름]
spring.datasource.password=[DB 사용자 비밀번호]
<--! Hibernate JPA를 적용하기 위한 설정 -->
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
<--! Hibernate JPA에 대해 SQL 결과를 인지하기 위한 문장. Debug를 통해 일일히 확인 시킨다. --> 
logging.level.org.hibernate.SQL=DEBUG
<--! Hibernate JPA에 대해서 SQL의 기본 문장에 대해 TRACE 단계를 통해 확인을 시켜준다. -->
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## Screen Shot
![music_friend_1st_release_screenshot_01](/src/doc/music_friend_1st_release_screenshot_01.png "music_friend_1st_release_screenshot_01")

- 사용자 별로 음악 목록을 관리할 수 있는 MY HEART 기능의 일부입니다.
- 여기서 하트를 다시 클릭하면 음악 목록을 제할 수 있습니다.

![music_friend_1st_release_screenshot_02](/src/doc/music_friend_1st_release_screenshot_02.png "music_friend_1st_release_screenshot_02")

![music_friend_1st_release_screenshot_03](/src/doc/music_friend_1st_release_screenshot_03.png "music_friend_1st_release_screenshot_03")

- **1인칭 사용자** 시점에서 그린 그래프입니다.
- 그래프의 진하기에 대해서는 자카드 유사도(Jaccard Similarity)를 이용해서 사용자 간의 음악 목록의 유사도에 대해서 측정을 하여 표기를 1부터 10까지 측정하여 계산을 하도록 만들었습니다.
- 그리고 본인과 타인의 음악 관계 뿐만 아니라 타인과 타인의 음악 관계에 대해서도 인식할 수 있도록 구상을 하였으며 이를 통해 현재 사용자가 들어 보지 못한 음악에 대해서 접근을 가까이 할 수 있도록 구성을 했습니다.

![music_friend_1st_release_screenshot_04](/src/doc/music_friend_1st_release_screenshot_04.png "music_friend_1st_release_screenshot_04")

- 그리고 친구 Follow 기능을 추가하여 실시간으로 친구와의 관계를 관리를 할 수 있도록 구성을 하였습니다.
- Follower는 현재 따르는 친구, Following은 본인이 따르고 있는 친구로 이해할 수 있습니다.
- 그리고 친구 추천에서는 현재 Follower들에 대해서는 파란 음영으로 구분을 하기 쉽게 표기를 하였으며, 정렬 기준은 현재 본인이 듣는 음악 목록과 유형이 비슷한 친구들로 정렬을 하였습니다.

![music_friend_1st_release_screenshot_05](/src/doc/music_friend_1st_release_screenshot_05.png "music_friend_1st_release_screenshot_05")

- Main Page에서는 실시간으로 올라오는 음악들에 대해 좋아요 버튼을 반영해서 저장을 할 수 있도록 간단한 조작감이 있는 UI로 구상을 하였습니다.
- 그리고 변동 수치에 대해서는 추후에 통계적인 개념을 기반으로 재측정하여 실시간 음악 순위들에 대해 변화도를 추가하여 인기 챠트를 한 눈에 알아보기 쉽게 구상할 예정입니다.

## Thanks To
- **열심히 소스 코드를 구상을 해 준 모든 참여자들**
- **Vis.js** Library