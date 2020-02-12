package com.sun.my.controller;

import com.sun.mini.beans.AutoWired;
import com.sun.mini.beans.Bean;
import com.sun.mini.web.mvc.Controller;
import com.sun.mini.web.mvc.RequestMapping;
import com.sun.mini.web.mvc.RequestParam;
import com.sun.my.service.SalaryService;

/**
 * @author zcm
 */
@Controller
public class SalaryController {

    @AutoWired
    SalaryService salaryService;

    @RequestMapping(url = "/getsa.json")
    public Integer getSalary(@RequestParam("name") String name, @RequestParam(("exp")) String experience){
        return salaryService.calSalay(Integer.valueOf(experience));
    }
}
