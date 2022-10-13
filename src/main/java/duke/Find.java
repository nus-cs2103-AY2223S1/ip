package duke;

import java.util.ArrayList;

public class Find extends Command {
    private String target;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> arrayList;

    public Find(String target, ArrayList<String> list) {
        this.target = target;
        this.arrayList = list;
    }

    @Override
    public void execute() {
        for (int k = 0; k < arrayList.size(); k++) {
            if (arrayList.get(k).contains(target)) {
                this.list.add(arrayList.get(k));
            }
        }
    }

    /**
     * Checks if the command should be added to the list.
     *
     * @return boolean
     */
    @Override
    public boolean addToList() {
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
