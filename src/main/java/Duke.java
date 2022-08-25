import data.LocalStorage;
import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import models.Deadline;
import models.Event;
import models.Task;
import models.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import static utils.DukePrint.dukePrint;

public class Duke {
    protected static ArrayList<Task> list;

    public static void main(String[] args) {
        Scanner dukeSc = new Scanner(System.in);
        LocalStorage storage = new LocalStorage();
        list = storage.load();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String greeting = "Hello! Imma Duke!\n What can I do for you?";
        String farewell = "Bye. Duke misses you.";

        dukePrint("Hello from\n" + logo);
        dukePrint(greeting);

        while (true) {
            String input = dukeSc.nextLine();

            try {
                if (input.equalsIgnoreCase("bye")) {
                    dukePrint(farewell);
                    return;
                } else if (input.equalsIgnoreCase("list")) {
                    dukePrint("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        dukePrint(i + 1 + ". " + list.get(i));
                    }
                }  else if (input.startsWith("mark ") || input.startsWith("unmark ") || input.startsWith("delete ")) {
                    try {
                        if (input.startsWith("mark ")) {
                            // Parse substring starting from index 5
                            int taskNum = Integer.parseInt(input.substring(5)) - 1;
                            Task t = list.get(taskNum);
                            t.markAsDone();
                            dukePrint("Nice! I've marked this tasks as done:\n" + t);
                        } else if (input.startsWith("unmark ")) {
                            // Parse substring starting from index 7
                            int taskNum = Integer.parseInt(input.substring(7)) - 1;
                            Task t = list.get(taskNum);
                            t.unmarkAsDone();
                            dukePrint("OK, I've marked this task as not done yet:\n" + t);
                        } else if (input.startsWith("delete ")) {
                            // Parse substring starting from index 7
                            int taskNum = Integer.parseInt(input.substring(7)) - 1;
                            Task t = list.remove(taskNum);
                            String tasksLeft = String.format("Now you have %d task(s) in the list.", list.size());
                            String printStr = String.join("\n",
                                    "Got it. I've added this task:",
                                    t.toString(),
                                    tasksLeft);
                            dukePrint(printStr);
                        }
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        int listSize = list.size();
                        dukePrint(String.format("Please enter a valid number from 1 to %d.%n", listSize));
                    }
                } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                    try {
                        Task newTask = null;
                        if (input.startsWith("todo ")) {
                            String todoInput = input.substring(5).trim();
                            if (todoInput.length() == 0) {
                                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                            }
                            newTask = new Todo(input.substring(5));
                        } else if (input.startsWith("deadline ")) {
                            String[] deadlineInputs = input.substring(9).split(" /by ", 2);
                            if (deadlineInputs.length < 2 || deadlineInputs[1].trim().equals("")) {
                                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                            }
                            newTask = new Deadline(deadlineInputs[0], deadlineInputs[1]);
                        } else if (input.startsWith("event ")) {
                            String[] eventInputs = input.substring(6).split(" /at ", 2);
                            if (eventInputs.length < 2 || eventInputs[1].trim().equals("")) {
                                throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                            }
                            newTask = new Event(eventInputs[0], eventInputs[1]);
                        }
                        list.add(newTask);
                        storage.write(list);
                        String tasksLeft = String.format("Now you have %d task(s) in the list.", list.size());
                        String printStr = String.join("\n",
                                "Got it. I've added this task:",
                                newTask.toString(),
                                tasksLeft);
                        dukePrint(printStr);
                    } catch (Exception e) {
                        dukePrint(e.getMessage());
                    }
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dukeException) {
                dukePrint(dukeException.getMessage());
            }
        }
    }
}
