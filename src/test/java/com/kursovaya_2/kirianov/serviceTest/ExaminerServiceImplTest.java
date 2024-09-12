package com.kursovaya_2.kirianov.serviceTest;

import com.kursovaya_2.kirianov.exceptions.ListOfQuestionsHasBeenExceeded;
import com.kursovaya_2.kirianov.question.Question;
import com.kursovaya_2.kirianov.service.ExaminerServiceImpl;
import com.kursovaya_2.kirianov.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Инициализирует моки и внедряет их в тестируемый объект
    }

    @Test
    void testGetQuestionSuccess() {
        // Данные для теста
        Question question1 = new Question("Вопрос 1", "Ответ 1");
        Question question2 = new Question("Вопрос 2", "Ответ 2");
        Question question3 = new Question("Вопрос 3", "Ответ 3");

        List<Question> allQuestions = Arrays.asList(question1, question2, question3);

        // Мокаем поведение сервиса
        when(questionService.getAll()).thenReturn(allQuestions);  // Возвращаем все вопросы
        when(questionService.getRandomQuestion()).thenReturn(question1, question2, question3);  // Последовательно возвращаем разные вопросы

        // Вызываем метод и проверяем результат
        Collection<Question> result = examinerService.getQuestion(3);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(question1));
        assertTrue(result.contains(question2));
        assertTrue(result.contains(question3));

        // Проверяем, что метод был вызван нужное количество раз
        verify(questionService, times(3)).getRandomQuestion();
    }

    @Test
    void testGetQuestionThrowsExceptionWhenAmountExceeds() {
        // Данные для теста
        Question question1 = new Question("Вопрос 1", "Ответ 1");

        // Мокаем, что у нас всего один вопрос
        when(questionService.getAll()).thenReturn(Collections.singletonList(question1));

        // Проверяем, что выбрасывается исключение, когда запрашивается больше вопросов, чем доступно
        Exception exception = assertThrows(ListOfQuestionsHasBeenExceeded.class, () -> {
            examinerService.getQuestion(2);  // Запрашиваем больше вопросов, чем доступно
        });

        assertEquals("Ожидается поступления вопросов больше, чем есть в списке", exception.getMessage());
    }

    @Test
    void testGetQuestionHandlesDuplicates() {
        // Данные для теста
        Question question1 = new Question("Вопрос 1", "Ответ 1");
        Question question2 = new Question("Вопрос 2", "Ответ 2");

        List<Question> allQuestions = Arrays.asList(question1, question2);

        // Мокаем поведение сервиса
        when(questionService.getAll()).thenReturn(allQuestions);
        when(questionService.getRandomQuestion()).thenReturn(question1, question1, question2);  // Возвращаем дубликаты

        // Вызываем метод и проверяем результат
        Collection<Question> result = examinerService.getQuestion(2);

        assertNotNull(result);
        assertEquals(2, result.size());  // Проверяем, что получили 2 уникальных вопроса
        assertTrue(result.contains(question1));
        assertTrue(result.contains(question2));

        // Проверяем, что метод был вызван 2 раза
        verify(questionService, atLeast(2)).getRandomQuestion();
    }
}
