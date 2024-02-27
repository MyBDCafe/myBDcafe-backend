## 등록 api
```
POST /events
HOST 13.209.154.40:8080
```



|필드명|타입|타입|null 가능여부|
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
            "openTime":"",
            "closeTime":""
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
<br><br>

## 검색 api

```
GET /search
HOST [13.209.154.40:8080]
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

|필드명|타입|타입|null 가능여부|
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
|count|검색된 이벤트 수|Long||
|size|한 페이지에 표시될 이벤트 수|int||
|page|전체 페이지 수|int||


<br/><br><br/>

검색 결과
```
http://13.209.154.40:8080/search?c=테스트
```
```
// 20240120164838
// http://13.209.154.40:8080/search?g=p&c=%EB%85%B8%EC%95%84&s=2024/02/02

{
  "content": [
    {
      "eventName": "테스트1",
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
                    "openTime": null, 
                    "closeTime": null 
                },
                {
                    "day": "2024-01-29",
                    "openTime": null, 
                    "closeTime": null
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

