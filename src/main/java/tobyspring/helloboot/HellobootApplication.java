package tobyspring.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {
			servletContext.addServlet("hello", new HttpServlet() {
				HelloController helloController = new HelloController();

				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					// 인증, 보안, 다국어, 공통 기능

					if (req.getRequestURI().equals("/hello") ) {
						String name = req.getParameter("name");

						// binding - 새로운 타입으로 변환하여 넘겨주는 작업
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
