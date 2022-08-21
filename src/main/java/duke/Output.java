package duke;

import duke.task.Task;

import java.time.LocalDateTime;

/**
 * Enum for the various different outputs that the Chat-bot can give.
 */
public enum Output{
  GREETINGS(" ____        _        \n\t "
          + "|  _ \\ _   _| | _____ \n\t "
          + "| | | | | | | |/ / _ \\\n\t "
          + "| |_| | |_| |   <  __/\n\t "
          + "|____/ \\__,_|_|\\_\\___|\n\n\t "
          + "Hello! I'm Duke\n\t "
          + "What can I do for you?\n"),
  GOODBYE("Bye. Hope to see you again soon!\n"),
  LIST("Here are the task(s) in your list:\n"),
  MARK("Nice! I've marked this task as done:\n"),
  UNMARK("OK, I've marked this task as not done yet:\n"),
  ADD("Got it. I've added this task:\n"),
  DELETE("Noted. I've removed this task:\n"),
  SAVE("Saving...\n"),
  LOAD("Loading...\n"),
  DATE("These tasks matches the date:\n"),
  FIND("Here are the matching tasks in your list:\n");

  private String output = "";

  /**
   * Constructor for the Enums
   * @param s String that is to be initialised in the enum
   */
  Output(String s){
    this.output = s;
  }

  /**
   * Prints the enum output with the specified format
   */
  public void print() {
    echo(this.output);
  }

  /**
   * Prints the format when a change of status has occurred
   * @param task The task where the change of status occurred
   */
  public void changeStatus(Task task) {
    echo(this.output + "\t  " + task + "\n");
  }

  /**
   * Prints the format when a new duke.task.Task is modified
   * @param task duke.task.Task to be modified
   * @param list List where the task is modified
   */
  public void modifyTask(Task task, StorageList list) {
    echo(this.output + "\t  " + task + "\n" + getNumTask(list));
  }

  /**
   * Returns the String representation of the number of tasks in the list
   * @param list List where the tasks are stored
   * @return String representation of the number of tasks in the list
   */
  private String getNumTask(StorageList list){
    return "\t Now you have " + list.getSize() + " task(s) in the list.\n";
  }

  /**
   * Prints the String format of the list
   * @param list List to be printed
   */
  public void list(StorageList list) {
    echo(this.output + list.toString());
  }

  /**
   * Prints the String representation of the list of tasks that matches the specified date
   * @param list List of tasks to be printed
   * @param date Date to be matched
   */
  public void listMatches(StorageList list, LocalDateTime date) {
    echo(this.output + list.toString(date));
  }
  
  /**
   * Prints the String representation of the list of tasks that matches the specified regex
   * @param list List of tasks to be printed
   * @param regex Regular expression to be matched
   */
  public void listMatches(StorageList list, String regex) {
    echo(this.output + list.toString(regex));
  }
  /**
   * Prints the specified output format
   * @param s String to be printed out
   */
  public static void echo(String s) {
    System.out.println(wrapper(s));
  }

  /**
   * Wraps the String with the specified format
   * @param s String to be wrapped
   * @return Wrapped String
   */
  public static String wrapper(String s) {
    return "\t____________________________________________________________\n" 
            + "\t " + s
            + "\t____________________________________________________________";
  }
}
