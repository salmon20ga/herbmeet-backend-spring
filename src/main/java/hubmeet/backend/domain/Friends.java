package hubmeet.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Friends {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FriendsID")
    private Long friendsID;

    @ManyToOne
    @JoinColumn(name = "UserNUM1")
    private Users user1;

    @ManyToOne
    @JoinColumn(name = "UserNUM2")
    private Users user2;

}
