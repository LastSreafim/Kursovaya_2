package com.kursovaya_2.kirianov.service;

import com.kursovaya_2.kirianov.exceptions.ListOfQuestionsHasBeenExceeded;
import com.kursovaya_2.kirianov.question.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestion(int amount) {

        Set<Question> randomQuestions = new LinkedHashSet<>();

        if (amount > questionService.getAll().size()) {
            throw new ListOfQuestionsHasBeenExceeded("Ожидается поступления вопросов больше, чем есть в списке");
        }

        while (randomQuestions.size() < amount) {
            randomQuestions.add(questionService.getRandomQuestion());
        }

        return randomQuestions;
    }
}
