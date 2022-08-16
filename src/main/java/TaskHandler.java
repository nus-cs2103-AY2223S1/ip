import java.util.ArrayList;

public class TaskHandler {
    public static void add(String input, ArrayList<Task> taskList) {
        Task newTask = new Task("");
        if (input.contains("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            if (input.contains("todo")) {
                String newInput = input.replace("todo", "");
                Todo todoTask = new Todo(newInput);
                taskList.add(todoTask);
                newTask = todoTask;
            } else {
                String defaultInput = input.substring(0, input.toString().lastIndexOf("/"));
                String date = input.substring(input.lastIndexOf("/") + 3);
                if (input.startsWith("deadline")) {
                    String newInput = defaultInput.replace("deadline", "");
                    Deadline deadlineTask = new Deadline(newInput, date);
                    taskList.add(deadlineTask);
                    newTask = deadlineTask;
                } else if (input.startsWith("event")) {
                    String newInput = defaultInput.replace("event", "");
                    Event eventTask = new Event(newInput, date);
                    taskList.add(eventTask);
                    newTask = eventTask;
                }
            }
            System.out.println("Got it. I've added this task: \n" +
                    "   " + newTask.toString() +
                    "\n Now you have " + taskList.size() +
                    (taskList.size() > 1 ? " tasks in your list." : " task in your list."));
        } else {
            Task task = new Task(input);
            taskList.add(task);
            System.out.println("added: " + task.description);
        }
    }
}
