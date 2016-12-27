package com.budget.web.controller;

import com.budget.dao.entities.Category;
import com.budget.dao.entities.User;
import com.budget.services.ICategoryService;
import com.budget.services.IPlaceService;
import com.budget.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

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
        Category category = categoryService.getCategoryByid(1);
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
        return "registration";
    }


}
