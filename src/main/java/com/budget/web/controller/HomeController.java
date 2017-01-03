package com.budget.web.controller;

import com.budget.dao.entities.Category;
import com.budget.dao.entities.User;
import com.budget.services.ICategoryService;
import com.budget.services.IPlaceService;
import com.budget.services.IUserService;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.lang.model.util.Elements;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by home on 14.11.16.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    public HomeController(IUserService userService, IPlaceService placeService, ICategoryService categoryService){
        this.userService = userService;
        this.placeService = placeService;
        this.categoryService = categoryService;
    }

    private final IUserService userService;
    private final IPlaceService placeService;
    private final ICategoryService categoryService;


    //получение стартовой страницы
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String startPage(@AuthenticationPrincipal User user, ModelMap modelMap, HttpServletRequest request){
        int i = 0;
        return "startPage";
    }

    //получение стр логина
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login() {
        return "login";
    }

    //получение формы из страницы логина
    @RequestMapping(method = RequestMethod.GET, value = "/login", params = {"auth=fail"})
    public String login(ModelMap model) {
        model.put("loginFailed", true);
        return "login";
    }

    //получение страницы регистрации
    @RequestMapping(method = RequestMethod.GET, value = "/registration")
    public String registration(){
        return "Register_page";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/registration")
    public String registration(HttpServletRequest request, ModelMap model){
        User user = new User();
        user.setMail(request.getParameter("mail"));
        user.setUsername(request.getParameter("login"));
        user.setPassword(request.getParameter("password"));
        user.setEnabled(true);
        user.setCreateDate(new java.sql.Date(new java.util.Date().getTime()));
        if(userService.checkIfMailExists(user.getMail())){
            String message = "this mail is already exist. Please fix it";
            model.addAttribute("message", message);
            return "/registration";
        }
        if(userService.checkIfUserExists(user.getUsername())){
            String message = "this login is already exist. Please fix it";
            model.addAttribute("message", message);
            return "/registration";
        }

        userService.setUserRole(user);
        User usr = userService.saveUser(user);

        return "login";
    }

    //Start Page
    @RequestMapping(method = RequestMethod.GET, value = "/main")
    public String mainPage(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){
            return "login";
        }
        return "";
    }
}
