package hubmeet.backend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class GroupMemberId implements Serializable {
    @Column(name = "GroupID")
    private Long groupID;

    @Column(name = "UserNUM")
    private Long userNUM;
}
