package duke.task;

import java.util.ArrayList;

public class Tags {
    private final ArrayList<Tag> tagsList;

    public Tags() {
        this.tagsList = new ArrayList<>();
    }

    public void addTags(Tag... tags) {
        Tag[] allTags = tags;
        for (int i = 0; i < allTags.length; i++) {
            tagsList.add(allTags[i]);
        }
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < tagsList.size(); i++) {
            result = result + tagsList.get(i) + " ";
        }
        return result;
    }

    public String toMemoryString() {
        String result = "";
        for (int i = 0; i < tagsList.size(); i++) {
            result = result + tagsList.get(i) + ", ";
        }
        return result;
    }
}
