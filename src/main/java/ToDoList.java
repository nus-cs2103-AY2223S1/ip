import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list = new ArrayList<>(100);

    /* Define constructor for to do list*/
    public ToDoList() {}

    /* Method for adding items to the list */
    public void addTask(String command) throws BobException {
        if (command.startsWith("todo")) {
            command = command.replace("todo", "");
            if (command.length() == 0) {
                throw new BobException(
                        "\n   --------------------------------------------------------------------------------\n" +
                                "     The description of deadline cannot be empty!\n" +
                                "     You can't possibly just do nothing... Right? Right? Guys I'm RIGHT right?\n" +
                                "   --------------------------------------------------------------------------------"
                );
            } else {
                Task todo = new Todo(command);
                list.add(todo);

                System.out.println(
                        "   --------------------------------------------------------------------------------\n" +
                                "     Got it. I've added this task: \n" +
                                "       " + todo.toString() + "\n" +
                                "     You now have " + list.size() + " tasks in the list.\n" +
                                "   --------------------------------------------------------------------------------"
                );
            }
        } else if (command.startsWith("deadline")) {
            try {
                command = command.replace("deadline ", "");
                if (command.length() == 0) {
                    throw new BobException(
                            "\n   --------------------------------------------------------------------------------\n" +
                                    "     The description of deadline cannot be empty!\n" +
                                    "     You need to finish your tasks eventually... Right? Right?\n" +
                                    "   --------------------------------------------------------------------------------"
                    );
                } else {
                    String[] deadline = command.split(" /by ");
                    Task task = new Deadline(deadline[0], deadline[1]);
                    list.add(task);

                    System.out.println(
                            "   --------------------------------------------------------------------------------\n" +
                                    "     Got it. I've added this task: \n" +
                                    "       " + task.toString() + "\n" +
                                    "     You now have " + list.size() + " tasks in the list.\n" +
                                    "   --------------------------------------------------------------------------------"
                    );
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BobException(
                        "\n   --------------------------------------------------------------------------------\n" +
                        "       Set a deadline for your task!\n" +
                                "   --------------------------------------------------------------------------------"
                );
            }
        } else if (command.startsWith("event")) {
            try {
                command = command.replace("event ", "");
                if (command.length() == 0) {
                    throw new BobException(
                            "   --------------------------------------------------------------------------------\n" +
                                    "     The description of event cannot be empty!\n" +
                                    "     It's impossible to go for something that does not exist...\n" +
                                    "   --------------------------------------------------------------------------------"
                    );
                } else {
                    String[] event = command.split(" /at ");
                    Task task = new Event(event[0], event[1]);
                    list.add(task);

                    System.out.println(
                            "   --------------------------------------------------------------------------------\n" +
                                    "     Got it. I've added this task: \n" +
                                    "       " + task.toString() + "\n" +
                                    "     You now have " + list.size() + " tasks in the list.\n" +
                                    "   --------------------------------------------------------------------------------"
                    );
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new BobException(
                    "\n   --------------------------------------------------------------------------------\n" +
                            "       Please set a date for your event!\n" +
                            "   --------------------------------------------------------------------------------"
                );
            }
        } else {
            throw new BobException(
                    "\n   --------------------------------------------------------------------------------\n" +
                            "     Deepest apologies, I am a mere automated bot.\n" +
                            "     Please stick to input that starts with 'todo', 'deadline' or 'event'! \n" +
                            "   --------------------------------------------------------------------------------"
            );
        }
    }

    /* Method for deleting a specific event */
    public void deleteTask(int index) {
        System.out.println(
                "   --------------------------------------------------------------------------------\n" +
                        "     Got it. I've removed this task: \n" +
                        "       " + this.list.get(index).toString() + "\n" +
                        "     You now have " + (list.size() - 1) + " tasks in the list.\n" +
                        "   --------------------------------------------------------------------------------"
        );
        this.list.remove(index - 1);
    }

    /* Method to mark a certain item in the list as done */
    public void markItemDone(int index) {
        this.list.get(index).markDone();

        System.out.println(
                "   --------------------------------------------------------------------------------\n" +
                        "     GOOD JOB! I'm making this task as done: \n" +
                        "       " + this.list.get(index).toString() + "\n" +
                        "   --------------------------------------------------------------------------------"
        );
    }

    /* Method to mark a certain item in the list as undone */
    public void markItemUndone(int index) {
        this.list.get(index).markUndone();

        System.out.println(
                "   --------------------------------------------------------------------------------\n" +
                        "     GOOD JOB! But it would be even better if you got this done: \n" +
                        "       " + this.list.get(index).toString() + "\n" +
                        "   --------------------------------------------------------------------------------"
        );
    }

    /* Method for printing items in the list */
    @Override
    public String toString() {
        int numOfElements = this.list.size();
        String res = "   --------------------------------------------------------------------------------\n";
        res += "      Here are your tasks:\n";
        for (int i = 1; i <= numOfElements; i++) {
            String curr = "      " + i + ". " + this.list.get(i - 1).toString() + "\n";
            res += curr;
        }
        res += "   --------------------------------------------------------------------------------";
        return res;
    }
}
