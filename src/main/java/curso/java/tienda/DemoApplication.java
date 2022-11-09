package curso.java.tienda;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import curso.java.tienda.utils.HiloEstadoPedidos;


@Configuration
@ComponentScan(basePackages = {"curso.java.tienda"})
@SpringBootApplication(scanBasePackages={
		"curso.java.tienda"})
public class DemoApplication {

	static Logger logger = Logger.getLogger(DemoApplication.class);
	

	@Autowired
	static HiloEstadoPedidos hiloEstadoPedidos;
	
	public static void main(String[] args) {
		logger.info("Log4j appender configuration is successful !!");
		ThreadContext.put("logFolder", "log1");
		ThreadContext.put("LogFileName", "log");
		
		SpringApplication.run(DemoApplication.class, args);
		
	}
	


}
