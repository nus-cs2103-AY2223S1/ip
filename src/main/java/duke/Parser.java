package duke;

import java.io.IOException;
import java.util.Scanner;

public class Parser {

    private TaskList taskList;
    private Storage storage;

    public Parser(TaskList taskList, Storage storage) {
        this.taskList = taskList;
        this.storage = storage;
    }

    public void parse() {
        try {
            Scanner sc = new Scanner(System.in);

            while (sc.hasNext()) {
                try {
                    String input = sc.next();

                    if (input.equals("bye")) {
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (input.equals("list")) {
                        System.out.println(this.taskList.printList());
                    } else if (input.equals("mark")) {
                        int number = Integer.parseInt(sc.next());

                        this.taskList.markList(number);

                        this.storage.writeToFile();
                    } else if (input.equals("unmark")) {
                        int number = Integer.parseInt(sc.next());

                        this.taskList.unMarkList(number);

                        this.storage.writeToFile();
                    } else if (input.equals("todo")) {
                        String toDo = sc.nextLine();

                        if (toDo.equals("")) {
                            throw new DukeException("☹ OOPS!!! " + "The description of a todo cannot be empty.\n");
                        } else {
                            ToDo toDoTask = new ToDo(toDo);

                            this.taskList.addTask(toDoTask);

                            System.out.println("Got it. I've added this task:\n "
                                    + this.taskList.getTask(this.taskList.getIndex()).toString() + "\nNow you have "
                                    + (this.taskList.getIndex() + 1) + " tasks in the list.\n");

                            this.storage.writeToFile();

                            this.taskList.incrementIndex();
                        }
                    } else if (input.equals("deadline")) {
                        String deadlineTask = sc.nextLine();

                        if (deadlineTask.equals("")) {
                            throw new DukeException("☹ OOPS!!! " +
                                    "The description of a deadline cannot be empty.\n");
                        } else {
                            int integer = deadlineTask.indexOf("/by");
                            String description = deadlineTask.substring(0, integer - 1);
                            String by = deadlineTask.substring(integer + 4);
                            Deadline deadLineTask = new Deadline(description, by);

                            this.taskList.addTask(deadLineTask);

                            System.out.println("Got it. I've added this task:\n "
                                    + this.taskList.getTask(this.taskList.getIndex()).toString()
                                    + "\nNow you have " + (this.taskList.getIndex() + 1) + " tasks in the list.\n");

                            this.storage.writeToFile();

                            this.taskList.incrementIndex();
                        }
                    } else if (input.equals("event")) {
                        String event = sc.nextLine();

                        if (event.equals("")) {
                            throw new DukeException("☹ OOPS!!! " + "The description of a event cannot be empty.\n");
                        } else {
                            int integer = event.indexOf("/at");
                            String description = event.substring(0, integer - 1);
                            String at = event.substring(integer + 4);

                            Event eventTask = new Event(description, at);

                            this.taskList.addTask(eventTask);

                            System.out.println("Got it. I've added this task:\n "
                                    + this.taskList.getTask(this.taskList.getIndex()).toString() + "\nNow you have "
                                    + (this.taskList.getIndex() + 1) + " tasks in the list.\n");

                            this.storage.writeToFile();

                            this.taskList.incrementIndex();
                        }
                    } else if (input.equals("delete")) {
                        int deleteIndex = sc.nextInt();

                        Task task = this.taskList.getTask(deleteIndex - 1);

                        this.taskList.removeTask(deleteIndex - 1);

                        this.taskList.decrementIndex();

                        this.storage.writeToFile();

                        System.out.println("Noted. I've removed this task:\n " + task.toString() + "\nNow you have "
                                + this.taskList.getIndex() + " tasks in the list.\n");
                    } else {
                        input += sc.nextLine();
                        throw new DukeException("☹ OOPS!!! I'm sorry, " + "but I don't know what that means :-(\n");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

