package coruripe.controleativos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import coruripe.controleativos.config.FilterConfig;

@SpringBootApplication
public class ControleAtivosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleAtivosApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<FilterConfig> loggingFilter() {
		FilterRegistrationBean<FilterConfig> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new FilterConfig());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}
