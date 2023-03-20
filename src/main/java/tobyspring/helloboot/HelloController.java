package tobyspring.helloboot;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(String name) {

        // null일 경우 예외발생
        return helloService.sayHello(Objects.requireNonNull(name));
    }
}
