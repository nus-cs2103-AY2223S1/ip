package duke;

import java.util.ArrayList;

public class Tag extends Parser {
    private String tag;
    private String content;

    public Tag(ArrayList<String> arrayList, int num, String content) {
        super(arrayList.get(num));
        this.tag = arrayList.get(num);
        this.content = content;
        arrayList.set(num, arrayList.get(num) + " #" + content);
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

    /**
     * Returns a String representation of the command.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "OK! I have tagged:\n" + this.tag + " as #" + content;
    }
}
