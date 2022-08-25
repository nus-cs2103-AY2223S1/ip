import java.util.Scanner;

public class Ui {

    public void Greet() {
        System.out.println("Hello, this is Siri! It is a pleasure to meet you!");
        System.out.println("How may I assist you?");
        printBorder();
    }

    public void GoodBye() {
        printBorder();
        System.out.println("So Long, farewell!");
        printBorder();
    }

    public String readCommand(Scanner userInput) {
        return userInput.nextLine().strip();
    }

    public void printBorder() {
        System.out.println("##############################################");
    }

    public void displayCommandMessage(String message, Task task, Integer size) {
        if (message != null) {
            System.out.println(message);
        }
        if (task != null) {
            System.out.println("\t\t\t" + task);
        }
        if (size != null) {
            String numOfTasks = String.format("You currently have %d tasks in the list", size);
            System.out.println(numOfTasks);
        }
    }

    public void showExceptionMessage(String message) {
        System.out.println(message);
    }
}
