package com.budget.web.controller;

import com.budget.dao.entities.Record;
import com.budget.dao.entities.User;
import com.budget.services.ICategoryService;
import com.budget.services.IRecordService;
import com.budget.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by home on 03.01.17.
 */
@Controller
@RequestMapping(value = "/categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final IRecordService recordService;
    private final IUserService userService;

    @Autowired
    public CategoryController(ICategoryService categoryService, IRecordService recordService, IUserService userService) {
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/*")
    public String categoriesPage(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){return "login";}
        model.addAttribute("categories", categoryService.getAllCategories());
        return "Kategory_page";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRecords/{category}")
    public String getRecordsByCategory(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model, @PathVariable int category){
        if(user == null){return "login";}
        model.addAttribute("categories", categoryService.getAllCategories());
        List<Record> records = new ArrayList<>();
        for(Record record : recordService.getRecordsByUserId(user.getId())){
            if(record.getCategory().getId() == category){
                records.add(record);
            }
        }
        model.addAttribute("records", records);
        model.addAttribute("message", " по категории " + categoryService.getCategoryByid(category).getType());
        return "Kategory_page";
    }
}
