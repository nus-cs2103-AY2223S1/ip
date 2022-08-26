package duke;

import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> taskList = new ArrayList<>();

    static ArrayList<Task> copyToList(ArrayList<Task> newArr) {
        for (int i = 1; i <= taskList.size(); i++) {
            newArr.add(taskList.get(i - 1));
        }
        return newArr;
    }

    public static void add(String input) {
        Task newTask = new Task("");
        try {
            if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                if (input.startsWith("todo")) {
                    if (!input.equals("todo")) {
                        String newInput = input.replace("todo ", "");
                        if (newInput.trim().length() == 0) {
                            throw new DukeToDoIncorrectException();
                        } else {
                            Todo todoTask = new Todo(newInput);
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
                            Deadline deadlineTask = new Deadline(newDescription, date);
                            taskList.add(deadlineTask);
                            newTask = deadlineTask;
                        } else if (input.startsWith("event")) {
                            String newDescription = description.replace("event ", "");
                            Event eventTask = new Event(newDescription, date);
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
            Ui.addedMsg(newTask);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void delete(String input) {
        if (!input.equals("delete")) {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                Task removedTask = taskList.remove(index);
                Ui.deleteMsg(removedTask);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(new DukeException("ERROR: there is no item in the list there!").getMessage());
            }
        } else {
            System.out.println(new DukeException("ERROR: please specify an index.").getMessage());
        }
    }

    public static void showList() {
        if (taskList.size() == 0) {
            System.out.println(new DukeException("ERROR: empty list.").getMessage());
        }
        for (int i = 1; i <= taskList.size(); i++) {
            Task currTask = taskList.get(i - 1);
            System.out.println(i + "." + currTask.toString());
        }
    }

    static void markChild(int index) {
        taskList.get(index).mark();
        Ui.markMsg(index);
    }

    static void unmarkChild(int index) {
        taskList.get(index).unmark();
        Ui.unmarkMsg(index);
    }

    public static void find(String input) {
        Ui.resultsMsg();
        ArrayList<Task> temp = new ArrayList<>();
        copyToList(temp).removeIf(s -> !s.getDescription().contains(input));
        ;
        if (temp.size() == 0) {
            System.out.println(new DukeException("ERROR: empty list.").getMessage());
        }
        for (int i = 1; i <= temp.size(); i++) {
            Task currTask = temp.get(i - 1);
            System.out.println(i + "." + currTask.toString());
        }
    }
}
