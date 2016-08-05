package hello;

/**
 * Created by saifularifin on 6/20/2016.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
    @RequestMapping("/home")
    public String index() {
        return "index";
    }
}
