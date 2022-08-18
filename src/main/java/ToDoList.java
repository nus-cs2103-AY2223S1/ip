import java.util.ArrayList;

public class ToDoList {
    ArrayList<String> list = new ArrayList<>(100);

    /* Define constructor for to do list*/
    public ToDoList() {}

    /* Method for adding items to the list */
    public void add(String item) {
        this.list.add(item);
    }

    /* Method for printing items in the list */
    @Override
    public String toString() {
        int numOfElements = this.list.size();
        String res = "   --------------------------------------------------------------------------------\n";
        for (int i = 1; i <= numOfElements; i++) {
            String curr = "     " + i + ". " + this.list.get(i - 1) + "\n";
            res += curr;
        }
        res += "   --------------------------------------------------------------------------------";
        return res;
    }
}
