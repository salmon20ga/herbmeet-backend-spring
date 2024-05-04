package hubmeet.backend.service;

import hubmeet.backend.domain.Users;
import hubmeet.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(Users user) {
        validateDuplicateUser(user);
        userRepository.save(user);
    }

    private void validateDuplicateUser(Users user) {
        Users existingUserByEmail = userRepository.findByUserID(user.getUserID());
        if (existingUserByEmail != null) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }

        Users existingUserByUserID = userRepository.findByUserID(user.getUserID());
        if (existingUserByUserID != null) {
            throw new IllegalStateException("이미 존재하는 사용자 ID입니다.");
        }
    }

    public Users findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users findUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
