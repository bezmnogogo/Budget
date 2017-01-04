package com.budget.web.controller;

import com.budget.dao.entities.*;
import com.budget.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by home on 03.01.17.
 */
@Controller
@RequestMapping(value = "/records")
public class RecordsController {

    private final IUserService userService;
    private final ICategoryService categoryService;
    private final IRecordService recordService;
    private final ICardService cardService;
    private final IPlannedRecordService plannedRecordService;

    @Autowired
    public RecordsController(IUserService userService, ICategoryService categoryService, IRecordService recordService, ICardService cardService, IPlannedRecordService plannedRecordService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.cardService = cardService;
        this.plannedRecordService = plannedRecordService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addPaidRecord")
    public String addPaidRecord(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){
            return "login";
        }
        List<Category> categories = categoryService.getAllCategories();
        List<Card> cards = cardService.getCardsByUserId(user.getId());
        model.addAttribute("categories", categories);
        model.addAttribute("cards", cards);
        return "Add_Record";
    }


    //добавляем расход
    @RequestMapping(method = RequestMethod.POST, value = "/addPaidRecord")
    public String addPaidRecord(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model){
        Record record = new Record();
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        try {
            date = new Date(format.parse(request.getParameter("recordDate")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Category category = categoryService.getCategoryByType(request.getParameter("selectedCategory"));

        if(request.getParameter("card") != null){
            record.setCard(cardService.getCardByCardNumber(request.getParameter("card")));
        }

        record.setUser(user);
        record.setCategory(category);
        record.setNote(request.getParameter("text"));
        record.setRecordDate(date);
        record.setSum(Float.valueOf(request.getParameter("sum")));
        //category.setRecords(records);
        //category.addRecord(record);
        //categoryService.saveCategory(category);
        recordService.addRecord(record);
        user.addRecord(record);
        category.addRecord(record);
        return addPaidRecord(user, model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "addPlannedRecord")
    public String addPlannedRecord(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){
            return "login";
        }
        List<Category> categories = categoryService.getAllCategories();
        List<Card> cards = cardService.getCardsByUserId(user.getId());
        model.addAttribute("categories", categories);
        model.addAttribute("cards", cards);
        return "Add_Planned_R";
    }

    @RequestMapping(method = RequestMethod.POST, value = "addPlannedRecord")
    public String addPlannedRecord(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model){
        if(user == null){return "login";}

        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
        try {
            date = new Date(format.parse(request.getParameter("recordDate")).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        PlannedRecord plannedRecord = new PlannedRecord();
        plannedRecord.setSum(Float.valueOf(request.getParameter("sum")));
        plannedRecord.setUser(user);
        plannedRecord.setCategory(categoryService.getCategoryByType(request.getParameter("selectedCategory")));
        plannedRecord.setDayPosition(Integer.valueOf(request.getParameter("period")));
        plannedRecord.setStartDate(date);
        if(request.getParameter("card") != null){
            plannedRecord.setCard(cardService.getCardByCardNumber(request.getParameter("card")));
        }
        plannedRecord.setNote(request.getParameter("text"));
        plannedRecordService.savePlannedRecord(plannedRecord);
        user.addPlannedRecord(plannedRecord);
        plannedRecord.getCategory().addPlannedRecords(plannedRecord);
        return addPlannedRecord(user,model);
    }

}
