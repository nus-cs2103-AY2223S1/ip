import java.util.Scanner;
import java.util.ArrayList;

/*
 * TODO: add exceptions
 * - when input for mark or unmark does not contian a proper index 
 *   - not int
 *   - array does not contain that task in that index
 *   - index <= 0
 *
 * TODO: abstract out status updates from settleInput
 */
public class Duke {
  /* String ArrayList to store text entered by the user.
   * Won't go past 100 inputs.
   */
  private static ArrayList<Task> tasks = new ArrayList<Task>();

  private static void actionAfterInput(String userInput) {
    Task newTask = new Task(userInput);
    tasks.add(newTask);
    System.out.println("Hey sweetie, I've added: '" + userInput + "' to your lists of tasks~\n");
  }


  private static void goodbye() {
    System.out.println("MumBot: Goodbyeeee sweetheart <3");
    System.exit(0);
  }

  private static void list() {
    for (int i = 0; i < tasks.size(); i++) {
      System.out.println((i + 1) + ". " + tasks.get(i));
    }
    System.out.println("");
  }

  /*
   * Performs actions based on the input.
   *
   * @param input The input given by the user.
   *
   * @return Returns true if the programme should continue prompting the user
   * for inputs. Returns false if the programme is to be terminated.
   */
  private static boolean settleInput(String input) {
    if (input.equals("Bye")) {
      goodbye();
      return false;

    } else if (input.equals("list")) {
      list();
      return true;

    } else if (
        input.length() >= 6 &&
        input.substring(0, 4).equals("mark")
        )
    {
      int index = input.charAt(5) - '0';
      Task taskToUpdate = tasks.get(index - 1);
      System.out.println(taskToUpdate.updateStatus(true) + "\n");
      return true;

    } else if (
        input.length() >= 8 &&
        input.substring(0, 6).equals("unmark")
        )
    {
      int index = input.charAt(7) - '0';
      Task taskToUpdate = tasks.get(index - 1);
      System.out.println(taskToUpdate.updateStatus(false) + "\n");
      return true;

    } else {
      actionAfterInput(input);
      return true;
    }
  }

  public static void main(String[] args) {
    // Welcome message
    System.out.println("MumBot: Hi dear! You are precious <3\n");

    /*
     * Take in user input.
     * All inputs are considered as Strings.
     */
    System.out.print("You: ");
    Scanner sc = new Scanner(System.in);

    String input = sc.nextLine();
    while (settleInput(input)) {
      System.out.print("You: ");
      input = sc.nextLine();
    }
  }
}
