package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;


@NoArgsConstructor  // 빈생성자가 필요
@Entity
@Data
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String title;
    private  String content;

    @CreationTimestamp // persistance centext 에 전달될 때 자동으로 주입됨.
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;



    @Builder
    public Board(Integer id, String title, String content, Timestamp createdAt,User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.user =user;
    }

    public void Update(String title,String content){
        this.title = title;
        this.content = content;
    }
}



