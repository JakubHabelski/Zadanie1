package xCodeSoftware.Zadanie1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardingController {

    @GetMapping(value = "/{path:[^\\.]*}")
    public String forward() {
        // Forward to the root URL (Angular will handle the routes)
        return "forward:/";
    }
}
