package com.kursovaya_2.kirianov.service;

import com.kursovaya_2.kirianov.question.Question;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {


    Set<Question> questions = new HashSet<>();


    @PostConstruct
    public void init() {
        questionAdd("Что такое JVM (Java Virtual Machine)?",
                "JVM — это виртуальная машина, которая позволяет запускать Java-программы, " +
                        "обеспечивая платформонезависимость. Она компилирует байт-код в машинный код " +
                        "для выполнения на конкретной платформе.");

        questionAdd("В чем разница между JDK и JRE?",
                "JDK (Java Development Kit) — это набор инструментов для разработки Java-программ," +
                        " включая компилятор и библиотеки. JRE (Java Runtime Environment) — это среда выполнения," +
                        " необходимая для запуска Java-программ, но не включает инструменты для разработки.");

        questionAdd("Что такое классы и объекты в Java?",
                "Класс — это шаблон для создания объектов, который определяет свойства и методы. " +
                        "Объект — это экземпляр класса, который имеет состояние и поведение, описанные в классе.");

        questionAdd("Что такое наследование в Java?",
                " Наследование — это механизм, позволяющий одному классу (подклассу) " +
                        "наследовать поля и методы другого класса (суперкласса), " +
                        "что способствует повторному использованию кода и созданию иерархий классов.");

        questionAdd("Что такое интерфейсы в Java?",
                "Интерфейсы — это абстрактные типы, которые определяют набор методов," +
                        " которые класс должен реализовать. Они позволяют создавать контракты для классов " +
                        "и поддерживают множественное наследование.");

        questionAdd("Что такое исключения в Java?",
                "Исключения — это события, которые возникают во время выполнения программы и нарушают " +
                        "ее нормальное выполнение. Java предоставляет механизм обработки " +
                        "исключений с помощью блоков try, catch и finally.");

        questionAdd(" Что такое коллекции в Java?",
                "Коллекции — это структуры данных, которые позволяют хранить, " +
                        "обрабатывать и управлять группами объектов." +
                        " В Java есть различные интерфейсы и классы для работы с коллекциями, " +
                        "такие как List, Set, Map и т.д.");

        questionAdd(" Как работает сборка мусора (Garbage Collection) в Java?",
                "Сборка мусора — это автоматический процесс освобождения памяти от объектов," +
                        " которые больше не используются. JVM отслеживает объекты и автоматически удаляет те," +
                        " на которые нет ссылок, чтобы предотвратить утечки памяти.");

        questionAdd("Что такое полиморфизм в Java?",
                "Полиморфизм — это способность методов выполнять разные действия в зависимости от объекта, " +
                        "на котором они вызываются." +
                        " Он позволяет использовать один интерфейс для разных типов объектов.");

        questionAdd("Что такое аннотации в Java?",
                "Аннотации — это специальные метаданные, которые можно добавлять к классам, методам и переменным." +
                        " Они не влияют на выполнение программы, " +
                        "но могут использоваться инструментами компиляции" +
                        " и фреймворками для предоставления дополнительной информации или конфигурации.");
    }

    @Override
    public Question questionAdd(String question, String answer) {
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question questionAdd(Question question) {
        questions.add(question);
        return question;
    }

    @Override
    public Question questionRemove(Question question) {
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions.stream().toList();
    }

    @Override
    public Question getRandomQuestion() {

        List<Question> questionList = new ArrayList<>(questions);

        Random random = new Random();

        int numberQuestion = random.nextInt(questionList.size());

        return questionList.get(numberQuestion);

//        return questions.toArray(new Question[questions.size()])[numberQuestion];
    }
}
