package com.kursovaya_2.kirianov.service;

import com.kursovaya_2.kirianov.question.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestion(int amount);

}
