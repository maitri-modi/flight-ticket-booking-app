package devRev.user.service;

import devRev.user.EmailValidation;
import devRev.user.MobileNumberValidation;
import devRev.user.PasswordValidation;
import devRev.user.dto.UserSignUpDTO;
import devRev.user.entity.UserAuthEntity;
import devRev.user.entity.UserEntity;
import devRev.user.exception.UserAlreadyExistException;
import devRev.user.repository.UserAuthRepository;
import devRev.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceSignUp {
    private final UserRepository userRepository;
    private final UserAuthRepository userAuthRepository;
    private final PasswordValidation passwordValidation = new PasswordValidation();
    private final MobileNumberValidation mobileNumberValidation = new MobileNumberValidation();
    private final EmailValidation emailValidation = new EmailValidation();

    UserServiceSignUp(UserRepository userRepository, UserAuthRepository userAuthRepository) {
        this.userRepository = userRepository;
        this.userAuthRepository = userAuthRepository;
    }

    public int signUp(UserSignUpDTO userSignUpDTO) throws UserAlreadyExistException {
        UserEntity userEntity = UserEntity.builder()
                .email(userSignUpDTO.getEmail())
                .mobile_number(userSignUpDTO.getMobileNumber()).build();

        Optional<UserEntity> user = userRepository.findByEmail(userSignUpDTO.getEmail());

        if (user.isPresent()) {
            throw new UserAlreadyExistException("User Already exists");
        }

        if (passwordValidation.checkPassword(userSignUpDTO.getPassword()) && mobileNumberValidation.isMobileNumberValid(userSignUpDTO.getMobileNumber())
                && emailValidation.isEmailValid(userSignUpDTO.getEmail())) {
            userEntity = userRepository.save(userEntity);
            UserAuthEntity userAuthEntity = UserAuthEntity.builder().id(userEntity.getId()).password(userSignUpDTO.getPassword()).build();
            userAuthEntity.setPassword(new BCryptPasswordEncoder().encode(userAuthEntity.getPassword()));
            userAuthRepository.save(userAuthEntity);
        }
        return userEntity.getId();
    }
}