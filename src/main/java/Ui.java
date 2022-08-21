import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public void greet() {
        System.out.print("Eh hello, my name is Uncle Cheong. \n" +
                "What you want?\n");
    }

    public void sayGoodbye() {
        System.out.print("Eh you leaving me so soon?\n");
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public String sayErrorMessage(String error) {
        return "Eh something went wrong " + error;
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
