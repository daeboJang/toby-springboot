package tobyspring.helloboot;

import java.util.Objects;

public class HelloController {
    public String hello(String name) {
        SimpleHelloService helloService = new SimpleHelloService();

        // null일 경우 예외발생
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
