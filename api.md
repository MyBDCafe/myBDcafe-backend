## 통합 검색

```
GET /event/search
HOST [43.201.105.59:8080]
```

|서치명|서치내용|예시|
|------|---|---|
|g|그룹/장르명|search?g=plave|
|c|주인공|search?c=한노아|
|s|카페 시작일|search?s=2024-02-02|
|e|카페 종료일|search?e=2024-02-04|

- 복합 검색 가능
- 만약 검색 기간을 하루만 지정할 시 시작일, 종료일 둘 중 하나만 입력해도 ok
<br/><br><br/>


### 리턴값

|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|***eventId***|이벤트 카페 ID|Long|x|
|eventName|이벤트 카페명|String|x|
|mainCharacter|이벤트 주인공|String|o|
|genre|장르/그룹명|String|o|
|startDate|이벤트 시작일|Date|o|
|endDate|이벤트 종료일|Date|o|
|location |카페 위치|||
|latitude|위도|String|o|
|longitude|경도|String|o|
|businessHours|영업시간|||
|day|영업일|Date|o|
|openTime|오픈 시간|Date|o|
|closeTime|종료 시간|Date|o|
|eventUrl|sns주소|String|o|
|memo|메모|String|o|
|count|검색된 이벤트 수|Long||
|size|한 페이지에 표시될 이벤트 수|int||
|page|전체 페이지 수|int||


<br/>

### 입력

```
http://43.201.105.59:8080/event/search?c=테스트
```

### 검색결과

```

{
  "content": [
    {
      "eventId": 8,
      "eventName": "테스트2",
      "mainCharacter": "테스트 이름",
      "genre": "테스트장르",
      "startDate": "2024-04-01",
      "endDate": "2024-04-03",
      "location": {
        "latitude": "테스트위도",
        "longitude": "테스트경도"
      },
      "businessHours": [
        {
          "day": "2024-04-01",
          "openTime": "10:00:00",
          "closeTime": "18:00:00"
        },
        {
          "day": "2024-04-02",
          "openTime": "10:00:00",
          "closeTime": "18:00:00"
        },
        {
          "day": "2024-04-03",
          "openTime": "10:00:00",
          "closeTime": "18:00:00"
        }
      ],
      "eventUrl": "testUrl",
      "memo": "메모"
    }
  ],
  "count": 1,
  "size": 20,
  "page": 0
}
```
<br><br/>


### 이벤트 Id를 이용해 이벤트 정보 불러오기

```
GET /getevent
HOST [43.201.105.59:8080]
```

### 리턴값

|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|eventId|이벤트 카페 ID|Long|x|
|eventName|이벤트 카페명|String|x|
|mainCharacter|이벤트 주인공|String|o|
|genre|장르/그룹명|String|o|
|startDate|이벤트 시작일|Date|o|
|endDate|이벤트 종료일|Date|o|
|location |카페 위치|||
|locationId|장소Id|Long|x|
|latitude|위도|String|o|
|longitude|경도|String|o|
|businessHours|영업시간|||
|hourId|영업시간Id|Long|x|
|day|영업일|Date|o|
|openTime|오픈 시간|Date|o|
|closeTime|종료 시간|Date|o|
|eventUrl|sns주소|String|o|
|memo|메모|String|o|

### 입력
```
http://43.201.105.59:8080/getevent/13
```

```
{
  "eventId": 13,
  "eventName": "테스트중",
  "mainCharacter": "테스트이름",
  "genre": "테스트장르",
  "startDate": "2024-01-28",
  "endDate": "2024-01-29",
  "location": {
    "locationId": 16,
    "latitude": "테스트위도",
    "longitude": "테스트 경도"
  },
  "businessHours": [
    {
      "hourId": 40,
      "day": "2024-01-28",
      "openTime": "10:00:00",
      "closeTime": "18:00:00"
    },
    {
      "hourId": 41,
      "day": "2024-01-29",
      "openTime": null,
      "closeTime": null
    },
    {
      "hourId": 42,
      "day": "2024-01-28",
      "openTime": "10:00:00",
      "closeTime": "18:00:00"
    }
  ],
  "eventUrl": "testUrl",
  "memo": "메모"
}
```

### 오류 발생시 리턴값

|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|status|오류코드|int|x|
|timestamp|오류가 일어난 시간|LocalDateTime|x|
|errorClass|오류 클래스명|String|x|
|errMsg|오류 원인|String|x|


```
{
  "status": 500,
  "timestamp": "2024-04-28T09:26:08.31663654",
  "errorClass": "java.util.NoSuchElementException",
  "errMsg": "No value present"
}
```

<br><br>

# 등록 api

```
POST /event/create
HOST 43.201.105.59:8080
```


### 입력값

|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|eventName|이벤트 카페명|String|x|
|mainCharacter|이벤트 주인공|String|o|
|genre|장르/그룹명|String|o|
|startDate|이벤트 시작일|Date|o|
|endDate|이벤트 종료일|Date|o|
|location |카페 위치|||
|latitude|위도|String|o|
|longitude|경도|String|o|
|businessHours|영업시간|||
|day|영업일|Date|o|
|openTime|오픈 시간|Date|o|
|closeTime|종료 시간|Date|o|
|eventUrl|sns주소|String|o|
|memo|메모|String|o|



