package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.err.exception.Exception401;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository ;

    @Transactional
    public User save(UserRequest.SaveDTO requestDTO) {
       User user = userJPARepository.save(requestDTO.toEntity());

        return user ;
    }

    public User findByUsernameAndPassword(String username, String password) {
       User user = userJPARepository.findByUsernameAndPassword(username,password)
               .orElseThrow(() -> new Exception401("인증되지 않았습니다"));

       return user ;
    }
}
