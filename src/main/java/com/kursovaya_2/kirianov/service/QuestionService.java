package com.kursovaya_2.kirianov.service;

import com.kursovaya_2.kirianov.question.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface QuestionService {

    Question questionAdd(String question, String answer);

    Question questionAdd(Question question);

    Question questionRemove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();

}
