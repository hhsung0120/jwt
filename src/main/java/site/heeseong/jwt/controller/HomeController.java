package site.heeseong.jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.heeseong.jwt.util.Jwt;

@Controller
@RestController
public class HomeController {

    @GetMapping("/index")
    public String home() {
        return Jwt.testToken();
    }

    @PostMapping("/token")
    public String verificationJwt(@RequestParam(required = false, defaultValue = "") String jwt) {
        try {
            return "vaild";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/invalidToken")
    public String invalidToken() {
        return "invalidToken";
    }
}
