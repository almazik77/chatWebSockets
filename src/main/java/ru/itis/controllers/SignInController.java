package ru.itis.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dto.SignInDto;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SignInController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/signIn")
    public ModelAndView getSignInPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign_in");
        return modelAndView;
    }

    @SneakyThrows
    @PostMapping("/signIn")
    public String signIn(@ModelAttribute SignInDto signInDto, HttpServletRequest request, HttpServletResponse response) {

        User user = userService.signIn(signInDto);

        String cookie = objectMapper.writeValueAsString(UserDto.from(user));

        //response.addCookie(new Cookie("user", cookie));
        request.getSession().setAttribute("email", user.getEmail());

        System.out.println(request.getSession().getAttribute("email"));

        return "redirect:/rooms";

    }
}
