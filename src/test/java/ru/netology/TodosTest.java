package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodosTest {
    SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

    String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
    Epic epic = new Epic(55, subtasks);

    Meeting meeting = new Meeting(
            555,
            "Выкатка 3й версии приложения",
            "Приложение НетоБанка",
            "Во вторник после обеда"
    );

    Todos todos = new Todos();

    @BeforeEach
    public void init() {
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
    }

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchNoQuery() {
        Task[] expected = {};
        Task[] actual = todos.search("Мясо");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSimpleTask() {
        Task[] expected = {simpleTask};
        Task[] actual = todos.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSubtasks() {
        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchMeeting() {
        Task[] expected = {meeting};
        Task[] actual = todos.search("Выкатка");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchAll() {
        SimpleTask simpleTask1 = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks1 = {"Позвонить в офис", "Съездить на встречу"};
        Epic epic1 = new Epic(55, subtasks1);

        Meeting meeting1 = new Meeting(
                555,
                "Позвонить партнерам",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos1 = new Todos();
        todos1.add(simpleTask1);
        todos1.add(epic1);
        todos1.add(meeting1);

        Task[] expected = {simpleTask1, epic1, meeting1};
        Task[] actual = todos1.search("Позвонить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchEpicWithTwoSubtasks() {
        SimpleTask simpleTask1 = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks1 = {"Съездить в офис", "Съездить на встречу"};
        Epic epic1 = new Epic(55, subtasks1);

        Meeting meeting1 = new Meeting(
                555,
                "Позвонить партнерам",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos1 = new Todos();
        todos1.add(simpleTask1);
        todos1.add(epic1);
        todos1.add(meeting1);

        Task[] expected = {epic1};
        Task[] actual = todos1.search("Съездить");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchEpicWithTwoMeetingElements() {
        SimpleTask simpleTask1 = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks1 = {"Съездить в офис", "Съездить на встречу"};
        Epic epic1 = new Epic(55, subtasks1);

        Meeting meeting1 = new Meeting(
                555,
                "Проект НетоБанка",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos1 = new Todos();
        todos1.add(simpleTask1);
        todos1.add(epic1);
        todos1.add(meeting1);

        Task[] expected = {meeting1};
        Task[] actual = todos1.search("НетоБанка");
        Assertions.assertArrayEquals(expected, actual);
    }
}