package devRev.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EncoderConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                String password = rawPassword.toString();
                System.out.println(password);

                boolean isPasswordMatch = passwordEncoder.matches(password, encodedPassword);
                System.out.println("Password : " + password + "   isPasswordMatch    : " + isPasswordMatch);
                return isPasswordMatch;
            }
        };
    }
}
