package app.thymeleaf;

/**
 * Created by saifularifin on 6/20/2016.
 * Controller Untuk Springboot + thymeleaf
 * Like SPRING MVC yg akan mengakses halaman index pada resources/templates/index.html
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
