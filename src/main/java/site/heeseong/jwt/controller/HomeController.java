package site.heeseong.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.heeseong.jwt.service.TokenService;

@Controller
@RestController
public class HomeController {

    private final TokenService tokenService;
    private HomeController (TokenService tokenService){
        this.tokenService = tokenService;
    }

    @GetMapping("/index")
    public String home(){
        return tokenService.testToken();
    }

    @GetMapping("/token")
    public String verificationJwt(@RequestParam(required = false, defaultValue = "") String jwt){
        tokenService.verificationJwt(jwt);
        return "유효";
    }
}
