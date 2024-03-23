package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public void save(BoardRequest.SaveDTO requestDTO) {
        boardJPARepository.save(requestDTO.toEntity());
    }


    public Board findByIdJoinUser(User sessionUser, Integer boardId) {
      Board board = boardJPARepository.findByIdJoinUser(boardId);
      return board ;
    }
}
