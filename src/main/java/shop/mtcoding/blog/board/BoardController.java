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
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.save(requestDTO,sessionUser);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id,HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        BoardResponse.DetailDTO responseDTO = boardService.findByIdJoinUser(sessionUser,id);
        request.setAttribute("board",responseDTO);
        return "board/detail";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){
        boardService.deleteById(id);

        return "redirect:/";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id,HttpServletRequest request){
        Board board = boardService.findById(id);
        request.setAttribute("board",board);
        return "/board/update-form" ;
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id,BoardRequest.UpdateDTO requestDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.updateById(id,sessionUser,requestDTO);

        return "redirect:/board/"+id;
    }

}
