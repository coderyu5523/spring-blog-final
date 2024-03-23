package shop.mtcoding.blog.board;

import lombok.Data;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.user.User;

public class BoardResponse {

    @Data
    public static class DetailDTO{
        private int id ;
        private String title;
        private String content ;
        private UserDTO user ;
        private Boolean isBoardOwner ;

        public DetailDTO(Board board, User sessionuser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.user = new UserDTO(board.getUser());
            this.isBoardOwner = false;
            if(sessionuser !=null){
                if(sessionuser.getId()==board.getUser().getId()){
                    isBoardOwner = true;
                }
            }
        }
        @Data
        private class UserDTO{
            private int id ;
            private String username ;

            public UserDTO(User user){
                this.id =user.getId();
                this.username =user.getUsername();
            }
        }

    }
}
