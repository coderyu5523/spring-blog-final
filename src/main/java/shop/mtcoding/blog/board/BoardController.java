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

    private final BoardRepository boardRepository;
    private final HttpSession session;

    @GetMapping("/" )
    public String index(HttpServletRequest request) {

       List<Board> boardList = boardRepository.findAll();
       request.setAttribute("boardList",boardList);
        return "index";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request){
        User sessionUser = (User) session.getAttribute("sessionUser");
       BoardResponse.DetailDTO responseDTO = boardRepository.findByIdJoinUser(id);
        Boolean isBoardOwner = false ;

        if(sessionUser!=null){
           if(responseDTO.getUserId() == sessionUser.getId()){
               isBoardOwner = true ;
           }
           request.setAttribute("isBoardOwner",isBoardOwner);
       }

        request.setAttribute("responseDTO",responseDTO);
        return "board/detail";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.saveDTO requestDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardRepository.save(requestDTO,sessionUser.getId());

        return "redirect:/" ;
    }

}
