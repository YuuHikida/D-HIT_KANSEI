package analix.DHIT.controller;


import analix.DHIT.input.MemberSearchInput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Controller
public class AuthController {

    @GetMapping("/")
    public String displayHome(
            Model model
    ) {
        return "redirect:/manager/home";
    }

    @GetMapping("/login")
    public String displayLogin(){
        return "common/login";
    }



}
