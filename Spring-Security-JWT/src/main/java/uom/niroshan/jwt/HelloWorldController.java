package uom.niroshan.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String home(){
        return("Hello User Welcom To Jungle");
    }

}
