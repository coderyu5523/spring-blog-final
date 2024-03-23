package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepository ;

    @Transactional
    public User save(UserRequest.SaveDTO requestDTO) {
       User user = userJPARepository.save(requestDTO.toEntity());

        return user ;
    }
}
