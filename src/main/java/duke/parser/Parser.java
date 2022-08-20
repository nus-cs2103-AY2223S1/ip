package duke.parser;

import duke.exception.DukeException;
import duke.task.*;
import duke.ui.Ui;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

  private static final Scanner scanner = new Scanner(System.in);

  public static boolean readCommand(Ui ui, TaskList taskList)
    throws DukeException {
    String[] inputs = scanner.nextLine().trim().split("\\s+", 2);

    switch (inputs[0]) {
      case "bye":
        return true;
      case "list":
        ui.showOutput(taskList.toString());
        return false;
      case "mark":
        if (inputs.length < 2) {
          throw new DukeException("Invalid input format!");
        }
        try {
          taskList.mark(Integer.parseInt(inputs[1]) - 1, true);

          ui.showOutput("Task " + inputs[1] + " is marked as done!");
        } catch (NumberFormatException e) {
          throw new DukeException("Invalid input format!");
        }
        return false;
      case "unmark":
        if (inputs.length < 2) {
          throw new DukeException("Invalid input format!");
        }
        try {
          taskList.mark(Integer.parseInt(inputs[1]) - 1, false);

          ui.showOutput("Task " + inputs[1] + " is marked as not done!");
        } catch (NumberFormatException e) {
          throw new DukeException("Invalid input format!");
        }
        return false;
      case "delete":
        if (inputs.length < 2) {
          throw new DukeException("Invalid input format!");
        }
        try {
          taskList.delete(Integer.parseInt(inputs[1]) - 1);

          ui.showOutput("Task " + inputs[1] + " has been deleted!");
        } catch (NumberFormatException e) {
          throw new DukeException("Invalid input format!");
        }
        return false;
      case "todo":
        if (inputs.length < 2) {
          throw new DukeException("Invalid input format!");
        }
        Todo todo = new Todo(inputs[1], false);
        taskList.add(todo);

        ui.showOutput("Todo task has been added!");
        ui.showOutput(todo.toString());
        return false;
      case "deadline":
        if (inputs.length < 2) {
          throw new DukeException("Invalid input format!");
        }
        String[] deadlineArgs = inputs[1].split("\\s+/by\\s+", 2);
        if (deadlineArgs.length < 2) {
          throw new DukeException("Invalid input format!");
        }

        try {
          Deadline deadline = new Deadline(
            deadlineArgs[0],
            false,
            deadlineArgs[1]
          );
          taskList.add(deadline);

          ui.showOutput("Deadline task has been added!");
          ui.showOutput(deadline.toString());
        } catch (DateTimeParseException e) {
          throw new DukeException("Invalid date format!");
        }
        return false;
      case "event":
        if (inputs.length < 2) {
          throw new DukeException("Invalid input format!");
        }
        String[] eventArgs = inputs[1].split("\\s+/at\\s+", 2);
        if (eventArgs.length < 2) {
          throw new DukeException("Invalid input format!");
        }

        try {
          Event event = new Event(eventArgs[0], false, eventArgs[1]);
          taskList.add(event);

          ui.showOutput("Event task has been added!");
          ui.showOutput(event.toString());
        } catch (DateTimeParseException e) {
          throw new DukeException("Invalid date format!");
        }
        return false;
      default:
        throw new DukeException(
          "Sorry, I don't know what do you mean by that."
        );
    }
  }
}
