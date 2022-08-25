package duke;

import java.util.ArrayList;

public class Find extends Task {
    public String target;
    ArrayList<String> list = new ArrayList<>();

    public Find(String target, ArrayList<String> list) {
        super(target);
        this.target = target;
        for (int k = 0; k < list.size(); k++) {
            if (list.get(k).contains(target)) {
                this.list.add(list.get(k));
            }
        }
    }

    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
    @Override
    public boolean AddToList() {
        return false;
    }

    public String getList() {
        String list = "";
        for (int k = 1; k < this.list.size() + 1; k++) {
            list += k + "." + this.list.get(k - 1) + "\n";
        }
        return list;
    }

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Here are the matching tasks in your list:\n" + getList();
    }
}
