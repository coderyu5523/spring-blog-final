package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserRepository {

    private final EntityManager em;

    @Transactional
    public void save(UserRequest.JoinDTO requestDTO) {
        String q = """
                insert into user_tb(username,password,email,created_at) values(?,?,?,now())
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1,requestDTO.username);
        query.setParameter(2,requestDTO.password);
        query.setParameter(3,requestDTO.email);
        query.executeUpdate();

    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO requestDTO) {
        String q = """
                select * from user_tb where username =? and password =? 
                """;
        Query query = em.createNativeQuery(q,User.class);
        query.setParameter(1,requestDTO.getUsername());
        query.setParameter(2,requestDTO.getPassword());
        return (User) query.getSingleResult();


    }
}
