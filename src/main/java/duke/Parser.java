package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {

    private TaskList tasks;
    protected void Process(String s, TaskList tasks) {
        this.tasks = tasks;
        String[] words = s.split(" ");
        String command = words[0];
        switch (command) {
        case "list":
            OutputList();
            break;
        case "done":
            MarkItemDone(s);
            break;
        case "unmark":
            MarkItemUndone(s);
            break;
        case "todo":
            InsertTodo(s);
            break;
        case "deadline":
            InsertDeadline(s);
            break;
        case "event":
            InsertEvent(s);
            break;
        case "delete":
            DeleteTask(s);
            break;
        case "find":
            find(s);
            break;
        default:
            System.out.println("sorry, I don't understand you");
            break;
        }
    }

    protected void OutputList() {
        if (tasks.size() == 0) {
            System.out.println("you got no tasks");
        } else {
            System.out.println("heres your tasks");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.format("\t%d.%s\n", i + 1, tasks.get(i));
            }
        }
    }

    protected void InsertTodo(String input) {
        try {
            String description = input.substring(5);
            InsertTask(new Todo(description, false));
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        }
    }

    protected void InsertDeadline(String input) {
        try {
            String[] items = input.substring(9).split(" /by ");
            InsertTask(new Deadline(items[0], false, items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        } catch (DukeException e) {
            System.out.println("please enter a valid date format.");
            System.out.println("date: dd/mm/YYYY");
        }
    }

    protected void InsertEvent(String input) {
        try {
            String[] items = input.substring(6).split(" /at ");
            InsertTask(new Event(items[0], false, items[1]));
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
            System.out.println("description cannot be empty");
        } catch (DukeException e) {
            System.out.println("please enter a valid date format.");
            System.out.println("date and time: dd/mm/YYYY hh:mm");
        }
    }

    protected void InsertTask(Task task) {
        tasks.add(task);
        System.out.println("added: ");
        System.out.println("\t" + task);
        System.out.format("you have %d task(s) in the list\n", tasks.size());
    }

    protected void MarkItemDone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException("too many parameters!");
            }
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markDone();
            System.out.println("cool, this task is marked as done");
            System.out.println("\t" + tasks.get(index - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }

    protected void MarkItemUndone(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException("too many parameters!");
            }
            int index = Integer.parseInt(words[1]);
            tasks.get(index - 1).markUndone();
            System.out.println("ok, this task is marked as not done yet");
            System.out.println("\t" + tasks.get(index - 1));
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }

    protected void DeleteTask(String input) {
        try {
            String[] words = input.split(" ");
            if (words.length > 2) {
                throw new DukeException("too many parameters!");
            }
            int index = Integer.parseInt(words[1]);
            tasks.remove(index - 1);
            System.out.println("ok, i removed this task");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DukeException e) {
            System.out.println("format: mark <number>");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("enter a valid index");
        }
    }

    protected void find(String s) {
        try {
            String toFind = s.substring(5);
            ArrayList<Task> matchingTasks = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                if (task.description.contains(toFind)) {
                    matchingTasks.add(task);
                }
            }

            if (matchingTasks.isEmpty()) {
                System.out.println("\tyou have no matching tasks!");
            } else {
                int count = 1;
                System.out.println("\there are your matching tasks:");
                for (Task task : matchingTasks) {
                    System.out.format("\t%d. %s\n", count, task);
                    count++;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("invalid format! try find item");
        }
    }
}
