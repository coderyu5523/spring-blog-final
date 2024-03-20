package shop.mtcoding.blog.controller.board;


import lombok.Data;
import shop.mtcoding.blog.controller.user.User;

public class BoardRequest {

    @Data
    public static class SaveDTO{

        private String title;
        private String content ;

        public Board toEntity(User user){
           return Board.builder()
                    .title(title)
                    .content(content)
                   .user(user)
                    .build();

        }

    }
    @Data
    public static class UpdateDTO{
        private String title;
        private String content ;
    }

}
