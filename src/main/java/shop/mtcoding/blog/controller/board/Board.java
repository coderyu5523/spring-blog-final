package shop.mtcoding.blog.controller.board;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.controller.user.User;

import java.sql.Timestamp;

@NoArgsConstructor  // 빈생성자가 필요
@Entity
@Data
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title ;
    private String content ;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user ;

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Board(Integer id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
