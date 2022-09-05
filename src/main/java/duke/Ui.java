package duke;

public class Ui {

    public String getStartMessage() {
        return "Hello I'm Karen. Can I see the manager???";
    }
    public String getMarkedMessage(Task task) {
        return "Oh you did a task. Congratulations.\n" + task;
    }
    
    public String getUnmarkedMessage(Task task) {
        return "Hmm make up your mind maybe??.\n" + task;
    }
    public String getFailureMessage() {
        return "What's that??";
    }
    
    public String getEmptyTaskMessage() {
        return "Empty task? Are you kidding me??";
    }

    public String getWrongIndexMessage() {
        return "Umm can you count?" + "\n";
    }
    
    public String getTaskDeletedMessage(Task task, int size) {
        return "K. Removed your task:" + task + "Now you have " + size + " tasks.";
    }
    
    public String getAddedMessage(Task task, int size) {
        return "K. Added your task:" + task + "Now you have " + size + " tasks.";
    }
    
    public String getList(TaskList tl) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tl.size(); i++) {
            Task taskI = tl.get(i);
            result.append(i+1).append(". ").append(taskI);
            if (i != tl.size() - 1) {
                result.append("\n");
            }
        }
        return result.toString();
    }
    
    public String getByeMessage() {
        return "Hmm kay...\n";
    }
    
}
