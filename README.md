# 토비의 Springboot 강의

### 연습 코드

#### 독립 실행형 스프링 애플리케이션

- HelloController 를 컨테이너에 직접 bean 으로 등록

#### Dependency Injection
- A클래스가 B에 의존적이라는거, B의 변경이 A에 영향을 준다
- B가 바뀌면 A도 변경이 발생할 확률이 크다
- interface 를 사용하여 의존성을 해결한다 (소스코드 레벨에서 의존성 해결)
- interface 에서 실제 구현한 class 가 무엇인지 연관 관계를 만들어 주는 과정을 DI 라고 부른다
- DI 작업에는 제3의 존재가 필요, 이것을 Assembler 라고 함
- Spring Container 가 Assembler 이다

#### 의존 오브젝트 DI 적용
- HelloService 를 Spring Controller 가 Assembler 로서 동작하게 수정
- HelloService 인터페이스 추가
- bean으로 등록 하여 DI 실행
```
  applicationContext.registerBean(SimpleHelloService.class);
```

#### 

