package shop.mtcoding.blog.controller.board;

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
                select b from Board b order by b.id desc 
                """;
        Query query = em.createQuery(q,Board.class);
        return query.getResultList();

    }
    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO) {
         em.persist(requestDTO.toEntity());

    }

    public Board findById(Integer boardId) {
        Board board = em.find(Board.class,boardId);
        return board;
    }

    @Transactional
    public void deleteById(int boardId) {
        String q = """
                delete from Board b where b.id =:id
                """;

        Query query = em.createQuery(q);
        query.setParameter("id",boardId);
        query.executeUpdate();
    }
    @Transactional
    public void updateById(Integer boardId, BoardRequest.UpdateDTO requestDTO) {
        Board board = findById(boardId);
        board.setTitle(requestDTO.getTitle());
        board.setContent(requestDTO.getContent());

    }
}
