package hubmeet.backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PersonalSchedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "P_ScheduleID")
    private Long pScheduleID;

    @ManyToOne
    @JoinColumn(name = "UserNUM")
    private Users user;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "StartTime", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @Column(name = "EndTime", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @Column(name = "Description")
    private String description;

    @Column(name = "Color", nullable = false)
    private String color;
}
