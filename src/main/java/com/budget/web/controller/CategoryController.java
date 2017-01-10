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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
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
        List<Category> categories = categoryService.getStandartCategories();
        if(user.getUsersCategories() != null){
            categories.addAll(user.getUsersCategories());
        }
        model.addAttribute("categories", categories);
        //model.addAttribute("categories", categoryService.getAllCategories());
        return "Kategory_page";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRecords/{category}")
    public String getRecordsByCategory(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model, @PathVariable int category){
        if(user == null){return "login";}
        //model.addAttribute("categories", categoryService.getAllCategories());
        List<Category> categories = categoryService.getStandartCategories();
        if(user.getUsersCategories() != null){
            categories.addAll(user.getUsersCategories());
        }
        List<Record> records = new ArrayList<>();
        for(Record record : recordService.getRecordsByUserId(user.getId())){
            if(record.getCategory().getId() == category){
                records.add(record);
            }
        }
        Collections.sort(records, Record.getCompByDate());
        model.addAttribute("categories", categories);
        model.addAttribute("records", records);
        model.addAttribute("message", " по категории " + categoryService.getCategoryByid(category).getType());
        return "Kategory_page";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/addCategory")
    public String addCategory(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){return "login";}
        return "Add_category";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addCategory")
    public String addCategory(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model){
        if(user == null){return "login";}
        //List<Category> categories = categoryService.getAllCategories();
        List<Category> categories = categoryService.getStandartCategories();
        if(user.getUsersCategories() != null){
            categories.addAll(user.getUsersCategories());
        }

        boolean exist = false;
        for (Category category : categories){
            if (category.getType().equals(request.getParameter("category"))){
                exist = true;
            }
        }
        if(exist){
            model.addAttribute("message", "Такая категория уже существует. Введите другую");
            return "Add_category";
        }else {
            Category category = new Category();
            category.setType(request.getParameter("category"));
            category.setUser(user);
            user.addUserCategory(category);
            categoryService.saveCategory(category);
            model.addAttribute("addedMessage", " Категория добавлена!");
            return "Add_category";
        }
    }
}
