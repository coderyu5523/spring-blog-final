package shop.mtcoding.blog.controller.user;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserJPARepository {

    private final EntityManager em;

    @Transactional
    public void save(UserRequest.SaveDTO requestDTO) {
        em.persist(requestDTO.toEntity());
    }
}
