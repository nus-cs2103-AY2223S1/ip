package duke.data.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class Task implements Serializable {

    // Tags are represented by a "#"
    // e.g. "#school"
    private static final Pattern tagRegexPattern = Pattern.compile("(#(\\w+))");
    // A collection of tags without prefix "#"
    protected final List<String> tags;
    protected String title;
    protected boolean isMarked;

    /**
     * Constructor for Task class
     *
     * @param title title of the task
     */
    Task(String title) {
        this.title = title;
        this.isMarked = false;

        this.tags = new ArrayList<>();
        extractTags();
    }

    /**
     * Extract out all tags from the task title
     */
    private void extractTags() {
        Matcher matcher = tagRegexPattern.matcher(this.title);
        while (matcher.find()) {
            // 1st capture group: #tag
            // 2nd capture group: tag
            tags.add(matcher.group(2));
        }
        title = title.replaceAll(tagRegexPattern.toString(), "")
                     .replaceAll("\\s\\s", " ") // handle extra whitespace when tag is in middle
                     .trim(); // handle extra whitespace when tag is at the start/end
    }

    /**
     * Mark a task as completed
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Mark a task as uncompleted
     */
    public void unmark() {
        this.isMarked = false;
    }

    public boolean containsKeyword(String keyword) {
        return this.title.contains(keyword);
    }

    @Override
    public String toString() {
        String tagsStr = tags.isEmpty() ? ""
            : String.format("(%s)",
                tags.stream().map(tag -> "#" + tag).collect(Collectors.joining(", ")));
        return String.format("[%c]%s %s", this.isMarked ? 'X' : ' ', tagsStr, title);
    }
}
