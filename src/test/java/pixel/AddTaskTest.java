package pixel;

import org.junit.jupiter.api.Test;
import pixel.util.IncorrectFormatException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddTaskTest {

    Pixel pixelBot = new Pixel("C:/!Education/CS2103/gitFolderOne/data/pixel.txt"); // output file address

//    @Test
//    public void testAddingTasks() throws FileNotFoundException {
        //String inputFileAddress = "C:/!Education/CS2103/gitFolderOne/data/testInput.txt";
        //File text = new File(inputFileAddress);
        //Scanner inputScanner = new Scanner(text);

        //Reading each line of the file using Scanner class
//        int lineNumber = 1;
//        while (inputScanner.hasNextLine()){
//            String line = inputScanner.nextLine();
//            System.out.println("line " + lineNumber + " :" + line);
//            lineNumber++;
//        }

    @Test
    public void testInvalidInput() {
        try {
            pixelBot.parser.parse("dsfdsfdsfdsf");
        } catch (Exception exception) {
            //System.out.println(exception.toString());
            assertTrue(exception instanceof IncorrectFormatException);
            assertEquals(exception.toString(), "Input should be a task or a command!");
        }
    }

    @Test
    public void testInvalidDue() {
        try {
            pixelBot.taskList.handleNewTask("deadline /as jjjj", "D");
        } catch (Exception exception) {
            // System.out.println(exception);
            assertTrue(exception instanceof IncorrectFormatException);
            assertEquals(exception.toString(), "pixel.util.IncorrectFormatException: Slash should be followed by \"by\" or \"at\"!");
        }
    }

    @Test
    public void testDeleteTask() {
        try {
            pixelBot.taskList.handleNewTask("deadline CS2103 assignment /by tomorrow", "D");
            pixelBot.taskList.handleNewTask("todo meet Wayne for dinner /at 2022-26-08 1850", "D");
            assertEquals(2, Pixel.inputTasks.size());
            pixelBot.storage.deleteEntry("delete 2");
            assertEquals(1, Pixel.inputTasks.size());
        } catch (Exception exception) {
            // System.out.println(exception);
            assertTrue(exception instanceof IOException);
            // assertEquals("pixel.util.IncorrectFormatException: Slash should be followed by \"by\" or \"at\"!", exception.toString());
        }
    }

//        // test case 1
//        pixelBot.(new String[]{"E001", "E002"});
//        assertEquals(6400, p.totalSalary());
//
//         .handleNewTask(String userInput, int indexOfSlash, String type)
//
//        // test case 2
//        p.setEmployees(new String[]{"E001"});
//        assertEquals(2300, p.totalSalary());
//
//        // more tests...

//    @Test
//    public void dummyTest() {
//        assertEquals(2, 2);
//    }
//
//    @Test
//    public void anotherDummyTest(){
//        assertEquals(4, 4);
//    }
}

//public class PayrollTest {
//    public static void main(String[] args) throws Exception {
//
//        // test setup
//        Payroll p = new Payroll();
//
//        // test case 1
//        p.setEmployees(new String[]{"E001", "E002"});
//        // automatically verify the response
//        if (p.totalSalary() != 6400) {
//            throw new Error("case 1 failed ");
//        }
//
//        // test case 2
//        p.setEmployees(new String[]{"E001"});
//        if (p.totalSalary() != 2300) {
//            throw new Error("case 2 failed ");
//        }
//
//        // more tests...
//
//        System.out.println("All tests passed");
//    }
//
//}
//
//
