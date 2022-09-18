package duke;

public class Ui {

    public String getStartMessage() {
        return "I'm Karen. Can I see the manager???";
    }
    public String getMarkedMessage(Task task) {
        return "Oh you did a task. Congratulations.\n" + task;
    }
    
    public String getUnmarkedMessage(Task task) {
        return "Make up your mind!.\n" + task;
    }
    public String getFailureMessage() {
        return "What's that now??";
    }
    
    public String getEmptyTaskMessage() {
        return "Come back when you have a proper task. Waste of time";
    }

    public String getWrongIndexMessage() {
        return "Can you like count?" + "\n";
    }
    
    public String getTaskDeletedMessage(Task task, int size) {
        return "K. Removed your task:\n" + task + "\nNow you have " + size + " tasks.";
    }
    
    public String getTaskSnoozedMessage(Task task) {
        return "Snoozed your task by 1 day. Can you get anything done???\n" + task;
    }
    
    public String getCannotSnoozeMessage(){
        return "You cannot snooze a Todo, dumbass";
    }
    
    public String getAddedMessage(Task task, int size) {
        return "K. Added your task:\n" + task + "\nNow you have " + size + " tasks.";
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
        return "Whatever.\n";
    }
    
}
