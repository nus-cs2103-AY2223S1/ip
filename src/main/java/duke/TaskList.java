package duke;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Copies contents of old list into new list.
     * @param newArr the new arraylist to be copied into.
     */
    static ArrayList<Task> copyToList(ArrayList<Task> newArr) {
        for (int i = 1; i <= taskList.size(); i++) {
            newArr.add(taskList.get(i - 1));
        }
        return newArr;
    }

    public static String add(String input, Storage storage) {
        Task newTask = new Task("");
        try {
            if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                if (input.startsWith("todo")) {
                    if (!input.equals("todo")) {
                        String newInput = input.replace("todo ", "");
                        if (newInput.trim().length() == 0) {
                            throw new DukeToDoIncorrectException();
                        } else {
                            TodoTask todoTask = new TodoTask(newInput);
                            taskList.add(todoTask);
                            newTask = todoTask;
                        }
                    } else {
                        throw new DukeToDoIncorrectException();
                    }
                } else {
                    try {
                        String description = input.substring(0, input.toString().indexOf("/") - 1);
                        String date = input.substring(input.lastIndexOf("/") + 4);

                        if (input.startsWith("deadline")) {
                            String newDescription = description.replace("deadline ", "");
                            DeadlineTask deadlineTask = new DeadlineTask(newDescription, date);
                            taskList.add(deadlineTask);
                            newTask = deadlineTask;
                        } else if (input.startsWith("event")) {
                            String newDescription = description.replace("event ", "");
                            EventTask eventTask = new EventTask(newDescription, date);
                            taskList.add(eventTask);
                            newTask = eventTask;
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        if (input.contains("deadline")) {
                            throw new DukeDeadlineIncorrectException();
                        } else {
                            throw new DukeEventIncorrectException();
                        }
                    }
                }
            } else {
                throw new DukeUnknownException();
            }
            storage.writeData();
            return Ui.addedMsg(newTask);
        } catch (DukeException e) {
            return (e.getMessage());
        }
    }

    public static String delete(String input, Storage storage) {
        if (!input.equals("delete")) {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                assert index >= 0 : "index should at least 0";
                Task removedTask = taskList.remove(index);
                storage.writeData();
                return Ui.deleteMsg(removedTask);
            } catch (IndexOutOfBoundsException e) {
                return (new DukeException("ERROR: there is no item in the list there!").getMessage());
            }
        } else {
            return (new DukeException("ERROR: please specify an index.").getMessage());
        }
    }

    /**
     * Method to show contents of list.
     * @return the contents of the list as a string.
     */
    public static String showList() {
        if (taskList.size() == 0) {
            return (new DukeException("ERROR: empty list.").getMessage());
        }
        String list = "";
        for (int i = 0; i < TaskList.taskList.size(); i++) {
            int j = i + 1;
            list = list + j + "." + TaskList.taskList.get(i) + "\n";
        }
        return "Here are your tasks:" + "\n" + list;
    }

    static String markChild(int index, Storage storage) {
        taskList.get(index).mark();
        storage.writeData();
        return Ui.markMsg(index);
    }

    static String unmarkChild(int index, Storage storage) {
        taskList.get(index).unmark();
        storage.writeData();
        return Ui.unmarkMsg(index);
    }

    public static String find(String input) {
        ArrayList<Task> temp = new ArrayList<>();

        copyToList(temp).removeIf(s -> !s.getDescription().toLowerCase().contains(input.toLowerCase())
                &&
                !s.getDate().toLowerCase().contains(input.toLowerCase()));
        ;
        if (temp.size() == 0) {
            return (new DukeException("ERROR: empty list.").getMessage());
        }
        String list = "";
        for (int i = 0; i < temp.size(); i++) {
            int j = i + 1;
            list = list + j + "." + temp.get(i) + "\n";
        }
        return Ui.resultsMsg(list);
    }
}
