package coruripe.controleativos.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authHttp) -> authHttp
				.requestMatchers("/script/imports.js").permitAll()
				.requestMatchers("/css/login.css").permitAll()
				.requestMatchers("/cad/usuario/**").hasAnyAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(formLg -> formLg
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/dashboard")
			)
			.logout(lgOut -> lgOut
				.logoutUrl("/logout").permitAll()
			)
			.exceptionHandling(exHandl -> exHandl
				.accessDeniedPage("/error/access-denied")
			);

		return http.build();
	}

}
