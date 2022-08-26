import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    enum TASKS {
        TODO,
        DEADLINE,
        EVENT
    }

    public ArrayList<Task> addTask(String[] taskText) throws TaskNotFoundException, ContentNotFoundException {
        if (taskText[0].contentEquals("todo")) {
            if (taskText.length > 1) {
                ToDo todo = ToDo.of(taskText[1], "UI");
                tasks.add(todo);
                System.out.println("Ah, more reality stuff. Here, I've added:\n" + todo.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new ContentNotFoundException(
                        "Input error: no content found after todo");
            }
        } else if (taskText[0].contentEquals("deadline")) {
            if (taskText.length > 1) {
                Deadline deadline = Deadline.of(taskText[1], "UI");
                tasks.add(deadline);
                System.out.println("Ah, more reality stuff. Here, I've added:\n" + deadline.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new ContentNotFoundException(
                        "Input error: no content found after deadline");
            }
        } else if (taskText[0].contentEquals("event")) {
            if (taskText.length > 1) {
                Event event = Event.of(taskText[1], "UI");
                tasks.add(event);
                System.out.println("Ah, more reality stuff. Here, I've added:\n" + event.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new ContentNotFoundException(
                        "Input error: no content found after event");
            }
        } else {
            throw new TaskNotFoundException(
                    "Command not found: " + taskText[0]);
        }

        return tasks;
    }

    public TaskList loadTask(String[] details) {
        if (details[0].trim().contentEquals("T")) {
            if (details.length > 1) {
                ToDo todo = ToDo.of(details[1], "FILE");
                tasks.add(todo);
                System.out.println("Ah, more reality stuff. Here, I've added:\n" + todo.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } else if (details[0].trim().contentEquals("D")) {
            if (details.length > 1) {
                Deadline deadline = Deadline.of(details[1], "FILE");
                tasks.add(deadline);
                System.out.println("Ah, more reality stuff. Here, I've added:\n" + deadline.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } else if (details[0].trim().contentEquals("E")) {
            if (details.length > 1) {
                Event event = Event.of(details[1], "FILE");
                tasks.add(event);
                System.out.println("Ah, more reality stuff. Here, I've added:\n" + event.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        }
        return this;
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    public void list() {
        if (tasks.size() <= 0) {
            System.out.println("There are no tasks in the list!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            int count = i + 1;
            System.out.println(count + ". " + tasks.get(i).toString());
        }
    }

    public String markTask(Integer n) {
        Task taskMark = tasks.get(n.intValue() - 1);
        taskMark.done();
        return taskMark.toString();
    }

    public String unmarkTask(Integer n) {
        Task taskMark = tasks.get(n.intValue() - 1);
        taskMark.notDone();
        return taskMark.toString();
    }

    public String deleteTask(Integer n) {
        Task deletedTask = tasks.remove(n.intValue() - 1);
        return deletedTask.toString() + "\n" + "Now you have " + tasks.size() + " tasks in the list.";
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }
}
