package tobyspring.helloboot;

public class SimpleHelloService implements HelloService {
    @Override
    public String sayHello(String name) {
        return "HelloService) Hello " + name;
    }
}
