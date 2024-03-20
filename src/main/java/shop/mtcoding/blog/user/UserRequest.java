package shop.mtcoding.blog.user;

import lombok.Data;

public class UserRequest {
    @Data
    public static class JoinDTO{
        public String username ;
        public String password ;
        public String email ;

    }
    @Data
    public static class LoginDTO{
        public String username;
        public String password;
    }

}
