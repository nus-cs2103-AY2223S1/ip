package duke;

/**
 * Parser class to assist in string parsing
 */
public class Parser {

    StringReplacer stringReplacer;
    Storage storage;
    TaskList taskList;

    Parser(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
        this.stringReplacer = new StringReplacer();
    }


    public String parse(String input)  {
        if (input.equals("bye")) {
            return "Bye! Hope to see you again!";
        }

        try {
            String toReply = "";

            if (input.equals("list")) {
                toReply += taskList.getAllTasks();
                System.out.println(toReply);
                return toReply;
            } else if (input.startsWith("mark")) {
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
                        toReply += "Nice! I've marked this task as done:\n";
                        toReply += taskList.getList().get(index).toString();
                    }
                }
            } else if (input.startsWith("unmark")) {
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
                        toReply += "Ok, I've marked this task as not done yet:\n";
                        toReply += taskList.getList().get(index).toString();
                    }
                }
            } else if (input.startsWith("delete")) {
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
                        toReply += "Noted. I've removed this task:\n";
                        toReply += removed.toString();
                        toReply += "Now you have " + taskList.getList().size() + " task(s) in the list";
                    }
                }
            } else if (input.startsWith("find")) {
                taskList.findTask(stringReplacer.replaceAll(input, "find"));
            } else {
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
                    throw new IllegalArgumentException("Please provide a proper format");
                }

                if (!task.isToDo() && task.getDateTime() == null) {
                    // to deal with incorrect date format (to-do no date)
                    throw new DukeException("Input date-time in the format yyyy-MM-dd HHmm");
                } else {
                    taskList.getList().add(task);
                    assert taskList.getList().size() > 0 : "Task list not updated properly";
                    storage.writeToFile(task);
                    toReply += "Got it. I've added this task: \n";
                    toReply += "  " + task.toString() +"\n";
                    toReply += "Now you have " + taskList.getList().size() + " task(s) in the list.";
                }
            }
            return toReply;
        } catch (DukeException | IllegalArgumentException e) {
            return e.toString();
        }
    }

}

