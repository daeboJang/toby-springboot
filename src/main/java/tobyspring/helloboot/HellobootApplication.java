package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		// 스프링 컨테이너 만들기
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class);		// class 정보만 넘김
		applicationContext.registerBean(SimpleHelloService.class);	// 실체 구현 class 를 bean으로 등록하면 해당 interface 를 검색해 찾아준다
		applicationContext.refresh();	// 구성정보를 이용해 bean object 만들어 초기화

		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {

				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능

					if (req.getRequestURI().equals("/hello") ) {
						// binding - 새로운 타입으로 변환하여 넘겨주는 작업
						String name = req.getParameter("name");

						// 스프링 컨테이너를 사용해 ojbect 를 받아옴
						HelloController helloController = applicationContext.getBean(HelloController.class);
						String ret = helloController.hello(name);

//						resp.setStatus(HttpStatus.OK.value());
//						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(ret);
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
				// mapping
			}).addMapping("/*");
		});

		webServer.start();
	}

}
