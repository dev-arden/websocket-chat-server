package com.websocket.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.websocket.chat.model.User;
import com.websocket.chat.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	@GetMapping("/login")
    public String LoginPage() {
        return "/chat/LoginPage";
    }

    @PostMapping("/login")
    @ResponseBody
    public String Login(@RequestParam("id") String id
                       , @RequestParam("password") String password) throws Exception {

        String path = "";

        User user = new User();

        user.setId(id);
        user.setPassword(password);

        int result = userservice.Login(user);

        if(result == 1) {
            path = "/chat/room";
        } else {
            path = "/chat/LoginPage";
        }

        return path;

    }
}

/*
 * @Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String LoginPage() {
        return "/common/LoginPage";
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public String Login(@RequestParam("id") String id
                       , @RequestParam("password") String password) throws Exception {

        String path = "";

        MemberVO vo = new MemberVO();

        vo.setId(id);
        vo.setPassword(password);

        int result = memberServiceImpl.Login(vo);

        if(result == 1) {
            path = "home";
        } else {
            path = "/common/LoginPage";
        }

        return path;

    }

}
 * 
 * */