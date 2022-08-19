import java.util.ArrayList;

class ChatBot {

    private String name;
    private final String line = "------------------------------" +
            "----------------------------------";
    private ArrayList<Task> tasks;

    ChatBot(String name) {

        this.name = name;
        this.tasks = new ArrayList<Task>();

    }

    public void greet() {

        System.out.println(line + "\n\t Hello I'm " + name + "!!\n" +
                "What do you wanna chat about today?\n" + line);
    }

    public void addTask(Task task) {

        this.tasks.add(task);
        System.out.println(line + "\n\tGot it. I just added the " +
                "task:\n\t\t" + task + "\n\tNow you have " +
                "" + this.tasks.size() + " tasks in the list\n" + line);
    }

    public void printTasks() {

        System.out.println(line);

        for(int i = 0; i < this.tasks.size(); i++) {

            System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i));
        }
        System.out.println(line);
    }

    public void markDone(int index) {

        this.tasks.get(index).done(true);
        System.out.println(line + "\n\tExcellent! I have marked " +
                "the task as done:\n\t" + this.tasks.get(index) + "\n" + line);
    }

    public void markUndone(int index) {

        this.tasks.get(index).done(false);
        System.out.println(line + "\n\tNoted! I have marked " +
                "the task as not done yet:\n\t"
                + this.tasks.get(index) + "\n" + line);
    }

    public void delete(int index) {

        System.out.println(line + "\n\tNoted. I've remove this task:" +
                this.tasks.get(index));
        this.tasks.remove(index);
        System.out.println("\t Now you have " + this.tasks.size() + " " +
                "tasks in the list.\n" + line);
    }

    public void echo(String input) {

        System.out.println(line + "\n\tYou just said " + input + "\n" + line);
    }

    public void bye() {

        System.out.println(line + "\n\t Bye. Looking forward to chating " +
                "with you soon again!\n" + line);
    }

}
