package technical;
/**
 * Parser class to process user inputs.
 * @author Nicholas Patrick
 */

import java.io.IOException;

public class Parser {
  /**
   * Splits a command line into arguments.
   *
   * @param line The command line String.
   * @return A String array of arguments.
   */
  public static String[] tokenise(String line) {
    return line.split(" ");
  }

  /**
   * Lists the list of commands.
   */
  public static void mismatch() {
    Ui.reply("list of commands: list, mark, unmark, todo, deadline, event, delete");
  }

  /**
   * Parses and executes a command.
   *
   * @param line The line of the command
   * @return Whether the program should continue running
   * @throws IOException in case of failure to write into save file.
   */
  public static boolean parseExecute(String line) throws IOException {
    String[] arguments = Parser.tokenise(line);
    if (arguments[0].equals("bye")) {
      Ui.bye();
      return false;
    }
    if (arguments[0].equals("list")){
      TaskList.list();
      return true;
    }
    if (arguments[0].equals("mark")) {
      TaskList.mark(arguments);
      return true;
    }
    if (arguments[0].equals("unmark")) {
      TaskList.unmark(arguments);
      return true;
    }
    if (arguments[0].equals("todo")) {
      TaskList.todo(arguments);
      return true;
    }
    if (arguments[0].equals("deadline")) {
      TaskList.deadline(arguments);
      return true;
    }
    if (arguments[0].equals("event")) {
      TaskList.event(arguments);
      return true;
    }
    if (arguments[0].equals("delete")) {
      TaskList.delete(arguments);
      return true;
    }
    if (arguments[0].equals("find")) {
      TaskList.find(arguments);
      return true;
    }
    mismatch();
    return true;
  }
}
