package coruripe.controleativos;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Run {
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}
}
