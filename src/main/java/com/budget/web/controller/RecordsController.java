package com.budget.web.controller;

import com.budget.dao.entities.*;
import com.budget.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    enum Mounth{Январь,Февраль, Март, Апрель, Май, Июнь, Июль, Август, Сентябрь, Октябрь, Ноябрь, Декабрь}

    @Autowired
    public RecordsController(IUserService userService, ICategoryService categoryService, IRecordService recordService, ICardService cardService, IPlannedRecordService plannedRecordService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.recordService = recordService;
        this.cardService = cardService;
        this.plannedRecordService = plannedRecordService;
    }

    //получение страницы добавления расхода
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

        if(request.getParameter("selectedCard") != null){
            record.setCard(cardService.getCardByCardNumber(request.getParameter("selectedCard")));
            //record.setCard(user.getCardByNumber(request.getParameter("selectedCard")));
        }

        record.setUser(user);
        record.setCategory(category);
        record.setNote(request.getParameter("text"));
        record.setRecordDate(date);
        record.setSum(Float.valueOf(request.getParameter("sum")));
        //category.setRecords(records);
        //category.addRecord(record);
        //categoryService.saveCategory(category);
        user.addRecord(record);
        category.addRecord(record);

        if(record.getCard() != null) {                 //update card
            record.getCard().addRecord(record);
            //cardService.saveCard(record.getCard());
        }
        recordService.addRecord(record);

        return addPaidRecord(user, model);
    }

    //получение страницы добавления запланированного расхода
    @RequestMapping(method = RequestMethod.GET, value = "/addPlannedRecord")
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

    //добавляем запланированный расход
    @RequestMapping(method = RequestMethod.POST, value = "/addPlannedRecord")
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
        if(request.getParameter("selectedCard") != null){
            plannedRecord.setCard(cardService.getCardByCardNumber(request.getParameter("selectedCard")));
            //plannedRecord.setCard(user.getCardByNumber(request.getParameter("selectedCard")));
        }
        plannedRecord.setNote(request.getParameter("text"));
        plannedRecordService.savePlannedRecord(plannedRecord);
        user.addPlannedRecord(plannedRecord);
        plannedRecord.getCategory().addPlannedRecords(plannedRecord);

        if(plannedRecord.getCard() != null) {                        //update card
            plannedRecord.getCard().addPlannedRecord(plannedRecord);
            //cardService.saveCard(record.getCard());
        }

        return addPlannedRecord(user,model);
    }

    //получаем расходы по месяцу
    @RequestMapping(method = RequestMethod.GET, value = "/mounthlyRecords")
    public String getMounthlyRecords(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){return "login";}
        int intMounth = Calendar.getInstance().getTime().getMonth();
        String mounth = "";

        List<Record> records = user.getRecordsByMounth(intMounth);
        List<Record> plannedRecords = user.getPlannedRecordsByMounth(intMounth);
        records.addAll(plannedRecords);
        Collections.sort(records, Record.getCompByDate());

        for(Mounth m : Mounth.values()){
            if(intMounth == (m.ordinal())){
                mounth = m.name();
            }
        }
        int prevMounth = (intMounth == 0) ? (11) : (intMounth-1);
        int nextMounth = (intMounth == 11) ? (0) : (intMounth+1);
        model.addAttribute("prevMounth", prevMounth);
        model.addAttribute("nextMounth", nextMounth);
        model.addAttribute("mounth", mounth);
        model.addAttribute("records", records);

        return "Slider_bet_page";
    }

    //получаем расход по месяцу, когда тапаем по кнопке сл месяца
    @RequestMapping(method = RequestMethod.GET, value = "/getRecordsByMounth/{mounth}")
    public String getRecordsByMounth(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model, @PathVariable int mounth){
        if(user == null){return "login";}

        int intMounth = mounth;
        String mounthStr = "";

        List<Record> records = user.getRecordsByMounth(intMounth);
        List<Record> plannedRecords = user.getPlannedRecordsByMounth(intMounth);
        records.addAll(plannedRecords);
        Collections.sort(records, Record.getCompByDate());

        for(Mounth m : Mounth.values()){
            if(intMounth == (m.ordinal())){
                mounthStr = m.name();
            }
        }
        int prevMounth = (intMounth == 0) ? (11) : (intMounth-1);
        int nextMounth = (intMounth == 11) ? (0) : (intMounth+1);
        model.addAttribute("prevMouth", prevMounth);
        model.addAttribute("nextMounth", nextMounth);
        model.addAttribute("mounth", mounthStr);
        model.addAttribute("records", records);

        return "Slider_bet_page";
    }

    //получаем расход и переходим на страницу его изменения
    @RequestMapping(method = RequestMethod.GET, value = "/getRecord/{isPlanned}/{id}")
    public String getRecord(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model, @PathVariable int isPlanned, @PathVariable long id){
        if(user == null){return "login";}
        PlannedRecord plannedRecord = null;
        Record record = null;
        List<Category> categories = categoryService.getAllCategories();
        if(isPlanned == 1){
            plannedRecord = plannedRecordService.getPlannedRecordById(id);
            model.addAttribute("plannedRecord", plannedRecord);
            model.addAttribute("categories", categories);
            model.addAttribute("cards", user.getCards());
            return "SeePlaned_pay_page";
        }
        else{
            record = recordService.getRecordById(id);
            model.addAttribute("record", record);
            model.addAttribute("categories", categories);
            model.addAttribute("cards", user.getCards());
            return "See_pay_page";
        }
    }

    //изменяем расход или удаляем его
    @RequestMapping(method = RequestMethod.POST, value = "/changeRecord")
    public String changeRecord(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model){
        if(user == null){return "login";}
        String delete = request.getParameter("clear_b");
        String change = request.getParameter("repair_b");
        long id = Long.parseLong(request.getParameter("id"));

        if(delete != null){
            if(user.getRecordById(id) != null)
                user.getRecordById(id).getCategory().deleteRecordById(id);  //clear category
            user.deleteRecordById(id); //clear user
            recordService.deleteRecordById(id);  //delete record
        }
        if(change != null){

            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date date = null;
            try {
                date = new Date(format.parse(request.getParameter("recordDate")).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String str = request.getParameter("selectedCard");

            Record record = new Record();
            record.setUser(user);
            record.setId(id);
            record.setCategory(categoryService.getCategoryByType(request.getParameter("selectedCategory")));
            record.setRecordDate(date);
            record.setNote(request.getParameter("text"));
            record.setSum(Float.valueOf(request.getParameter("sum")));
            if(request.getParameter("selectedCard") != null){
                record.setCard(cardService.getCardByCardNumber(request.getParameter("selectedCard")));
                //record.setCard(user.getCardByNumber(request.getParameter("selectedCard")));
            }

            if(record.getCard() != null){               //update card
                record.getCard().updateRecord(record);
            }

            record.getCategory().updateRecord(record);
            user.updateRecord(record);
            recordService.addRecord(record);
            return getRecord(user,request,model,0,id);
        }
        return addPaidRecord(user, model); //
    }

    //изменяем или удаляем запланированный расход
    @RequestMapping(method = RequestMethod.POST, value = "/changePlannedRecord")
    public String changePlannedRecord(@AuthenticationPrincipal User user, HttpServletRequest request, ModelMap model){
        if(user == null){return "login";}
        String delete = request.getParameter("clear_b");
        String change = request.getParameter("repair_b");
        long id = Long.parseLong(request.getParameter("id"));

        if(delete != null){
            if(user.getPlannedRecordById(id) != null)
                user.getPlannedRecordById(id).getCategory().deletePlannedRecordById(id);  //clear category
            user.deletePlannedRecordById(id); //clear user
            plannedRecordService.deletePlannedRecordById(id);  //delete record
        }
        if(change != null){
            DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date date = null;
            try {
                date = new Date(format.parse(request.getParameter("recordDate")).getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            PlannedRecord plannedRecord = new PlannedRecord();
            plannedRecord.setId(id);
            plannedRecord.setSum(Float.valueOf(request.getParameter("sum")));
            plannedRecord.setUser(user);
            plannedRecord.setCategory(categoryService.getCategoryByType(request.getParameter("selectedCategory")));
            plannedRecord.setDayPosition(Integer.valueOf(request.getParameter("period")));
            plannedRecord.setStartDate(date);
            if(request.getParameter("selectedCard") != null){
                plannedRecord.setCard(cardService.getCardByCardNumber(request.getParameter("selectedCard")));
                //plannedRecord.setCard(user.getCardByNumber(request.getParameter("selectedCard")));
            }
            plannedRecord.setNote(request.getParameter("text"));

            plannedRecord.getCategory().updatePlannedRecord(plannedRecord);
            user.updatePlannedRecord(plannedRecord);

            if(plannedRecord.getCard() != null){                                //update card
                plannedRecord.getCard().updatePlannedRecord(plannedRecord);
            }

            plannedRecordService.savePlannedRecord(plannedRecord);
            return getRecord(user,request,model,1,id);
        }

        return addPlannedRecord(user, model); //
    }

    //обзор расходов
    @RequestMapping(method = RequestMethod.GET, value = "/Overview")
    public String overviewRecords(@AuthenticationPrincipal User user, ModelMap model){
        if(user == null){return "login";}
        int intMounth = Calendar.getInstance().getTime().getMonth();
        List<Record> records = user.getRecordsByMounth(intMounth);
        List<Record> plannedRecords = user.getPlannedRecordsByMounth(intMounth);
        List<Record> allRecords = new ArrayList<>();
        allRecords.addAll(records);
        allRecords.addAll(plannedRecords);
        plannedRecords.clear();
        records.clear();
        for (Record record : allRecords){
            if(record.getRecordDate().compareTo(Calendar.getInstance().getTime()) > 0){
                plannedRecords.add(record);
            }
            else{
                records.add(record);
            }
        }
        Collections.sort(records, Record.getCompByDate());
        Collections.sort(plannedRecords, Record.getCompByDate());
        float currentPay = calculatePayments(records);
        float futurePay = calculatePayments(plannedRecords);
        float limit = user.getMounthlyLimit();
        float balance = limit - currentPay;
        if(balance < 0){
            model.addAttribute("message", "Вы превысили лимит!");
        }

        model.addAttribute("currentPay", currentPay);
        model.addAttribute("futurePay", futurePay);
        model.addAttribute("balance", balance);
        model.addAttribute("limit", limit);

        model.addAttribute("records", records);
        model.addAttribute("futureRecords", plannedRecords);

        return "Overview_Res_page";
    }

    float calculatePayments(List<Record> records){
        float summ = 0;
        for (Record record : records){
            summ += record.getSum();
        }
        return summ;
    }

}
