package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.err.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardJPARepository boardJPARepository ;

    public List<Board> findAll() {
       List<Board> boardList = boardJPARepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
       return boardList;
    }
    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO,User sessionUser) {
        boardJPARepository.save(requestDTO.toEntity(sessionUser));
    }


    public BoardResponse.DetailDTO findByIdJoinUser(User sessionUser, Integer boardId) {
      Board board = boardJPARepository.findByIdJoinUser(boardId).orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

      return new BoardResponse.DetailDTO(board,sessionUser) ;
    }
    @Transactional
    public void deleteById(Integer id) {
      boardJPARepository.findById(id).orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
      boardJPARepository.deleteById(id);
    }
}
