package duke;

import java.util.ArrayList;

public class TagList {
    private final ArrayList<Tag> tagList = new ArrayList<>();

    public Tag getTagByName(String name) {
        for (Tag tag : tagList) {
            if (name.equals(tag.getTagName())) {
                return tag;
            }
        }

        return null;
    }

    public Tag addTag(String name) {
        Tag tag = new Tag(name);
        tagList.add(tag);
        return tag;
    }

    public boolean checkTagExist(String name) {
        for (Tag tag : tagList) {
            if (name.equals(tag.getTagName())) {
                return true;
            }
        }

        return false;
    }
}
