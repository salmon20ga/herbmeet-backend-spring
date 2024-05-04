package hubmeet.backend.controller;

import hubmeet.backend.DTO.LoginRequest;
import hubmeet.backend.DTO.RegisterRequest;
import hubmeet.backend.domain.Users;
import hubmeet.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginRequest request) {
        // 이메일과 비밀번호로 사용자 확인
        Users user = userService.findUserByEmailAndPassword(request.getEmail(), request.getPassword());

        if (user != null) {
            // 사용자가 존재하면 로그인 성공
            return "redirect:/"; // 홈페이지로 이동
        } else {
            // 사용자가 존재하지 않으면 다시 로그인 페이지로 이동
            return "redirect:/users/login";
        }
    }

    @GetMapping("/register")
    public String RegistrationForm() {
        return "registration"; // registration.html로 이동
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterRequest request) {
        // DTO로부터 받은 정보를 기반으로 새로운 사용자 생성
        Users newUser = new Users();
        newUser.setUserID(request.getUserID());
        newUser.setPassword(request.getPassword());
        newUser.setEmail(request.getEmail());
        newUser.setPhone(request.getPhone());

        // 새로운 사용자 저장
        userService.saveUser(newUser);

        return "redirect:/";
    }

}
