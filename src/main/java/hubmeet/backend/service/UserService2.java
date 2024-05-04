package hubmeet.backend.service;

import hubmeet.backend.entity.User2;
import hubmeet.backend.repository.UserRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService2 {

    @Autowired
    private UserRepository2 userRepository2;

    public void create(User2 user2) {

        userRepository2.save(user2);
    }

    public List <User2> userList(){
        return userRepository2.findAll();
    }
}
