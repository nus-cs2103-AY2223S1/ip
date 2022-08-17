package main;

import commands.*;
import exception.DukeException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

  private ArrayList<Task> TaskList = new ArrayList<>();
  private boolean isExit = false;
  private Scanner sc = new Scanner(System.in);

  void run() {
    this.greet();
    while (true) {
      if (isExit) {
        break;
      } else {
        getCommand(sc.nextLine());
      }
    }
  }

  private void getCommand(String input) {
    try {
      Command c = Parser.parse(input);
      c.execute(TaskList);
      System.out.println('\n');
      if (c instanceof ByeCommand) {
        this.isExit = true;
      }
    } catch (DukeException e) {
      System.out.println(e.getMessage() + '\n');
    }
  }

  private void greet() {
    String logo =
      " ____        _        \n" +
      "|  _ \\ _   _| | _____ \n" +
      "| | | | | | | |/ / _ \\\n" +
      "| |_| | |_| |   <  __/\n" +
      "|____/ \\__,_|_|\\_\\___|\n";
    System.out.println("Hello from\n" + logo);
    System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
  }
}
