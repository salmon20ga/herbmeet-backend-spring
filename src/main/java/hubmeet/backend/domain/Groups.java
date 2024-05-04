package hubmeet.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "MeetingGroup")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GroupID")
    private Long groupID;

    @ManyToOne
    @JoinColumn(name = "LeaderUserNUM")
    private Users leaderUser;

    @Column(name = "Name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "group")
    private List<GroupMembers> groupMembers = new ArrayList<>();

    // Midpoints와의 일대다 매핑
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Midpoints> midpoints = new ArrayList<>();
}
