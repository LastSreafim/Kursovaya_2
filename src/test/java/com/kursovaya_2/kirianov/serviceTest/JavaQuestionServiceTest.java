package com.kursovaya_2.kirianov.serviceTest;

import com.kursovaya_2.kirianov.question.Question;
import com.kursovaya_2.kirianov.service.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {

    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    void testQuestionAdd() {
        Question question = questionService.questionAdd("Что такое полиморфизм?", "Это свойство ООП.");
        assertNotNull(question);
        assertEquals("Что такое полиморфизм?", question.getQuestion());
        assertEquals("Это свойство ООП.", question.getAnswer());

        Collection<Question> allQuestions = questionService.getAll();
        assertTrue(allQuestions.contains(question));
    }

    @Test
    void testQuestionRemove() {
        Question question = questionService.questionAdd("Что такое наследование?", "Это свойство ООП.");
        questionService.questionRemove(question);

        Collection<Question> allQuestions = questionService.getAll();
        assertFalse(allQuestions.contains(question));
    }

    @Test
    void testGetAll() {
        Question question1 = questionService.questionAdd("Что такое классы?", "Это шаблоны для объектов.");
        Question question2 = questionService.questionAdd("Что такое объекты?", "Это экземпляры классов.");

        Collection<Question> allQuestions = questionService.getAll();

        assertEquals(2, allQuestions.size());
        assertTrue(allQuestions.contains(question1));
        assertTrue(allQuestions.contains(question2));
    }

    @Test
    void testGetRandomQuestion() {
        questionService.questionAdd("Что такое классы?", "Это шаблоны для объектов.");
        questionService.questionAdd("Что такое объекты?", "Это экземпляры классов.");

        Question randomQuestion = questionService.getRandomQuestion();
        assertNotNull(randomQuestion);  // Проверяем, что случайный вопрос был возвращен
    }
}
