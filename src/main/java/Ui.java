import java.util.Scanner;

public class Ui {
  public void showWelcome() {
    System.out.println("Woof! I'm Cheese, your puppy assistant.\n"
        + "What can I do for you?");
  }

  public String readCommand() {
    showLine();
    System.out.print("~ ");
    Scanner scanner = new Scanner(System.in);
    String fullCommand = scanner.nextLine();
    scanner.close();
    showLine();
    return fullCommand;
  }

  public void showAddTask(Task addedTask, int newListSize) {
    System.out.println("Gotcha! I have a paw-fect memory!");
    System.out.println("  " + addedTask);
    System.out.println("You have " + newListSize + " task(s) in the list.");
  }

  public void showDeleteTask(Task deletedTask, int remainingListSize) {
    System.out.println("Gotcha! I'll forget about this task!");
    System.out.println("  " + deletedTask);
    System.out.println("You have " + remainingListSize + " task(s) remaining.");
  }

  public void showMarkTaskAsDone(Task taskDone) {
    System.out.println("Paw-some! Another task done!");
    System.out.println("  " + taskDone);
  }

  public void showMarkTaskAsNotDone(Task taskNotDone) {
    System.out.println("Okay, I've marked this task as not done yet.");
    System.out.println("  " + taskNotDone);
  }

  public void showTaskList(TaskList taskList) {
    System.out.println(taskList);
  }

  public void showGoodbye() {
    System.out.println("Going so soon? :') Bye");
  }

  public void showError(String errorMessage) {
    System.out.println(errorMessage);
  }

  public void showLine() {
    System.out.println("-----");
  }
}
