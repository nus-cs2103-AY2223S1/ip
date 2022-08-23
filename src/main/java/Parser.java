import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Parser {

    public static String[] parseTypingInput(String str) {
        String allLowerCase = str.toLowerCase();
        return allLowerCase.strip().split(" ", 2);
    }

    public static void parseInput() {

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                new TaskList(Storage.loadTask());
                //parse
                String str1 = sc.nextLine();
                String[] wordArray = Parser.parseTypingInput(str1);
                String str2 = "";

                if (wordArray.length >= 2) {
                    str2 = wordArray[1];
                }
                String firstWord = wordArray[0];

                switch (firstWord) {

                case "bye":
                    UI.end();
                    running = false;
                    break;

                case "list":
                    UI.printList();
                    break;

                case "mark":
                    TaskList.markAsDone(str2);
                    break;

                case "unmark":
                    TaskList.markAsUndone(str2);
                    break;

                case "delete":
                    TaskList.deleteTaskfromArray(str2);
                    break;

                case "deadline":
                    TaskList.addTaskToArray(str2, Task.TYPE.DEADLINE);
                    break;

                case "todo":
                    TaskList.addTaskToArray(str2, Task.TYPE.TODO);
                    break;

                case "event":
                    TaskList.addTaskToArray(str2, Task.TYPE.EVENT);
                    break;

                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

                }
                Storage.save(TaskList.getTaskList());

            } catch (InputMismatchException e) {
                UI.getLINE();
                System.out.println("Invalid input");
                UI.getLINE();

            } catch (DukeException e) {
                UI.getLINE();
                System.out.println(e);
                UI.getLINE();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
