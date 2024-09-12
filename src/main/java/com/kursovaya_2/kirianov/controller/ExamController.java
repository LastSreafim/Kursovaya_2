package com.kursovaya_2.kirianov.controller;

import com.kursovaya_2.kirianov.question.Question;
import com.kursovaya_2.kirianov.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExamController {
    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @Autowired
    private ExaminerService examinerService;

    @GetMapping(path = "/randomQuestions")
    public Collection<Question> getRandomQuestions(@RequestParam("amount") int amount_) {
        return examinerService.getQuestion(amount_);
    }
}
