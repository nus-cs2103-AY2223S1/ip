import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list = new ArrayList<>(100);

    /* Define constructor for to do list*/
    public ToDoList() {}

    /* Method for adding items to the list */
    public void addTask(String command) {
        if (command.startsWith("todo")) {
            command = command.replace("todo ", "");
            Task todo = new Todo(command);
            list.add(todo);

            System.out.println(
                    "   --------------------------------------------------------------------------------\n" +
                            "     Got it. I've added this task: \n" +
                            "       " + todo.toString() + "\n" +
                            "     You now have " + list.size() + " tasks in the list.\n" +
                            "   --------------------------------------------------------------------------------"
            );
        } else if (command.startsWith("deadline")) {
            command = command.replace("deadline ", "");
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
        } else if (command.startsWith("event")) {
            command = command.replace("event ", "");
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
