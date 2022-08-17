import java.util.Scanner;
import java.util.ArrayList;

import tasks.*;
import commands.*;

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

  /*
   * Parses a String[] for an "event", "deadline" or "todo" task.
   */
  private static String[] parseString(String action, String[] splitInput) {
    String[] newSplitInput;

    if (action.equals("todo")) {
      newSplitInput = new String[2];
      String taskDetails = "";

      for (int i = 1; i < splitInput.length; i++) {
        taskDetails += splitInput[i];
      }

      newSplitInput[1] = taskDetails;

    } else {
      String dateDelimiter = "";
      if (action.equals("event")) {
        dateDelimiter = "/at";
      } else if (action.equals("deadline")) {
        dateDelimiter = "/by";
      }

      newSplitInput = new String[3];

      // parse the splitInput String[]
      newSplitInput[0] = splitInput[0];

      String taskDetails = "";
      String date = "";

      boolean reachedDate = false;
      for (int i = 1; i < splitInput.length; i++) {
        String substring = splitInput[i];
        if (substring.equals(dateDelimiter)) {
          reachedDate = true;
          continue;
        }

        if (!reachedDate) {
          taskDetails += splitInput[i];
        } else {
          date += splitInput[i];
        }
      }

      newSplitInput[1] = taskDetails;
      newSplitInput[2] = date;
    }
    return newSplitInput;
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
    String[] splitInput = input.split(" ");
    String action = splitInput[0];
    if (action.equals("Bye")) {
      Command command = new ByeCommand(splitInput, tasks);
      return command.performAction();

    } else if (action.equals("list")) {
      Command command = new ListCommand(splitInput, tasks);
      return command.performAction();

    } else if (
        action.equals("mark") ||
        action.equals("unmark")
        )
    {
      Command command = new MarkCommand(splitInput, tasks);
      return command.performAction();

    } else if (
        action.equals("event") ||
        action.equals("deadline") ||
        action.equals("todo")
        )
    {
      String[] newSplitInput = parseString(action, splitInput);
      Command command = new TaskCommand(newSplitInput, tasks);
      return command.performAction();
    }
    return true;
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

    System.exit(0);
  }
}
