package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.UserTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.err.exception.Exception400;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession session;

    @PostMapping("/join")
    public String join(UserRequest.SaveDTO requestDTO){
        try {
          User user = userService.save(requestDTO);
            session.setAttribute("sessionUser",user);
        } catch (Exception e) {
            throw new Exception400("동일한 유저네임이 존재합니다.");
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserRequest.Login requestDTO){
        User user = userService.findByUsernameAndPassword(requestDTO.getUsername(),requestDTO.getPassword());
        session.setAttribute("sessionUser",user);
        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm() {
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
