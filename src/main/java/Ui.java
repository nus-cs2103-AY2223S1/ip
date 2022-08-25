public class Ui {

    static String divider = "____________________________________________________________";

    public void sendStartMessage() {
        System.out.println("Hello I'm Karen. What do you want??\n" + divider);
    }
    public void sendMarkedMessage(Task task) {
        System.out.println(divider);
        System.out.println("Oh you did a task. Congratulations.\n" + task + "\n" + divider);
    }
    
    public void sendUnmarkedMessage(Task task) {
        System.out.println(divider);
        System.out.println("Hmm make up your mind maybe??.\n" + task + "\n" + divider);
    }
    public void sendFailureMessage() {
        System.out.println(divider);
        System.out.println("What's that??");
        System.out.println(divider);
    }
    
    public void sendEmptyTaskMessage() {
        System.out.println(divider);
        System.out.println("Empty task? Are you kidding me??");
        System.out.println(divider);
    }

    public void sendWrongIndexMessage() {
        System.out.println(divider);
        System.out.println("Umm can you count?" + "\n" + divider);
    }
    
    public void sendTaskDeletedMessage(Task task, int size) {
        System.out.println(divider);
        System.out.println("K. Removed your task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks.");
        System.out.println(divider);
    }
    
    public void sendAddedMessage(Task task, int size) {
        System.out.println(divider);
        System.out.println("K. Added your task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks.");
        System.out.println(divider);
    }
    
    public void sendList(TaskList tl) {
        System.out.println(divider);
        for (int i = 0; i < tl.size(); i++) {
            Task taskI = tl.get(i);
            System.out.println(i+1 + ". " + taskI);
        }
        System.out.println(divider);
    }
    
    public void sendByeMessage() {
        System.out.println(divider);
        System.out.println("Hmm kay...\n" + divider);
    }
    
}
