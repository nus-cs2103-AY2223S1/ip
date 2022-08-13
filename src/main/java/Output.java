/**
 * Enum for the various different outputs that the Chat-bot can give.
 */
public enum Output{
  GREETINGS (" ____        _        \n\t "
          + "|  _ \\ _   _| | _____ \n\t "
          + "| | | | | | | |/ / _ \\\n\t "
          + "| |_| | |_| |   <  __/\n\t "
          + "|____/ \\__,_|_|\\_\\___|\n\n\t "
          + "Hello! I'm Duke\n\t "
          + "What can I do for you?\n"),
  GOODBYE ("Bye. Hope to see you again soon!\n");

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
  void print() {
    echo(this.output);
  }

  /**
   * Specifies the output format
   * @param s String to be printed out
   */
  static void echo(String s) {
    System.out.println("\t--------------------------------------------------------");
    System.out.println("\t " + s);
    System.out.println("\t--------------------------------------------------------");
  }
}
