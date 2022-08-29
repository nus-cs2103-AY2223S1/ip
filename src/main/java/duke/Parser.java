package duke;

/**
 * Used to parse user's commands entered and execute the command
 */
public class Parser {
    private Storage s;
    private Ui ui;
    private TaskList tasks;

    public Parser(Storage s, Ui ui, TaskList taskList) {
        this.s = s;
        this.ui = ui;
        this.tasks = taskList;
    }

    public void parse(String command) throws DukeException {
        boolean taskAdded = false;
        if (command.equals("list")) {
            for (int i = 0; i < tasks.getSize(); i++) {
                Task nextTask = tasks.get(i);
                System.out.println((i + 1) + ". " + nextTask.toString());
            }

        //else if command is done
        } else if (command.startsWith("done")) {
            int completedIndex = Character.getNumericValue(command.charAt(5));
            Task currentTask = tasks.get(completedIndex - 1);
            currentTask.setComplete(true);
            System.out.println("Nice! I've marked this task as done: [X] " + currentTask.getTaskName());
            s.writeFile(tasks);

        //delete task
        } else if (command.startsWith("delete")) {
            int deleteIndex = Character.getNumericValue(command.charAt(7));
            Task deletedTask = tasks.get(deleteIndex - 1);
            tasks.delete(deleteIndex - 1);
            System.out.println("Noted. I've removed this task:" + deletedTask.toString()
                    + "\nNow you have " +  tasks.getSize() +  " tasks in the list.");
            //save the tasks in hard disk
            s.writeFile(tasks);
        } else if (command.startsWith("find")) {
            System.out.println("Here are the matching tasks in your list");
            String keyword = command.substring(5);
            for (int i = 0; i < tasks.getSize(); i++) {
                if(tasks.get(i).toString().contains(keyword)) {
                    System.out.println(tasks.get(i).toString());
                }
            }
        }
        else {
            //Add a todo
            if (command.startsWith("todo")) {
                try {
                    if (command.length() == 4) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task newTask = new Todo(command, false);
                    tasks.add(newTask);
                    taskAdded = true;
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            //event
            } else if (command.startsWith("event")) {
                try {
                    if (command.length() == 5) {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    }
                    Task newTask = new Event(command, false);
                    tasks.add(newTask);
                    taskAdded = true;
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            //deadline
            } else if (command.startsWith("deadline")) {
                try {
                    if (command.length() == 8) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    Task newTask = new Deadline(command, false);
                    tasks.add(newTask);
                    taskAdded = true;
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new DukeException("Sorry, I don't know what that means");
            }
        }
        if (taskAdded) {
            Task addedTask = tasks.get(tasks.getSize() - 1);
            System.out.println("Got it. I've added this task: \n" + addedTask.toString() +
                    "\nNow you have " +  tasks.getSize() +  " tasks in the list.");
            //save the tasks in hard disk
            s.writeFile(tasks);
        }
    }
}
