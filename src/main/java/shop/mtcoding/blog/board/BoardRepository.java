package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
