package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the job to be done, and whether it is done
 *
 * @author eugeneleong
 * @version 1.0
 */

public class Task {
    protected String action;
    protected boolean isDone;
    protected List<String> tags; // for C-Tagging

    /**
     * Constructor for Task
     * @param action description of task
     */
    public Task(String action) {
        this.action = action;
        this.isDone = false;
        this.tags = new ArrayList<>();
    }

    /**
     * The job has been done
     */
    public void markIsDone() {
        this.isDone = true;
    }

    /**
     * Uh-oh, the job has not been done
     */
    public void unmarkIsDone() {
        this.isDone = false;
    }

    /**
     * Take in tag to be reflected and saves into the list of current tags
     * @param tag input tag
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    /**
     * Removes the most recently added tag
     */
    public String undoTag() {
        if (this.tags.size() > 0) {
            return tags.remove(tags.size() - 1);
        }
        return null;
    }

    /**
     * Returns the action description
     * @return the description of the action
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Returns the action status
     * @return the status of the action
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the list of tags
     * @return list of tags
     */
    public List<String> getTags() {
        return this.tags;
    }

    /**
     * Returns the generic description of a task
     * @return [marked?] description + all its tags
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[" + this.getStatusIcon() + "] " + this.action);
        for (String insideTag : this.tags) {
            str.append(" #").append(insideTag);
        }
        return str.toString();
    }

}
