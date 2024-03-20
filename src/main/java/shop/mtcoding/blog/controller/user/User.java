package shop.mtcoding.blog.controller.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;
@NoArgsConstructor
@Data //게터세터
@Entity  // 이게 붙으면 테이블 생성됨
@Table(name="user_tb") // 테이블명
public class User {
    @Id // 프라이머리키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id ;
    @Column(unique = true) // 유저네임은 유니크
    private String username;
    @Column(length = 60,nullable = false) //비밀번호 길이는 60, null 불가
    private String password;
    private String email;

    @CreationTimestamp // persistance centext 에 전달될 때 자동으로 주입됨.
    private Timestamp createdAt;

    @Builder
    public User(int id, String username, String password, String email, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }
}
