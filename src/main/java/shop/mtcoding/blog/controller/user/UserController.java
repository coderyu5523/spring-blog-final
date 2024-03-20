package shop.mtcoding.blog.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository ;
    private final HttpSession session ;

    @PostMapping("/join")
    public String save(UserRequest.SaveDTO requestDTO){
        userRepository.save(requestDTO);
        return "redirect:/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.Login requestDTO){

        User user = userRepository.findByUsernameAnd(requestDTO);
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
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
       User newSessionUser = userRepository.findById(sessionUser.getId());
       request.setAttribute("newSessionUser",newSessionUser);
        return "user/update-form";
    }

    @PostMapping("/user/update")
    public String update(UserRequest.Update requestDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        User newSessionUser = userRepository.updateById(sessionUser.getId(),requestDTO);
        session.setAttribute("sessionUser",newSessionUser);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";

    }
}
