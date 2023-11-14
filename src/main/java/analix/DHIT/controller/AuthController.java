package analix.DHIT.controller;


import analix.DHIT.input.MemberSearchInput;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String defaultRouting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_MANAGER"))) {
            return "redirect:/manager/home";
        } else if (authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ROLE_MEMBER"))) {
            return "redirect:/member/home";
        }
        return "redirect:/login";

    }

    @GetMapping("/login")
    public String displayLogin() {
        return "common/login";
    }

}
