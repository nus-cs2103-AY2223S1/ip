package seedu.duke.list;
import java.util.ArrayList;

/**
 * Class to represent list of items from given TaskList.
 * Items stored in the list are integer indices of the corresponding item on the list.
 * Doubtful abstraction but it is simple and works.
 */
public class FoundList extends ArrayList<Integer> {
    private TaskList list;

    /**
     * Constructor for FoundList. Relevant TaskList must be passed as param.
     * @param list
     */
    public FoundList(TaskList list) {
        super();
        this.list = list;
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            int num = this.get(i);
            str = str + num + ". " + this.list.get(i).toString() + "\n";
        }
        return str;
    }
}
