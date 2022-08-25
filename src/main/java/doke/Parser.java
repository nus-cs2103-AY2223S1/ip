package doke;

import java.io.IOException;

public class Parser {

    public boolean processString(TaskList taskList, Storage storage, String string, Ui ui) {

        //handle bye and list action
        if (string.equals("bye")) {
            return false;
        } else if (string.equals("list")) {
            taskList.listOut(ui);
            return true;
        }

        String[] temp = string.split(" ");
        int length = temp.length;
        String action = temp[0];

        //handle if adding action is invalid
        if (length == 1) {
            if (action.equals("todo")) {
                try {
                    throw new DokeException("todo");
                } catch (DokeException d) {
                    ui.printOut(d.toString());
                }
            } else if (action.equals("deadline")) {
                try {
                    throw new DokeException("deadline");
                } catch (DokeException d) {
                    ui.printOut(d.toString());
                }
            } else if (action.equals("event")) {
                try {
                    throw new DokeException("event");
                } catch (DokeException d) {
                    ui.printOut(d.toString());
                }
            } else {
                try {
                    throw new DokeException();
                } catch (DokeException d) {
                    ui.printOut(d.toString());
                }
            }
            return true;
        }

        //handle a find command
        if (action.equals("find") && temp.length > 1) {
            String search = string.substring(5);
            taskList.searchString(search, ui);
            return true;
        }

        //handle if delete, mark, and unmark action is valid
        if (length == 2 && Miscellaneous.isInt(temp[1])
                && (action.equals("mark") || action.equals("unmark") || action.equals("delete"))) {
            int num = Miscellaneous.toInt(temp[1]);
            Task current = taskList.getTask(num);
            String message;
            if(action.equals("mark")) {
                try {
                    current.markDone();
                    storage.updateFile(taskList.getList());
                    message = "_________________________ \n" + "I've marked this task as done: \n" +
                            current.toString() + "\n _________________________ \n";
                    ui.printOut(message);
                } catch (DokeException a) {
                    message = "_________________________ \n" + "Task is already marked done: \n" +
                            current.toString() + "\n _________________________ \n";
                    ui.printOut(message);
                }
            } else if(action.equals("unmark")) {
                try {
                    current.markNotDone();
                    storage.updateFile(taskList.getList());
                    message = "_________________________ \n" + "I've marked this task not done: \n" +
                            current.toString() + "\n _________________________ \n";
                    ui.printOut(message);
                } catch (DokeException a) {
                    message = "_________________________ \n" + "This task is already not marked done: \n" +
                            current.toString() + "\n _________________________ \n";
                    ui.printOut(message);
                }
            } else if(action.equals("delete")) {
                taskList.deleteTask(num);
                storage.updateFile(taskList.getList());
                message = "_________________________ \n" + "This task has been removed \n" +
                        current.toString() + "\n" + "Now you have " + taskList.getSize() +
                        " tasks!! "+ "\n _________________________ \n";
                ui.printOut(message);
            }

            return true;
        }

        //handle the case if the task adding action is valid
        if (action.equals("todo") || action.equals("deadline") || action.equals("event")){
            String word = "";
            String time = "";
            int i = 1;

            while (i < length && ((!temp[i].equals("/at") && action.equals("event"))
                    || (!temp[i].equals("/by") && action.equals("deadline"))
                    || action.equals("todo"))) {
                if (i == 1) {
                    word = word + temp[i];
                } else {
                    word = word + " " + temp[i];
                }
                i++;
            }

            if (i != length) {
                time = temp[++i];
                i++;
                while (i < length) {
                    time += " " + temp[i];
                    i++;
                }
            }

            Task addition;


            if (action.equals("todo")) {
                addition = new ToDo(word);
                taskList.addTask(addition);
            } else if (action.equals("deadline")) {
                addition = new Deadline(word, time);
                taskList.addTask(addition);
            } else {
                addition = new Events(word, time);
                taskList.addTask(addition);
            }
            ui.printOut("_________________________ \n" + "added: " + addition.toString() + "\n" +
                    "Nice, now you have " + taskList.getSize() + " tasks!! \n" +
                    "_________________________" +"\n");

            try {
                storage.writeToFile( Storage.createWordForFile(addition));
            } catch (IOException e) {
                ui.printOut("_________________________ \n" + "Something went wrong, try again" +
                        "\n _________________________ \n");

            }
            return true;
        }

        try {
            throw new DokeException();
        } catch (DokeException d) {
            ui.printOut(d.toString());
        }

        return true;
    }
}
