package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
Contains JUnit tests
*/
public class DukeTest {

    @Test
    public void parserGetCommandTest(){
        String result = Parser.parseUserInput("todo hw");
        assertEquals("todo", result);
    }

    @Test
    public void parserGetTaskNameTest(){
        String result = Parser.getTaskName("event graduation /at 2024-03-20");
        assertEquals("graduation", result);
    }

    @Test
    /* public void taskToTxtTest(){
        Task testTask = new Task("ip", "deadline", "2022-08-25", false);
        String txtResult = testTask.toTxt();
        assertEquals("T | 0 | ip | 2022-08-25", txtResult);
    } */
    public void taskToStringTest(){
        Task testTask = new Task("ip", "deadline", "2022-08-25", true);
        String txtResult = testTask.toString();
        assertEquals("[D][X] ip 2020-08-25", txtResult);
    }
}
