package chatbot.tasks;

/**
 * The class represents generic task like that
 * of the real world with completion status and task name
 * associated.
 */
public abstract class Task {
    private boolean isComplete = false;
    private String taskName;
    private String[] tags;

    public Task(String taskName, String[] tags) {
        this.taskName = taskName;
        this.tags = tags;
    }

    public void mark() {
        this.isComplete = true;
    }

    public void unmark() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        if (this.isComplete) {
            return "[X] " + taskName;
        } else {
            return "[ ] " + taskName;
        }
    }

    public String getTaskName() {
        return this.taskName;
    }

    public int getStatus() {
        return this.isComplete ? 1 : 0;
    }

    /**
     * The method returns a string representation of all the tags associated with the task
     * for data storage purpose.
     */
    public String saveTags() {
        if (this.tags.length > 0) {
            StringBuffer sb = new StringBuffer();
            System.out.println(tags.length);
            for (int i = 0; i < tags.length; i++) {
                sb.append(tags[i]);
                sb.append("#");
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }

        return null;
    }

    /**
     * The method should transform a task into the format it is to be saved
     * in storage.
     *
     * @return The String representation of the task to be saved.
     */
    public abstract String save();
}
