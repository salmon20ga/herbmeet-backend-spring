package hubmeet.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GroupMembers {
    @EmbeddedId
    private GroupMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GroupID", insertable = false, updatable = false)
    private Groups group;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserNUM", insertable = false, updatable = false)
    private Users user;
}
