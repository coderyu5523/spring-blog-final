package shop.mtcoding.blog.controller.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog.controller.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository ;
    private final HttpSession session;

    @GetMapping("/" )
    public String index(HttpServletRequest request) {

       List<Board> boardList = boardRepository.findAll();
       request.setAttribute("boardList",boardList);
       return "index";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO requestDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");

        boardRepository.save(requestDTO,sessionUser);
        return "redirect:/";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id,HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        User sessionUser = (User) session.getAttribute("sessionUser");
        Boolean isBoardOwner = false ;

        if(sessionUser!=null){
            if(sessionUser.getId()==board.getUser().getId()){
                isBoardOwner =true ;
            }
            request.setAttribute("isBoardOwner",isBoardOwner);
        }

        request.setAttribute("board",board);

        return "board/detail";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable int id){

        boardRepository.deleteById(id);

        return "redirect:/" ;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id,HttpServletRequest request){

       Board board = boardRepository.findById(id);
        request.setAttribute("board",board);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id,BoardRequest.UpdateDTO requestDTO){

        boardRepository.updateById(id,requestDTO);

        return "redirect:/board/"+id ;
    }

}
