package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService ;
    private final HttpSession session;

    @GetMapping("/" )
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardService.findAll();
        request.setAttribute("boardList",boardList);
        return "index";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO){
        boardService.save(requestDTO);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id,HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardService.findByIdJoinUser(sessionUser,id);
        request.setAttribute("board",board);
        return "board/detail";
    }
}
