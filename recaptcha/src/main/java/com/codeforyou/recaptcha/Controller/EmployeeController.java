package com.codeforyou.recaptcha.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codeforyou.recaptcha.Model.EmployeeModel;
import com.codeforyou.recaptcha.Repository.EmployeeRepository;
import com.codeforyou.recaptcha.Service.ReCaptchaValidationService;


import org.springframework.ui.Model;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private ReCaptchaValidationService reCaptchaValidationService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/register")
    public String showRegister(Model model) {

        model.addAttribute("employee", EmployeeModel.class);

        return "EmployeeRegister";

    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") EmployeeModel eModel,@RequestParam(name = "g-recaptcha-response") String captcha, Model model){
        if (reCaptchaValidationService.validateCaptcha(captcha)){
            employeeRepository.save(eModel);
            model.addAttribute("employee",new EmployeeModel());
            model.addAttribute("message","Employee added!!");
        }
        else{
            model.addAttribute("message","Please Verify Captcha");
            System.out.println("In else block");
        }
        return "EmployeeRegister";

        }

        @GetMapping("/all")
        public String getAllEmployees(Model model){
            model.addAttribute("list",employeeRepository.findAll());
            return "EmployeeData";
        }

}
