import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
  private final Scanner in;
  private final PrintStream out;

  public Ui() {
    in = new Scanner(System.in);
    out = System.out;
  }

  public void showWelcome() {
    out.println("Woof! I'm Cheese, your puppy assistant.\n"
        + "What can I do for you?");
  }

  public String readCommand() {
    showLine();
    out.print("~ ");
    String fullCommand = in.nextLine();
    showLine();
    return fullCommand;
  }

  public void showAddTask(Task addedTask, int newListSize) {
    out.println("Gotcha! I have a paw-fect memory!");
    out.println("  " + addedTask);
    out.println("You have " + newListSize + " task(s) in the list.");
  }

  public void showDeleteTask(Task deletedTask, int remainingListSize) {
    out.println("Gotcha! I'll forget about this task!");
    out.println("  " + deletedTask);
    out.println("You have " + remainingListSize + " task(s) remaining.");
  }

  public void showMarkTaskAsDone(Task taskDone) {
    out.println("Paw-some! Another task done!");
    out.println("  " + taskDone);
  }

  public void showMarkTaskAsNotDone(Task taskNotDone) {
    out.println("Okay, I've marked this task as not done yet.");
    out.println("  " + taskNotDone);
  }

  public void showTaskList(TaskList taskList) {
    out.println(taskList);
  }

  public void showGoodbye() {
    out.println("Going so soon? :') Bye");
  }

  public void showError(String errorMessage) {
    out.println(errorMessage);
  }

  public void showLine() {
    out.println("-----");
  }
}
