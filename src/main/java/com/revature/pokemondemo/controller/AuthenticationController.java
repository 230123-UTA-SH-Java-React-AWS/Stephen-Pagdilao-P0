package com.revature.pokemondemo.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.revature.pokemondemo.model.Trainer;
import com.revature.pokemondemo.service.TrainerService;
import com.revature.pokemondemo.util.JwtTokenUtil;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {
    private TrainerService trainServ;
    private JwtTokenUtil jwttoken;

    public AuthenticationController(TrainerService trainerService, JwtTokenUtil jwtTokenUtil) {
        this.trainServ = trainerService;
        this.jwttoken = jwtTokenUtil;
    }


    @RequestMapping(method = RequestMethod.POST, value = "login")
    public Trainer login(@RequestBody Trainer trainer, HttpServletResponse response)
    {
        Trainer foundTrainer = this.trainServ.findTrainer(trainer);

        if (foundTrainer.getName().equals("Trainer was not found"))
        {
            response.setStatus(401);
        }
        else 
        {
            Cookie jwtcookie = new Cookie("jwt", jwttoken.generateToken(trainer));
            jwtcookie.setHttpOnly(true);
            jwtcookie.setSecure(true);
            response.addCookie(jwtcookie);
        }
        
        return this.trainServ.findTrainer(trainer);
    }

    @RequestMapping(method = RequestMethod.POST, value = "logout")
    public void logout(HttpServletResponse response, @CookieValue(name = "jwt") String jwt)
    {
        if(jwt.equals(""))
        {
            response.setStatus(404);
        }
        else
        {
            Cookie removeCookie = new Cookie("jwt", "");
            response.addCookie(removeCookie);
            response.setStatus(200);
        }
    }
}
