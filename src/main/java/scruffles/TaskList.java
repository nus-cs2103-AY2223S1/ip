package scruffles;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * This class is where the tasks are stored while the program is running
 *
 * @author Shamus Tan
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCount;

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCount = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    /**
     * Used to convert the current TaskList into a string format that can be easily read by the program after saving
     *
     * @returns the TaskList in the required string format for saving to file
     */
    public String saveToFile() {
        String output = "";
        for (Task task : tasks) {
            String textInput = "";
            String isDone = "O / ";
            if (task.isDone) {
                isDone = "X / ";
            }

            if (task instanceof Todo) {
                textInput = "T / " + isDone + task.taskName + "\n";
            } else if (task instanceof Deadline) {
                textInput = "D / " + isDone + task.taskName + " / " + ((Deadline) task).by + "\n";
            } else if (task instanceof Event) {
                textInput = "E / " + isDone + task.taskName + " / " + ((Event) task).at + " / "
                        + ((Event) task).startTime + " / " + ((Event) task).endTime + "\n";
            }
            output.concat(textInput);
        }
        return output;
    }

    /**
     * Adds the tasks into the TaskList
     *
     * @param str the input string of the Task
     * @param type the enumerated type of Task (TODO, DEADLINE, EVENT)
     */
    public void add(String str, Scruffles.Type type) {
        if (type.equals(Scruffles.Type.TODO)) {
            try {
                if (str.equals("todo") || str.equals("todo ")) {
                    throw new DescriptionEmptyException();
                }
                tasks.add(new Todo(str.replace("todo ", "")));
                taskCount++;
                System.out.println(String.format("woof! the task is added woof!\n" +
                                "%s\n" +
                                "you now have %d tasks in the list woof!",
                        tasks.get(taskCount - 1).toString(),
                        taskCount));
            } catch (DescriptionEmptyException e) {
                System.out.println(e.getMessage());
            }
        } else if (type.equals(Scruffles.Type.DEADLINE)) {
            try {
                if (str.equals("deadline") || str.equals("deadline ")) {
                    throw new DescriptionEmptyException();
                }
                String[] input = str.split("/by ");
                String name = input[0].replace("deadline", "");
                LocalDate date = LocalDate.parse(input[1]);
                tasks.add(new Deadline(name, date));
                taskCount++;
                System.out.println(String.format("woof! the task is added woof!\n" +
                                "%s\n" +
                                "you now have %d tasks in the list woof!",
                        tasks.get(taskCount - 1).toString(),
                        taskCount));
            } catch (DescriptionEmptyException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("grrrr >:( when is your deadline?? woof woof!");
            } catch (DateTimeParseException e) {
                System.out.println("grrrr >:( please input deadline in yyyy-mm-dd format woof woof!");
            }
        } else if (type.equals(Scruffles.Type.EVENT)) {
            try {
                if (str.equals("event") || str.equals("event ")) {
                    throw new DescriptionEmptyException();
                }
                String[] input = str.split("/at ");
                String name = input[0].replace("event", "");
                tasks.add(new Event(name, input[1]));
                taskCount++;
                System.out.println(String.format("Got it. I've added this task:\n" +
                                "%s\n" +
                                "you now have %d tasks in the list woof!",
                        tasks.get(taskCount - 1).toString(),
                        taskCount));
            } catch (DescriptionEmptyException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("grrrr >:( when is your event?? woof woof!");
            } catch (DateTimeParseException e) {
                System.out.println("grrrr >:( please input event date as 'yyyy-mm-dd from hh:mm to hh:mm' format" +
                        " woof woof!");
            }
        }
    }

    /**
     * Deletes the task of given number from the TaskList
     *
     * @param input the input string of the message
     */
    public void delete(String input) {
        try {
            if (input.equals("delete") || input.equals("delete ")) {
                throw new DescriptionEmptyException("grrrr >:( you need to delete something woof woof!");
            } else {
                int index = Integer.valueOf(input.replace("delete ", ""));
                if (index > tasks.size() || index <= 0) {
                    throw new OutOfBoundsException(index);
                } else {
                    Task t = tasks.remove(index - 1);
                    taskCount--;
                    String str = String.format("woof! the task is now deleted woof!\n" +
                                    "%s\n" +
                                    "you now have %d tasks in the list woof!",
                            t.toString(),
                            taskCount);
                    System.out.println(str);
                }
            }
        } catch (OutOfBoundsException | DescriptionEmptyException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("grrrr >:( you need to input an integer woof woof!");
        }
    }

    /**
     * Marks or unmarks the task of given number from the TaskList
     *
     * @param input the input string of the message
     */
    public void mark(String input) {
        try {
            if (input.equals("mark") || input.equals("mark ")) {
                throw new DescriptionEmptyException("grrrr >:( you need to mark something woof woof!");
            } else {
                String str = input.replace("mark ", "");
                int index = Integer.valueOf(str);
                if (index > tasks.size() || index <= 0) {
                    throw new OutOfBoundsException(index);
                } else {
                    tasks.get(index - 1).setDone();
                    System.out.println("woof! the task is now marked as done woof!\n"
                            + tasks.get(index - 1).toString());
                }
            }
        } catch (OutOfBoundsException | DescriptionEmptyException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("grrrr >:( you need to input an integer woof woof!");
        }
    }

    /**
     * Displays the list in the program
     *
     * @return the list of tasks as a string
     */
    public String list() {
        String output = "";
        if (tasks.isEmpty()) {
            output = "you have no tasks woof woof!";
        }
        for (int i = 0; i < tasks.size(); i++) {
            output += ((i + 1) + "." + tasks.get(i).toString() + "\n");
        }
        return output;
    }
}
