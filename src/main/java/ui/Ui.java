package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    /**
     * Prints the greeting text by the Chatbot.
     */
    public void greet() {
        System.out.print("Eh hello, my name is Uncle Cheong. \n" +
                "What you want?\n");
    }

    /**
     * Prints the concluding text by the Chatbot.
     */
    public void sayGoodbye() {
        System.out.print("Eh you leaving me so soon?\n");
    }

    /**
     * Returns the next command by the user as a String.
     *
     * @return String containing the input by the user.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints the Error message.
     *
     * @param error the error message.
     */
    public void sayErrorMessage(String error) {
        System.out.println("Eh something went wrong " + error);
    }

    public void printStoredInputs(TaskList tasks) {
        int numberOfTasks = tasks.getSize();
        if (numberOfTasks > 0) {
            System.out.println("Boss ah, this one your tasks:");
            for (int i = 0; i < numberOfTasks; i++) {
                System.out.println(i + 1 + ". " + tasks.taskStringAtIndex(i));
            }
            this.printTaskCountMessage(tasks);
        } else if (numberOfTasks == 0) {
            System.out.println("Boss, you got no task yet ah");
        }
    }

    public void printTaskCountMessage(TaskList tasks) {
        System.out.printf("Boss, you got %s tasks now\n", tasks.getSize());
    }

    public void printAddedTaskMessage(Task task) {
         System.out.printf("Swee lah! I added this task liao:\n%s\n", task);
    }
}
