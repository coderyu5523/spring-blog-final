package shop.mtcoding.blog.controller.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public void save(UserRequest.SaveDTO requestDTO) {
        em.persist(requestDTO.toEntity());
    }

    public User findByUsernameAnd(UserRequest.Login requestDTO) {
        String q = """
                select u from User u where u.username = :username and u.password = :password
                """;
        Query query = em.createQuery(q,User.class);
        query.setParameter("username",requestDTO.getUsername());
        query.setParameter("password",requestDTO.getPassword());
       return (User) query.getSingleResult();
    }
}
