package devRev.user.config;


import devRev.user.entity.UserAuthEntity;
import devRev.user.entity.UserEntity;
import devRev.user.repository.UserAuthRepository;
import devRev.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userInit(UserRepository userRepository, UserAuthRepository userAuthRepository) {
        return (args) -> {
            String userName = "hello@gmail.com";
            String password = "password";

            UserEntity userEntity = UserEntity.builder()
                    .id(1)
                    .email(userName)
                    .build();

            if (userRepository.findByEmail(userName).isPresent())
                return;

            UserEntity savedUser = userRepository.save(userEntity);

            UserAuthEntity userAuth = UserAuthEntity.builder()
                    .id(savedUser.getId())
                    .password(password).build();

            userAuthRepository.save(userAuth);
        };
    }
}
