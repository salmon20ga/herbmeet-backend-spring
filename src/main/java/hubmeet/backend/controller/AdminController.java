package hubmeet.backend.controller;

import hubmeet.backend.entity.User2;
import hubmeet.backend.service.UserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @Autowired
    private UserService2 userService2;

    @GetMapping("/admin/users/form")
    public String adminWriteForm() {

        return "adminWrite";
    }


    @PostMapping("/admin/createpro")
    public String adminCreatePro(User2 user2){

        userService2.create(user2);

        return "adminWrite";
    }

    @GetMapping("/admin/user/list")
    public String userList(Model model) {

        model.addAttribute("list", userService2.userList());

        return "userList";
    }
}
