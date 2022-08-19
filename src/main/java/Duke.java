import java.util.Scanner;

public class Duke {

  private static AllTasksList allTasks = new AllTasksList();
  private static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    Command.greet();
    Command.chat(scanner, allTasks);
  }
}
