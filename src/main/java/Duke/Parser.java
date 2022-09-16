package duke;

import java.time.LocalDateTime;

/**
 * Parser class to assist in string parsing
 */
public class Parser {

    public static final String CATCH_PHRASE = "I'm Mr Meseeks, look at me!\n" + "** POOF **\n";

    StringReplacer stringReplacer;
    Storage storage;
    TaskList taskList;

    Parser(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
        this.stringReplacer = new StringReplacer();
    }


    public String parse(String input) {
        input = input.trim();
        // For developers: uncomment the line below to echo parser input
        // System.out.println("Echo: " + input);
        String toReply = "";

        try {

            if (input.equals("bye")) {
                toReply = "Bye! Hope to see you again!";
                System.out.println(toReply);
                return toReply;
            }

            if (input.startsWith("update")) {
                String in = stringReplacer.replaceAll(input, "update");
                if (in.isEmpty()) {
                    throw new TaskStatusException("Please provide valid input");
                }
                if (!in.contains("/")) {
                    throw new IllegalArgumentException("Please supply valid command format for update");
                }
                String[] inputs = stringReplacer.editCommandHelper(in);
                int index = Integer.parseInt(inputs[0].trim()) - 1;
                if (index >= taskList.getList().size() || index < 0) {
                    throw new TaskStatusException("Please provide valid task number");
                }
                if (inputs[1].isEmpty()) {
                    throw new MissingDateException("Please provide a new date and time to update the task");
                }
                assert index >= 0 && index < taskList.getList().size() :
                        "Accessing index out of bounds for taskList";
                if (taskList.getList().get(index).isToDo()) {
                    throw new DukeException("ToDo task has no date to update!");
                } else {
                    Task toUpdate = taskList.getList().get(index);
                    LocalDateTime oldTime = toUpdate.getDateTime();
                    toUpdate.editDate(inputs[1]);
                    if (oldTime.equals(toUpdate.getDateTime())) {
                        throw new DukeException("Input date-time in the format yyyy-MM-dd HHmm");
                    }
                    this.storage.taskEdit(toUpdate, index);
                    toReply += "Ooooweee! I've updated the following task:\n";
                    toReply += taskList.getList().get(index).toString();
                    toReply += CATCH_PHRASE;
                    System.out.println(toReply);
                    return toReply;
                }
            }

            if (input.trim().equals("list")) {
                toReply += taskList.getAllTasks();
                toReply += CATCH_PHRASE;
                System.out.println(toReply);
                return toReply;
            }

            if (input.startsWith("mark")) {
                String in = stringReplacer.replaceAll(input, "mark");
                if (in.isEmpty()) {
                    throw new TaskStatusException("Please provide task number");
                } else {
                    int index = Integer.parseInt(in) - 1;
                    if (index >= taskList.getList().size() || index < 0) {
                        throw new TaskStatusException("Please provide correct task number");
                    } else {
                        if (taskList.getList().get(index).getDone()) {
                            throw new TaskStatusException("Task already marked done!");
                        }
                        taskList.getList().get(index).Done();
                        Task newTask = taskList.getList().get(index);
                        storage.fileUpdater(newTask, false, index);
                        toReply += "Ooooweee! I've marked this task as done:\n";
                        toReply += taskList.getList().get(index).toString();
                        toReply += CATCH_PHRASE;
                        System.out.println(toReply);
                        return toReply;
                    }
                }
            }

            if (input.startsWith("unmark")) {
                String in = stringReplacer.replaceAll(input, "unmark");
                if (in.isEmpty()) {
                    throw new TaskStatusException("Please provide task number");
                } else {
                    int index = Integer.parseInt(in) - 1;
                    if (index >= taskList.getList().size() || index < 0) {
                        throw new TaskStatusException("Please provide correct task number");
                    } else {
                        if (!taskList.getList().get(index).getDone()) {
                            throw new TaskStatusException("Task is currently marked undone");
                        }
                        taskList.getList().get(index).unDone();
                        Task newTask = taskList.getList().get(index);
                        storage.fileUpdater(newTask, false, index);
                        toReply += "Ooooweee, I've marked this task as not done yet:\n";
                        toReply += taskList.getList().get(index).toString();
                        toReply += CATCH_PHRASE;
                        System.out.println(toReply);
                        return toReply;
                    }
                }
            }

            if (input.startsWith("delete")) {
                String in = stringReplacer.replaceAll(input, "delete");
                if (in.isEmpty()) {
                    throw new TaskStatusException("Please provide task number");
                } else {
                    int index = Integer.parseInt(in) - 1;
                    if (index >= taskList.getList().size() || index < 0) {
                        throw new TaskStatusException("Please provide correct task number");
                    } else {
                        Task removed = taskList.getList().remove(index);
                        storage.fileUpdater(removed, true, index);
                        toReply += "Ooooweeee. I've removed this task:\n";
                        toReply += removed.toString();
                        toReply += "Now you have " + taskList.getList().size() + " task(s) in the list";
                        toReply += CATCH_PHRASE;
                        System.out.println(toReply);
                        return toReply;
                    }
                }
            }

            if (input.startsWith("find")) {
                toReply = taskList.findTask(stringReplacer.replaceAll(input, "find"));
                toReply += CATCH_PHRASE;
                System.out.println(toReply);
                return toReply;
            }

            Task task;
            if (input.startsWith("todo")) {
                String s = input;
                if (stringReplacer.replaceAll(s, "todo").isEmpty()) {
                    throw new IncompleteTaskNameException("Please provide task name");
                } else {
                    task = new ToDo(stringReplacer.replaceAll(input, "todo"));
                }
            } else if (input.startsWith("event")) {
                String[] arr = input.split("/");
                if (arr.length != 2) {
                    throw new IllegalArgumentException("Please write your task in the proper format");
                } else {
                    String name = stringReplacer.replaceAll(arr[0], "event");
                    String date = arr[1];
                    if (name.isEmpty()) {
                        throw new IncompleteTaskNameException("Please provide task name");
                    } else if (date.isEmpty()) {
                        throw new MissingDateException("Please provide a time for your task");
                    } else if (!date.startsWith("at")) {
                        throw new IllegalArgumentException("Event time must be specified with at");
                    } else {
                        task = new Event(stringReplacer.replaceAll(arr[0], "event"),
                                stringReplacer.replaceAll(arr[1], "at"));
                    }
                }
            } else if (input.startsWith("deadline")) {
                String[] arr = input.split("/");
                if (arr.length != 2) {
                    throw new IllegalArgumentException("Please write your task in the proper format");
                } else {
                    String name = stringReplacer.replaceAll(arr[0], "deadline");
                    String date = arr[1];
                    if (name.isEmpty()) {
                        throw new IncompleteTaskNameException("Please provide task name");
                    } else if (date.isEmpty()) {
                        throw new MissingDateException("Please provide a time for your task");
                    } else if (!date.startsWith("by")) {
                        throw new IllegalArgumentException("Deadline time must be specified with by");
                    } else {
                        task = new Deadline(stringReplacer.replaceAll(arr[0], "deadline"),
                                stringReplacer.replaceAll(arr[1], "by"));
                    }
                }
            } else {
                throw new IllegalArgumentException("Please provide a proper format or valid command");
            }

            if (!task.isToDo() && task.getDateTime() == null) {
                // to deal with incorrect date format (to-do no date)
                throw new DukeException("Input date-time in the format yyyy-MM-dd HHmm");
            } else {
                taskList.getList().add(task);
                assert taskList.getList().size() > 0 : "Task list not updated properly";
                storage.writeToFile(task);
                toReply += "Ooooooweeee a new task! Here it is: \n";
                toReply += "  " + task.toString() + "\n";
                toReply += "Now you have " + taskList.getList().size() + " task(s) in the list.";
                toReply += CATCH_PHRASE;
                System.out.println(toReply);
            }
            return toReply;
            
        } catch (DukeException | IllegalArgumentException e) {
            System.out.println(e);
            return e.toString();
        }
    }

}

