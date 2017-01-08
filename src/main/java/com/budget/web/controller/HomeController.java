package com.budget.web.controller;

import com.budget.dao.entities.Card;
import com.budget.dao.entities.PlannedRecord;
import com.budget.dao.entities.Record;
import com.budget.dao.entities.User;
import com.budget.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

/**
 * Created by home on 14.11.16.
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    public HomeController(IUserService userService, IPlaceService placeService, ICategoryService categoryService, IRecordService recordService, IPlannedRecordService plannedRecordService, ICardService cardService){
        this.userService = userService;
        this.placeService = placeService;
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.plannedRecordService = plannedRecordService;
        this.cardService = cardService;
    }

    private final IUserService userService;
    private final IPlaceService placeService;
    private final ICategoryService categoryService;
    private final IRecordService recordService;
    private final IPlannedRecordService plannedRecordService;
    private final ICardService cardService;


    //получение стартовой страницы
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String startPage(@AuthenticationPrincipal User user, ModelMap modelMap, HttpServletRequest request){
        if(user != null){
            modelMap.addAttribute("user", user);
            return mainPage(user,modelMap);
        }
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
        model.put("message", "Что-то пошло не так. Проверьте логин или пароль.");
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
        //List<Record> records = recordService.getRecordsByUserId(user.getId());
        //List<PlannedRecord> plannedRecords = plannedRecordService.getPlannedRecordsByUserId(user.getId());

        Set<Record> records = user.getRecords();
        Set<PlannedRecord> plannedRecords = user.getPlannedRecords();
        Set<Card> cards = user.getCards();

        model.addAttribute("cards", cards);
        model.addAttribute("records", records);
        model.addAttribute("plannedRecords", plannedRecords);
        return "Main_page";
    }

    //add Card
    @RequestMapping(method = RequestMethod.GET, value = "/addCard")
    public String addCard(@AuthenticationPrincipal User user, ModelMap modelMap){
        if(user == null){ return "login";}
        return "Add_card";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCard")
    public String addCard(@AuthenticationPrincipal User user,  HttpServletRequest request, ModelMap model){
        if (user == null){return "login";}

        Card card = new Card();
        card.setUser(user);
        card.setCardNumber(request.getParameter("cardNumber"));
        card.setCash(0);
        user.addCard(card);
        cardService.saveCard(card);
        return mainPage(user, model);
    }
}
