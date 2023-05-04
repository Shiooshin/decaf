package com.pet.decaf.controller;

import com.pet.decaf.entity.ContentEntity;
import com.pet.decaf.storage.aws.S3StorageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class SimpleController {

    @Autowired
    private S3StorageHandler handler;

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("content", ContentEntity.builder().contentDate(new Date()));
        model.addAttribute("contentDate", new Date());
        return "home";
    }

    @PostMapping("/save")
    public String greetingSubmit(@ModelAttribute ContentEntity content, Model model) {
        return "result";
    }
}
