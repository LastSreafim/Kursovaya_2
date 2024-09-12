package com.kursovaya_2.kirianov.controller;

import com.kursovaya_2.kirianov.question.Question;
import com.kursovaya_2.kirianov.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private QuestionService service;

    @Autowired
    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping(path = "/add")
    @ResponseBody
    public Question addQuestion(@RequestParam("question") String question_,
                                @RequestParam("answer") String answer_) {
        return service.questionAdd(question_, answer_);

    }
    @GetMapping
    public Collection<Question> getQuestion() {
        return service.getAll();
    }

    @GetMapping(path = "/remove")
    public Question removeQuestion(@RequestParam("question") String question_,
                                   @RequestParam("answer") String answer_) {
        return service.questionRemove(new Question(question_, answer_));
    }

}
