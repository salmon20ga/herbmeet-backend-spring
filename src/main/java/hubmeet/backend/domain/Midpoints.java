package hubmeet.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Midpoints {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MidpointID")
    private Long midpointID;

    @Column(name = "Name", nullable = false, length = 100) // 추가: Name 컬럼 매핑
    private String name;

    @Column(name = "Description", length = 100) // 추가: Description 컬럼 매핑
    private String description;

    @Column(name = "Latitude", nullable = false, length = 100) // 추가: Latitude 컬럼 매핑
    private String latitude;

    @Column(name = "Longitude", nullable = false, length = 100) // 추가: Longitude 컬럼 매핑
    private String longitude;

    @ManyToOne(optional = false) // 수정: nullable 속성 추가
    @JoinColumn(name = "GroupID", nullable = false) // 수정: nullable 속성 추가
    private Groups group;
}
