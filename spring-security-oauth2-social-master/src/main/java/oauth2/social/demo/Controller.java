package oauth2.social.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class Controller {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Principal home(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/login/callback/google", method = RequestMethod.GET)
    public void ss(Principal principal) {

        System.out.println("ss");
    }
}
