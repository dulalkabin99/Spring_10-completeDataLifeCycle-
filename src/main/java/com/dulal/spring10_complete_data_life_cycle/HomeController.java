package com.dulal.spring10_complete_data_life_cycle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    CourseRepo courseRepo;

    @RequestMapping("/")
    public String listCourses(Model model){
        model.addAttribute("courses",courseRepo.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String courseForm(Model model){
        model.addAttribute("course", new Course());
        return "courseform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Course course, BindingResult result)
    {
        if (result.hasErrors()){
            return "courseform";
        }
        courseRepo.save(course);
        return "redirect:/";

    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepo.findById(id).get());
        return "show";

    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepo.findById(id));
        return "courseform";
    }


    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        courseRepo.deleteById(id);
        return "redirect:/";
    }
}
