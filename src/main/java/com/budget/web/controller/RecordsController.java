package com.budget.web.controller;

import com.budget.dao.entities.Category;
import com.budget.dao.entities.Record;
import com.budget.dao.entities.User;
import com.budget.services.ICategoryService;
import com.budget.services.IRecordService;
import com.budget.services.IUserService;
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

    @Autowired
    public RecordsController(IUserService userService, ICategoryService categoryService, IRecordService recordService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.recordService = recordService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addPaidRecord")
    public String addPaidRecord(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){
            return "login";
        }
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
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

        record.setUser(user);
        record.setCategory(category);
        record.setNote(request.getParameter("text"));
        record.setRecordDate(date);
        record.setSum(Float.valueOf(request.getParameter("sum")));
        category.addRecord(record);
        categoryService.saveCategory(category);
        //recordService.addRecord(record);
        return "";
    }

}
