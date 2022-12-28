package devRev.user.controller;

import devRev.user.dto.UserSignUpDTO;
import devRev.user.service.UserServiceSignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import devRev.user.dto.User;
import devRev.user.entity.UserAuthEntity;
import devRev.dto.SuccessResponse;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceSignUp userServiceSignUp;
    @GetMapping("/login")
    public User login(Authentication authentication) {
        UserAuthEntity user = (UserAuthEntity) authentication.getPrincipal();
        return User.builder()
                .id(user.getId())
                .username(user.getUsername()).build();
    }
    @PostMapping
    public ResponseEntity<SuccessResponse> create(@RequestBody UserSignUpDTO userSignUpDTO) throws Exception {
        if (userSignUpDTO.getEmail() == null || userSignUpDTO.getEmail().equals("")) {
            throw new IllegalArgumentException("Email field is required");
        }
        if (userSignUpDTO.getMobileNumber() == null || userSignUpDTO.getMobileNumber().equals("")) {
            throw new IllegalArgumentException("Mobile number field is required");
        }
        if (userSignUpDTO.getPassword() == null || userSignUpDTO.getPassword().equals("")) {
            throw new IllegalArgumentException("Password is required");
        }
        int id = userServiceSignUp.signUp(userSignUpDTO);


        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse(id));
    }
    @GetMapping("/logout")
    public void logout(){}
}
