package coruripe.controleativos;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Run {
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}
}


// $2a$10$EB87S9INe3i0eGQZadfu4eBjKgpnct6x8Goj3PMZg3Azz0kqcY5tG
// $2a$10$0lhmmDUxd41s4lM1XLTTYu8Jj7FLylzZu23xcEUWEaarCXiQinxVm