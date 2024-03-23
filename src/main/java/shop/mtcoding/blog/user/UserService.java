package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.err.exception.Exception401;
import shop.mtcoding.blog._core.err.exception.Exception404;

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

    public User findById(int sessionUserId) {
       User user = userJPARepository.findById(sessionUserId).orElseThrow(() -> new Exception404("조회된 데이터가 없습니다."));

        return user;
    }
    @Transactional
    public void updateById(UserRequest.UpdateDTO requestDTO, int sessionUserId) {
       User user = userJPARepository.findById(sessionUserId).orElseThrow(() -> new Exception404("조회된 데이터가 없습니다."));
       user.update(requestDTO.getPassword(),requestDTO.getEmail());
    }
}
