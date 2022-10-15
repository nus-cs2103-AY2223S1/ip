package duke;

import java.util.ArrayList;

/**
 * Represents the tag command.
 */
public class Tag extends Command {
    private String tag;
    private String content;
    private int num;
    private ArrayList<String> arrayList;

    /**
     * Creates an object of Tag.
     *
     * @param arrayList
     * @param num
     * @param content
     */
    public Tag(ArrayList<String> arrayList, int num, String content) {
        this.tag = arrayList.get(num);
        this.content = content;
        this.num = num;
        this.arrayList = arrayList;
    }

    @Override
    public void execute() {
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
