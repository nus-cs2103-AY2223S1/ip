import java.util.ArrayList;

public class TaskHandler {
    public static void add(String input, ArrayList<Task> taskList) {
        Task newTask = new Task("");
        try {
            if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                if (input.startsWith("todo")) {
                    if (!input.equals("todo")) {
                        String newInput = input.replace("todo", "");
                        Todo todoTask = new Todo(newInput);
                        taskList.add(todoTask);
                        newTask = todoTask;
                    } else {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                } else {
                    try {
                        String description = input.substring(0, input.toString().lastIndexOf("/") - 1);
                        String date = input.substring(input.lastIndexOf("/") + 4);

                        if (input.startsWith("deadline")) {
                                String newDescription = description.replace("deadline", "");
                                Deadline deadlineTask = new Deadline(newDescription, date);
                                taskList.add(deadlineTask);
                                newTask = deadlineTask;
                        } else if (input.startsWith("event")) {
                                String newDescription = description.replace("event", "");
                                Event eventTask = new Event(newDescription, date);
                                taskList.add(eventTask);
                                newTask = eventTask;
                        }
                    } catch (StringIndexOutOfBoundsException e) {
                        if (input.contains("deadline")) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty/missing.");
                        } else {
                            throw new DukeException("OOPS!!! The description of an event cannot be empty/missing.");
                        }
                    }
                }
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means.");
            }
            System.out.println("Got it. I've added this task: \n" +
                    "   " + newTask.toString() +
                    "\n Now you have " + taskList.size() +
                    (taskList.size() > 1 ? " tasks in your list." : " task in your list."));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }

}
