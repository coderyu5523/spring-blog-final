package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    public List<Board> findAll() {
        String q = """
                select * from board_tb order by id desc ;
                """;

        Query query = em.createNativeQuery(q,Board.class);
        List<Board> boardList = query.getResultList();
        return boardList ;
    }

    public BoardResponse.DetailDTO findByIdJoinUser(int boardId) {
        String q = """
                select b.id,b.title,b.content,b.user_id,u.username from board_tb b inner join user_tb u on u.id = b.user_id  where b.id =?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,boardId);

        Object[] row = (Object[]) query.getSingleResult();
        Integer id = (Integer) row[0];
        String title= (String) row[1];
        String content = (String) row[2];
        Integer userId = (Integer) row[3];
        String username = (String) row[4];

        BoardResponse.DetailDTO responseDTO = new BoardResponse.DetailDTO();

        responseDTO.setId(id);
        responseDTO.setTitle(title);
        responseDTO.setContent(content);
        responseDTO.setUserId(userId);
        responseDTO.setUsername(username);

        return responseDTO;
    }

    @Transactional
    public void save(BoardRequest.saveDTO requestDTO,int userId) {
        String q = """
                insert into board_tb(title,content,user_id,created_at) values(?,?,?,now())
                """;

        Query query = em.createNativeQuery(q);
        query.setParameter(1,requestDTO.getTitle());
        query.setParameter(2,requestDTO.getContent());
        query.setParameter(3,userId);
        query.executeUpdate();

    }
    @Transactional
    public void deleteById(Integer boardId) {
        String q = """
                delete from board_tb where id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,boardId);
        query.executeUpdate();


    }
}
