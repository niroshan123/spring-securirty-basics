package uom.niroshan.spring_security;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {
    @GetMapping("/")
    public String home(){
        return ("<h1>Hello Everyone Security</h1>");
    }

    @GetMapping("/user")
    public String user(){
        return ("<h1>Hello User Security</h1>");
    }

    @GetMapping("/admin")
    public String admin(){
        return ("<h1>Hello Admin Security</h1>");
    }
}
