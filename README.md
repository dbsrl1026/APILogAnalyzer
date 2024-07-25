# APILogAnalyzer
객체지향적 설계원칙을 고려한 java 프로그램 실습 자료   
Java 17   
JUnit 4.13.2   
    

## 폴더구조
```
src/
│
├── main/
│   ├── java/
│   │   ├── analysis/
│   │   │   ├── AnalysisAPIKeyUsage.java
│   │   │   ├── AnalysisBrowserUsage.java
│   │   │   └── AnalysisAPIKeyUsage.java
│   │   │
│   │   ├── reader/
│   │   │   ├── APILogReader.java
│   │   │   └── LogData.java
│   │   │
│   │   ├── writer/
│   │   │   └── ReportFileWriter.java
│   │   │
│   │   └── AnalysisManager.java
│   │
│   └── resource/
│
└── test/
      ├── java/
      │   ├── analysis/
      │   │   ├── AnalysisAPIKeyUsageTest.java
      │   │   ├── AnalysisBrowserUsageTest.java
      │   │   └── AnalysisAPIKeyUsageTest.java
      │   │
      │   ├── reader/
      │   │   └── APILogReaderTest.java
      │   │
      │   └── writer/
      │        └── ReportFileWriterTest.java
      │
      └── resource/
```


## 구조 설계
   
   
**src/** : 개발과 테스팅을 효율적으로 하기 위해 main과 test라는 두 개의 별도의 폴더를 두어 실제 애플리케이션 코드와 단위 테스트 코드를 분리하였습니다. main과 test는 서로 같은 폴더 구조를 갖습니다.

**main/** : 실제 애플리케이션 코드가 저장됩니다. 각 클래스들은 최소한의 책임을 갖도록 분리하고, 비슷한 용도의 클래스들을 한 패키지로 묶어 관련된 클래스들을 쉽게 찾고 관리할 수 있게 하였습니다.

**analysis/** : 데이터 분석을 담당하는 모든 클래스를 모아둡니다.

**reader/** : 로그 파일을 읽고 데이터를 처리하는 클래스를 포함합니다.

**writer/** : 분석 결과를 파일로 출력하는 클래스를 포함합니다.

**resource/** : 입출력 파일을 저장합니다.

**test/** : junit을 사용한 단위 테스트 코드가 저장됩니다.main과 동일하게 test 폴더 아래에 각 컴포넌트별로 테스트 클래스들을 위치시키며, 각 테스트 클래스의 이름은 대응되는 실제 클래스의 이름에 Test를 붙여 관리하기 편하게 하였습니다.
   
      
## 코드 설계
   
   
**main/**   
AnalysisManager.java : 전체적인 흐름을 관리하는 메인 클래스입니다. 애플리케이션의 진입점 역할을 하기 때문에, 프로젝트의 구조를 쉽게 파악할 수 있도록 별도의 패키지의 포함시키지 않고 main 디렉토리 바로 아래에 두었습니다.
   
**reader/**   
APILogReader.java : 로그 파일을 읽고 각 줄의 데이터를 분석 및 파싱하여 저장하는 전체 프로세스를 담당합니다. 각 줄의 상태 코드가 200 일 경우에만 해당 줄을 읽도록 했습니다. split() 과 substring() 을 통해 파싱하여 LogData 객체로 저장합니다. 추후 기능 확장 가능성을 고려해 ‘요청 시간’도 LocalDateTime 형태로 저장하였습니다.    
LogData.java : 데이터를 캡슐화하고, 관련 데이터와 기능을 하나의 단위로 묶어 관리하기 위한 클래스입니다. APILogReader 클래스만 참조하고 있고 별도의 데이터 객체 관련 패키지가 불필요하다고 판단해 reader 패키지에 위치시켰습니다. 생성자와 getter을 포함합니다.   
   
**analysis/**   
AnalysisAPIKeyUsage.java : 메인 클래스로부터 LogData 객체 리스트를 받아와 가장 많은 apiKey를 반환합니다.   
AnalysisBrowserUsage.java : 메인 클래스로부터 LogData 객체 리스트를 받아와 각 브라우저별 비율을 내림차순로 정렬해 백분율 형태로 반환합니다.   
AnalysisTopServiceID.java : 메인 클래스로부터 LogData 객체 리스트를 받아와 apiServiceId 개수를 집계하고, 내림차순으로 정렬해 상위 3개와 각각의 요청 수를 반환합니다.   
위 클래스 공통적으로 람다 표현식과 stream(), 이중 콜론을 사용하여 코드를 간결하게 하였고 가독성을 높혔습니다.   
   
   
**writer/**   
ReportFileWriter.java : 각 분석의 결과를 output.log 에 출력합니다. 호환성을 고려하여 UTF-8 인코딩을 사용하였습니다.   

## TIL 모음   

**람다식** : 람다식은 메소드를 하나의 식으로 표현한 것을 말한다.   

**스트림** : 스트림은 컬렉션의 저장 요소를 하나씩 참조해서 람다식으로 처리할 수 있도록 해 주는 내부 반복자이다.

**이중 콜론** : 람다식에서 파라미터를 중복해서 사용하고 싶지 않을 때 사용하고,   
람다식과 동일한 처리 방법을 갖긴 하지만, 이름으로 기존 메소드를 참조함으로써 더욱 보기 쉽게 사용할 수 있다.   
   
   
**패키지를 분리하는 이유** : 최소 권한 원칙에 의거해 클래스나 메소드의 접근 범위를 설정할 때는 항상 최소한의 권한을 부여하는 것이 좋습니다.   
이는 시스템의 보안성을 높이고, 불필요한 의존성을 줄이며 관련된 클래스들을 쉽게 찾고 관리할 수 있게 합니다.     
또한, 새로운 기능이나 클래스가 추가될 때도 어디에 위치시켜야 할지 명확해져 프로젝트의 확장성과 유지보수성이 향상됩니다.   

**메인클래스에서 Analysis의 하위 클래스로 파라미터를 넘길 때 필요한 데이터만 넘기지 않고 LogData 객체 전체를 보낸 이유** :   
객체를 넘기는 것이 캡슐화 측면에서 객체 지향 설계 원칙에 더 부합하다 생각했습니다. 또한 관련 데이터와 기능을 하나의 단위로 묶어 관리하여 코드의 가독성과 유지 보수성을 향상할 수 있습니다.   
모든 데이터를 넘김으로써 생기는 메모리 낭비는 스트림 처리를 통해 어느정도 해소하였으나,   
LogData 객체의 크기가 커질경우 필요한 데이터만 보내는 방식을 고려해야 합니다.   
