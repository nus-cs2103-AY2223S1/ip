import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public void welcome() {
        sc = new Scanner(System.in);
        System.out.println("_____________   _       _______________\n|___   __||  | | |     | |__  __| ____|"
                + "\n    | |  | __ ||  \\   /  |  | | | |___\n _  | | | |__| |   \\_/   |  | | |  ___|"
                + "\n| |_| | |  __  | |\\   /| |__| |_| |___\n|_____| |_|  |_|_| \\_/ |_|______|_____|");
        System.out.println("Hi, I'm Jamie.\nWhat do you want to do?\n"
                + "For dates and time, please enter in the format:\n"
                + "dd/MM/yyyy HHmm eg. 29/10/2022 0000");

        for (String input = sc.nextLine(); !input.equals("bye"); input = sc.nextLine()) {
            if (input.equals("list")) {
                TaskList.printTaskList();
            } else if (input.startsWith("mark ")) {
                try {
                    int number = Integer.parseInt(input.substring(5));
                    TaskList.mark(number);
                } catch (Exception e) {
                    System.out.println("Please enter numbers after mark");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int number = Integer.parseInt(input.substring(7));
                    TaskList.unMark(number);
                } catch (Exception e) {
                    System.out.println("Please enter numbers after unmark");
                }
            } else if (input.startsWith("todo ")) {
                try {
                    input = input.substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("The description of todo cannot be empty >:(");
                    continue;
                }
                TaskList.addTask(new ToDo(input));
            } else if (input.startsWith("deadline ")) {
                int end = input.indexOf("/by ");
                String textInput;
                String byInput;
                try {
                    textInput = input.substring(9, end);
                    byInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Something is missing in the description of this deadline >:(");
                    continue;
                }
                TaskList.addTask(new Deadline(textInput, byInput));
            } else if (input.startsWith("event ")) {
                int end = input.indexOf("/at ");
                String textInput;
                String atInput;
                try {
                    textInput = input.substring(6, end);
                    atInput = input.substring(end + 4);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Something is missing in the description of this event >:(");
                    continue;
                }
                TaskList.addTask(new Event(textInput, atInput));
            } else if (input.startsWith("delete ")) {
                try {
                    int number = Integer.parseInt(input.substring(7));
                    TaskList.deleteTask(number);
                } catch (Exception e) {
                    System.out.println("Please enter numbers after delete");
                }
            } else {
                System.out.println("Please enter a valid command");
            }

        }

        try {
            FileWriter writer = new FileWriter("Data/Jamie.txt", false);
            writer.write(TaskList.taskListToText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("bye :(");
    }
}
