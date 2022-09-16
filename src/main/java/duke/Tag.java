package duke;

import java.util.ArrayList;

public class Tag extends Parser {
    private String tag;

    public Tag(ArrayList<String> arrayList, int num) {
        super(arrayList.get(num));
        this.tag = arrayList.get(num);
        arrayList.set(num, arrayList.get(num) + " #fun");
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

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "OK! I have tagged:\n" + this.tag + " as #fun";
    }
}