```
{
    "eventName":"테스트2",
    "mainCharacter":"테스트이름",
    "genre":"테스트장르",
    "startDate":"2024-01-28",
    "endDate":"2024-01-29",
    "location":{
        "latitude":"테스트위도",
        "longitude":"테스트 경도"
    },
    "businessHours":[ 
        {
            "day":"2024-01-28",
            "openTime":"10:00:00",
            "closeTime":"18:00:00"
        },
        {
            "day":"2024-01-29",
            "openTime":"",
            "closeTime":""            
        }
    ],
    "eventUrl":"testUrl",
    "memo":"메모"
}
```

### 오류 발생시 리턴값

|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|status|오류코드|int|x|
|timestamp|오류가 일어난 시간|LocalDateTime|x|
|errorClass|오류 클래스명|String|x|
|errMsg|오류 원인|String|x|


```
{
    "status": 400,
    "timestamp": "2024-04-28T08:11:12.043144493",
    "errorClass": "org.springframework.web.bind.MethodArgumentNotValidException",
    "errMsg": "이벤트명은 필수로 입력해야합니다."
}
```

<br>

# 수정 api

```
PATCH /event/update
HOST 43.201.105.59:8080
```

### 입력값
- 등록 api와 거의 동일하나 **eventId, locationId, hourId 추가**

|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|**eventId**|이벤트 카페 ID|Long|x|
|eventName|이벤트 카페명|String|x|
|mainCharacter|이벤트 주인공|String|o|
|genre|장르/그룹명|String|o|
|startDate|이벤트 시작일|Date|o|
|endDate|이벤트 종료일|Date|o|
|location |카페 위치|||
|**locationId**|장소Id|Long|x|
|latitude|위도|String|o|
|longitude|경도|String|o|
|businessHours|영업시간|||
|day|영업일|Date|o|
|**hourId**|영업시간Id|Long|x|
|openTime|오픈 시간|Date|o|
|closeTime|종료 시간|Date|o|
|eventUrl|sns주소|String|o|
|memo|메모|String|o|

```
{
  "eventId": 13,
  "eventName": "테스트중",
  "mainCharacter": "테스트이름",
  "genre": "테스트장르",
  "startDate": "2024-01-28",
  "endDate": "2024-01-29",
  "location": {
    "locationId": 15,
    "latitude": "테스트위도",
    "longitude": "테스트 경도"
  },
  "businessHours": [
    {
      "hourId": 40,
      "day": "2024-01-28",
      "openTime": "10:00:00",
      "closeTime": "18:00:00"
    },
    {
      "hourId": 41,
      "day": "2024-01-29",
      "openTime": null,
      "closeTime": null
    }
  ],
  "eventUrl": "testUrl",
  "memo": "메모"
}
```

### 오류 발생시 리턴값

|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|status|오류코드|int|x|
|timestamp|오류가 일어난 시간|LocalDateTime|x|
|errorClass|오류 클래스명|String|x|
|errMsg|오류 원인|String|x|

```
{
    "status": 400,
    "timestamp": "2024-04-28T09:59:35.899233384",
    "errorClass": "org.springframework.web.bind.MethodArgumentNotValidException",
    "errMsg": "must not be null"
}
```
 

<br><br>

# 삭제 api

```
DELETE /event/delete/{eventId}
HOST 43.201.105.59:8080
```

이벤트 id를 통해 삭제

### 입력값

```
http://43.201.105.59:8080/event/delete/4
```

### 오류 발생시 리턴값

|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|status|오류코드|int|x|
|timestamp|오류가 일어난 시간|LocalDateTime|x|
|errorClass|오류 클래스명|String|x|
|errMsg|오류 원인|String|x|

```
{
    "status": 500,
    "timestamp": "2024-04-28T09:58:02.359965901",
    "errorClass": "javax.management.RuntimeErrorException",
    "errMsg": "해당하는 이벤트id가 없습니다."
}
```

<br><br>

### 공유 URL만들기

```
POST /createURL
HOST 43.201.27.181:8080
```


#### 입력 값
|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|id|이벤트ID|Long|x|


```
{
   "id":14
}
```


#### 리턴값

|필드명|이름|타입|null 가능여부|
|------|---|---|---|
|**url**|공유url|String|x|


```
{
    "url": "http://43.201.105.59:8080/shareEvent/D59icoUnHdTCg04Kk3fHv3HtAtide2Xn"
}
```

<br><br>

### URL로 이벤트카페 정보 불러오기

*이벤트 등록 페이지로 불러오기

```
GET /shareEvent/{encryptedId}
HOST 43.201.27.181:8080
```

### 입력
```
http://43.201.105.59:8080/shareEvent/D59icoUnHdTCg04Kk3fHv3HtAtide2Xn
```

### 리턴값

```
{
    "eventId": 13,
    "eventName": "테스트중",
    "mainCharacter": "테스트이름",
    "genre": "테스트장르",
    "startDate": "2024-01-28",
    "endDate": "2024-01-29",
    "location": {
        "latitude": "테스트위도",
        "longitude": "테스트 경도"
    },
    "businessHours": [
        {
            "day": "2024-01-28",
            "openTime": "10:00:00",
            "closeTime": "18:00:00"
        },
        {
            "day": "2024-01-29",
            "openTime": null,
            "closeTime": null
        },
        {
            "day": "2024-01-28",
            "openTime": "10:00:00",
            "closeTime": "18:00:00"
        }
    ],
    "eventUrl": "testUrl",
    "memo": "메모"
}
```
