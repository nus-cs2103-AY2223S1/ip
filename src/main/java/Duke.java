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
    String[] list = new String[100];
    int listIndex = 0;

    while (!input.equals("bye")) {
      try {
        reader = new BufferedReader(new InputStreamReader(System.in));
        input = reader.readLine();
        if (input.equals("bye")) {
          reader.close();
          break;
        }

        switch (input) {
          case "list":
            System.out.println("_______________________________________________________");
            for (int i = 0; i < listIndex; ++i) {
              System.out.println("\t " + (i + 1) + ". " + list[i]);
            }
            System.out.println("_______________________________________________________");
            break;
          default:
            list[listIndex++] = input;
            System.out.println("_______________________________________________________" +
                "\n\t added: " + input + "\n" +
                "_______________________________________________________");
        }
      } catch (IOException e) {
      }
    }

    System.out.println("_______________________________________________________" +
        "\n\t Bye. Hope to see you again soon!\n" +
        "_______________________________________________________");
  }
}
