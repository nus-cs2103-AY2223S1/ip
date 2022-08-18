import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
  public static void main(String[] args) {
    String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);

    BufferedReader reader;
    String input = "";

    while (!input.equals("bye")) {
      try {
        reader = new BufferedReader(new InputStreamReader(System.in));
        input = reader.readLine();
        if (input.equals("bye")) {
          reader.close();
          break;
        }
        System.out.println("_______________________________________________________" +
            "\n\t" + input + "\n" +
            "_______________________________________________________");
      } catch (IOException e) {
      }
    }

    System.out.println("Bye. Hope to see you again soon!");
  }
}
