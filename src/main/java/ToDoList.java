import java.util.ArrayList;

public class ToDoList {
    private ArrayList<Task> list = new ArrayList<>(100);

    /* Define constructor for to do list*/
    public ToDoList() {}

    /* Method for adding items to the list */
    public void add(String item) {
        Task task = new Task(item);
        this.list.add(task);
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
        for (int i = 1; i <= numOfElements; i++) {
            String curr = "      " + i + ". " + this.list.get(i - 1).toString() + "\n";
            res += curr;
        }
        res += "   --------------------------------------------------------------------------------";
        return res;
    }
}
