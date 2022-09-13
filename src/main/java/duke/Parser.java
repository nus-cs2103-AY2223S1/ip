package duke;

import java.io.IOException;

/**
 * A Parser class to read the user instructions and make send of them.
 */
public class Parser {

    /**
     * The Parser constructor.
     */
    Parser() {

    }
    private static String parseToDo(String todo, TaskList taskList) throws DukeException {
        //parses a string to obtain a to do event description and its date  and adds it to the task list
        Ui ui = new Ui(taskList);
        try {
            todo = todo.split(" ", 2)[1].trim();
            ToDo taskToDo = new ToDo(todo);
            //will not add the task if its a duplicate
            if (taskList.isDuplicateTask(taskToDo)) {
                throw new DukeException("Duplicate task detected, you might want\n" +
                        "to be more specific if this task is\n" +
                        "similar to another task on your list.");
            }
            taskList.addTask(taskToDo);
            return (ui.printAddedTask(taskToDo.toString()));

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(("OOPS!!! The description of a " +
                    "ToDo cannot be empty."));

        }

    }

    private static String parseDeadline(String deadline, TaskList taskList) throws DukeException {
        Ui ui = new Ui(taskList);
        try {
            //splits away deadline
            deadline = deadline.split(" ", 2)[1];
            String desc = deadline.split("/by")[0].trim();
            String by = deadline.split("/by")[1].trim();
            Deadline taskDeadline = new Deadline(desc, by);
            //will not add the task if it's a duplicate
            if (taskList.isDuplicateTask(taskDeadline)) {
                //throws exception for duplicate warning and adds task to the list.
                throw new DukeException("Duplicate task detected, you might want\n" +
                        "to be more specific if this task is\n" +
                        "similar to another task on your list.");
            }
            taskList.addTask(taskDeadline);
            return (ui.printAddedTask(taskDeadline.toString()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(("OOPS!!! The description of a Deadline " +
                    "cannot be empty/incomplete.\n" +
                    "The format is event <deadline_name> /by yyyy-mm-dd"));
        }
    }

    private static String parseEvent(String event, TaskList taskList) throws DukeException {
        Ui ui = new Ui(taskList);
        try {
            //splits away event into it's description and date
            event = event.split(" ", 2)[1];
            String desc = event.split("/at")[0].trim();
            String at = event.split("/at")[1].trim();
            Event taskEvent = new Event(desc, at);
            //will not add the task if its a duplicate
            if (taskList.isDuplicateTask(taskEvent)) {
                throw new DukeException("Duplicate task detected, you might want\n" +
                        "to be more specific if this task is\n" +
                        "similar to another task on your list.");
            }
            taskList.addTask(taskEvent);
            return (ui.printAddedTask(taskEvent.toString()));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(("OOPS!!! The description of a Deadline " +
                    "cannot be empty/incomplete.\n" +
                    "The format is event <event_name> /at yyyy-mm-dd"));
        }
    }

    private static String parseDelete(String deletedTask, TaskList taskList) throws DukeException {
        try {
            Ui ui = new Ui(taskList);
            deletedTask = deletedTask.split(" ", 2)[1].trim();
            int index = Integer.valueOf(deletedTask) - 1;
            String msg = taskList.getTask(index).toString();
            taskList.removeTask(index);
            return (ui.printRemovedTask(msg));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(("OOPS!!! You are trying to delete a task with an index that" +
                    " does not exist!"));
        }
    }

    private static String parseFind(String str, TaskList taskList) throws DukeException {
        Ui ui = new Ui(taskList);
        String wantedTask = str.split(" ")[1].trim();
        String msg = "";
        int counter = 1;
        for (int i = 0; i < taskList.getSize(); i++) {
            String task = taskList.getTask(i).toString();
            //this index is for labelling in duke
            if (task.contains(wantedTask)) {
                String foundTask = counter + "." + task;
                msg += foundTask + "\n";
                counter++;
            }
        }
        return (ui.printFindTask(msg));
    }

    private static boolean findDuplicate(Storage storage, Task task) {
        return true;
    }

    /**
     * Parses a user input and stores it inside a file while also showing a string in response to the input
     *
     * @param storage
     * @param taskList
     * @param str
     * @return String in response to the input of the user
     * @throws DukeException
     */
    public String parseInstruction(Storage storage, TaskList taskList, String str) throws DukeException {
        Ui ui = new Ui(taskList);
        String response = "";
        try {
            if (str.equals("bye")) {
                //exits the application
                System.exit(0);
                return ui.goodbye();
            }
            //for listing
            if (str.equals("list")) {
                return ui.printList();
            } else {
                String instruction = str.split(" ")[0];
                if (instruction.equals("mark")) {
                    //this is as index is greater than array index by 1
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    taskList.getTask(index).setDone();
                    response += (ui.printMarkDone(taskList.getTask(index).toString()));

                } else if ((instruction.equals("unmark"))) {
                    int index = Integer.valueOf(str.split(" ")[1]) - 1;
                    taskList.getTask(index).setUndone();
                    response += (ui.printMarkUndone(taskList.getTask(index).toString()));

                } else if (instruction.equals("todo")) {
                    response += parseToDo(str, taskList);

                } else if (instruction.equals("deadline")) {
                    response += parseDeadline(str, taskList);

                } else if (instruction.equals("event")) {
                    response += parseEvent(str, taskList);

                } else if (instruction.equals("delete")) {
                    response += parseDelete(str, taskList);

                } else if (instruction.equals("find")) {
                    response += parseFind(str, taskList);

                } else {
                    throw new DukeException("I have no idea what you are saying, this is not a task >_<");
                }
            }

            try {
                storage.updateFile("data/duke.txt", taskList);//updates dukeFile after each input
            } catch (IOException e) {
                System.out.println("Error in saving the file");
            }
            return response;
        } catch (DukeException e) {
            //returns the exception message
            return e.toString();
        }
    }

}

