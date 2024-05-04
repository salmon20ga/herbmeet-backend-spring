package hubmeet.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserNUM")
    private Long userNUM;

    @Column(name = "UserID", unique = true, nullable = false)
    private String userID;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Column(name = "Phone", nullable = false)
    private String phone;

}
