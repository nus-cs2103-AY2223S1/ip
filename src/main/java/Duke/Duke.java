package duke;

/**
 * Represents the Duke class to run the Duke program
 * @author Reuben Chay
 */
public class Duke {

    /**
     * Main driver function to run the Duke program
     * @param args arguments supplied
     * @throws DukeException exceptions specific to the Duke program
     * @throws IllegalArgumentException exceptions representing illegal/disallowed user inputs
     */
    public static void main(String[] args) throws DukeException, IllegalArgumentException {

        TaskList list = new TaskList();
        Storage storage = new Storage("./tasks.txt");
        Ui ui = new Ui();
        Parser parser = new Parser();

        // attempt to open file within same folder as src code, create file if file doesn't exist
       storage.openFile();

        // at this point file will exist, init taskList
        storage.listInit(list.getList());

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMsg = "Hello! I'm\n" + logo + "\nWhat can I do for you?\n";
        System.out.println(welcomeMsg);

        String input = "";

        while (true) {

            try {
                input = ui.scan();

                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.equals("list")) {
                    list.taskPrinter();
                } else if (input.startsWith("mark")) {
                    String in = parser.replaceAll(input, "mark");
                    if (in.isEmpty()) {
                        throw new TaskStatusException("Please provide task number");
                    } else {
                        int index = Integer.parseInt(in) - 1;
                        if (index >= list.getList().size() || index < 0) {
                            throw new TaskStatusException("Please provide correct task number");
                        } else {
                            if (list.getList().get(index).getDone()) {
                                throw new TaskStatusException("Task already marked done!");
                            }
                            list.getList().get(index).Done();
                            Task newTask = list.getList().get(index);
                            storage.fileUpdater(newTask, false, index);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(list.getList().get(index).toString());
                        }
                    }
                } else if (input.startsWith("unmark")) {
                    String in = parser.replaceAll(input, "unmark");
                    if (in.isEmpty()) {
                        throw new TaskStatusException("Please provide task number");
                    } else {
                        int index = Integer.parseInt(in) - 1;
                        if (index >= list.getList().size() || index < 0){
                            throw new TaskStatusException("Please provide correct task number");
                        } else {
                            if (!list.getList().get(index).getDone()) {
                                throw new TaskStatusException("Task is currently marked undone");
                            }
                            list.getList().get(index).unDone();
                            Task newTask = list.getList().get(index);
                            storage.fileUpdater(newTask, false, index);
                            System.out.println("Ok, I've marked this task as not done yet:");
                            System.out.println(list.getList().get(index).toString());
                        }
                    }
                } else if (input.startsWith("delete")) {
                    String in = parser.replaceAll(input, "delete");
                    if (in.isEmpty()) {
                        throw new TaskStatusException("Please provide task number");
                    } else {
                        int index = Integer.parseInt(in) - 1;
                        if (index >= list.getList().size() || index < 0){
                            throw new TaskStatusException("Please provide correct task number");
                        } else {
                            Task removed = list.getList().remove(index);
                            storage.fileUpdater(removed, true, index);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(removed.toString());
                            System.out.println("Now you have " + list.getList().size() + " task(s) in the list");
                        }
                    }
                } else {
                    Task task;
                    if (input.startsWith("todo")) {
                        String s = input;
                        if (parser.replaceAll(s, "todo").isEmpty()) {
                            throw new IncompleteTaskNameException("Please provide task name");
                        } else {
                            task = new ToDo(parser.replaceAll(input, "todo"));
                        }
                    } else if (input.startsWith("event")) {
                        String[] arr = input.split("/");
                        if (arr.length != 2) {
                            throw new IllegalArgumentException("Please write your task in the proper format");
                        } else {
                            String name = parser.replaceAll(arr[0], "event");
                            String date = arr[1];
                            if (name.isEmpty()) {
                                throw new IncompleteTaskNameException("Please provide task name");
                            } else if (date.isEmpty()) {
                                throw new MissingDateException("Please provide a time for your task");
                            } else if (!date.startsWith("at")) {
                                throw new IllegalArgumentException("Event time must be specified with at");
                                } else {
                                task = new Event(parser.replaceAll(arr[0], "event"),
                                        parser.replaceAll(arr[1], "at"));
                            }
                        }
                    } else if (input.startsWith("deadline")) {
                        String[] arr = input.split("/");
                        if (arr.length != 2) {
                            throw new IllegalArgumentException("Please write your task in the proper format");
                        } else {
                            String name = parser.replaceAll(arr[0], "deadline");
                            String date = arr[1];
                            if (name.isEmpty()) {
                                throw new IncompleteTaskNameException("Please provide task name");
                            } else if (date.isEmpty()) {
                                throw new MissingDateException("Please provide a time for your task");
                            } else if (!date.startsWith("by")) {
                                throw new IllegalArgumentException("Deadline time must be specified with by");
                            } else {
                                task = new Deadline(parser.replaceAll(arr[0], "deadline"),
                                        parser.replaceAll(arr[1], "by"));
                            }
                        }
                    }
                    else {
                        throw new IllegalArgumentException("Please provide a proper format");
                    }

                    if (!task.isToDo() && task.getDateTime() == null) {
                        // to deal with incorrect date format (to-do no date)
                        throw new DukeException("Input date-time in the format yyyy-MM-dd HHmm");
                    } else {
                        list.getList().add(task);
                        storage.writeToFile(task);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println("  " + task.toString());
                        System.out.println("Now you have " + list.getList().size() + " task(s) in the list.");
                    }
                }
                
            } catch (DukeException e) {
                System.out.println(e.toString());
            } catch (IllegalArgumentException e) {
                System.out.println(e.toString());
            }
        }

        ui.close();
    }

}
