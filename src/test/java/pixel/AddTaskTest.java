//package pixel;
//
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.Scanner;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class AddTaskTest {
//
//    @Test
//    public void testAddingTasks() throws FileNotFoundException {
//        String inputFileAddress = "C:/!Education/CS2103/gitFolderOne/data/testInput.txt";
//        Pixel pixelBot = new Pixel("C:/!Education/CS2103/gitFolderOne/data/pixel.txt"); // output file address
//        File text = new File(inputFileAddress);
//        Scanner inputScanner = new Scanner(text);
//
//        //Reading each line of the file using Scanner class
//        int lineNumber = 1;
//        while (inputScanner.hasNextLine()){
//            String line = inputScanner.nextLine();
//            System.out.println("line " + lineNumber + " :" + line);
//            lineNumber++;
//        }
//
//        // test case 1
//        pixelBot.setEmployees(new String[]{"E001", "E002"});
//        assertEquals(6400, p.totalSalary());
//
//        // test case 2
//        p.setEmployees(new String[]{"E001"});
//        assertEquals(2300, p.totalSalary());
//
//        // more tests...
//    }
//
//    @Test
//    public void dummyTest() {
//        assertEquals(2, 2);
//    }
//
//    @Test
//    public void anotherDummyTest(){
//        assertEquals(4, 4);
//    }
//}
//
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
