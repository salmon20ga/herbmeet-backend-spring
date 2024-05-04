package hubmeet.backend;

import hubmeet.backend.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DatabaseTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @Transactional
    public void 개인일정테스트() {
        // 유저 생성
        Users user = new Users();
        user.setUserID("test_user");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setPhone("123456789");
        entityManager.persist(user);

        // 개인 일정 생성
        PersonalSchedules personalSchedule = new PersonalSchedules();
        personalSchedule.setTitle("Test Schedule");
        personalSchedule.setStartTime(LocalDateTime.now());
        personalSchedule.setEndTime(LocalDateTime.now().plusHours(1));
        personalSchedule.setUser(user);
        personalSchedule.setColor("#AAAAAAA");
        entityManager.persist(personalSchedule);
        entityManager.flush();
        entityManager.clear();

        // 유저 조회
        Users retrievedUser = entityManager.find(Users.class, user.getUserNUM());
        assertNotNull(retrievedUser);
        assertEquals("test_user", retrievedUser.getUserID());

        // 개인 일정 조회
        PersonalSchedules retrievedSchedule = entityManager.find(PersonalSchedules.class, personalSchedule.getPScheduleID());
        assertNotNull(retrievedSchedule);
        assertEquals("Test Schedule", retrievedSchedule.getTitle());
    }

    @Test
    @Transactional
    public void 그룹테스트() {
        // 유저 생성
        Users user = new Users();
        user.setUserID("test_user");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setPhone("123456789");
        entityManager.persist(user);

        // 그룹 생성
        Groups group = new Groups();
        group.setName("Test Group");
        group.setLeaderUser(user);
        entityManager.persist(group);
        entityManager.flush();
        entityManager.clear();

        // 유저 조회
        Users retrievedUser = entityManager.find(Users.class, user.getUserNUM());
        assertNotNull(retrievedUser);
        assertEquals("test_user", retrievedUser.getUserID());

        // 그룹 조회
        Groups retrievedGroup = entityManager.find(Groups.class, group.getGroupID());
        assertNotNull(retrievedGroup);
        assertEquals("Test Group", retrievedGroup.getName());

        // 그룹의 리더 조회
        Users retrievedGroupLeader = retrievedGroup.getLeaderUser();
        assertNotNull(retrievedGroupLeader);
        assertEquals("test_user", retrievedGroupLeader.getUserID());

        // 그룹의 멤버 생성
        GroupMemberId memberId = new GroupMemberId();
        memberId.setGroupID(retrievedGroup.getGroupID());
        memberId.setUserNUM(retrievedUser.getUserNUM());

        GroupMembers groupMember = new GroupMembers();
        groupMember.setId(memberId);
        entityManager.persist(groupMember);
        entityManager.flush();
        entityManager.clear();

        // 그룹의 멤버 수 조회
        Long memberCount = entityManager.createQuery(
                        "SELECT COUNT(gm) FROM GroupMembers gm WHERE gm.group = :group", Long.class)
                .setParameter("group", retrievedGroup)
                .getSingleResult();
        assertEquals(1L, memberCount);
    }

    @Test
    @Transactional
    public void 중간지점테스트() {
        // 유저 생성
        Users user = new Users();
        user.setUserID("test_user");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setPhone("123456789");
        entityManager.persist(user);

        // 그룹 생성
        Groups group = new Groups();
        group.setName("Test Group");
        group.setLeaderUser(user);
        entityManager.persist(group);
        entityManager.flush();
        entityManager.clear();

        // 미드포인트 생성
        Midpoints midpoint = new Midpoints();
        midpoint.setName("Test Midpoint");
        midpoint.setDescription("Test Description");
        midpoint.setLatitude("123.456");
        midpoint.setLongitude("456.789");
        midpoint.setGroup(group); // 그룹 설정
        entityManager.persist(midpoint);
        entityManager.flush();
        entityManager.clear();

        // 미드포인트 조회
        Midpoints retrievedMidpoint = entityManager.find(Midpoints.class, midpoint.getMidpointID());
        assertNotNull(retrievedMidpoint);
        assertEquals("Test Midpoint", retrievedMidpoint.getName());
        assertEquals("Test Group", retrievedMidpoint.getGroup().getName());
        assertEquals("Test Description", retrievedMidpoint.getDescription());
        assertEquals("123.456", retrievedMidpoint.getLatitude());
        assertEquals("456.789", retrievedMidpoint.getLongitude());

        // 그룹에 속한 미드포인트 확인
        List<Midpoints> groupMidpoints = retrievedMidpoint.getGroup().getMidpoints();
        assertEquals(1, groupMidpoints.size());
    }

    @Test
    @Transactional
    public void 친구추가_및_목록조회_테스트() {
        // 유저 생성
        Users user1 = createUser("user1", "password1", "user1@example.com", "111111111");
        Users user2 = createUser("user2", "password2", "user2@example.com", "222222222");
        Users user3 = createUser("user3", "password3", "user3@example.com", "333333333");

        // 친구 추가
        addFriend(user1, user2);
        addFriend(user1, user3);
        addFriend(user2, user3);

        // 친구 목록 조회
        List<Users> user1Friends = getFriends(user1);
        List<Users> user2Friends = getFriends(user2);
        List<Users> user3Friends = getFriends(user3);

        // 친구 목록 검증
        assertTrue(user1Friends.contains(user2));
        assertTrue(user1Friends.contains(user3));
        assertTrue(user2Friends.contains(user1));
        assertTrue(user2Friends.contains(user3));
        assertTrue(user3Friends.contains(user1));
        assertTrue(user3Friends.contains(user2));

//        System.out.printf("user1친구 : " + user1Friends);
//        System.out.printf("user2친구 : " + user2Friends.toString());
//        System.out.printf("user3친구 : " + user3Friends.toString());
    }

    private Users createUser(String userID, String password, String email, String phone) {
        Users user = new Users();
        user.setUserID(userID);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        entityManager.persist(user);
        return user;
    }

    private void addFriend(Users user1, Users user2) {
        Friends friendship1 = new Friends();
        friendship1.setUser1(user1);
        friendship1.setUser2(user2);
        entityManager.persist(friendship1);

        Friends friendship2 = new Friends();
        friendship2.setUser1(user2);
        friendship2.setUser2(user1);
        entityManager.persist(friendship2);
    }

    private List<Users> getFriends(Users user) {
        return entityManager.createQuery(
                        "SELECT f.user2 FROM Friends f WHERE f.user1 = :user", Users.class)
                .setParameter("user", user)
                .getResultList();
    }


}
