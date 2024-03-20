package shop.mtcoding.blog.controller.user;

import lombok.Data;

public class UserRequest {
    @Data
    public static class SaveDTO{
        private String username ;
        private String password ;
        private String email ;

        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }

    }
    @Data
    public static class Login{

        private String username;
        private String password ;
    }

}
