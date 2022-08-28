public class Ui {

    private static final String line = "Dino:\n";

    public Ui() {
        System.out.println("               __\n" +
                "              / _)\n" +
                "     _.----._/ /\n" +
                "    /         /\n" +
                " __/ (  | (  |\n" +
                "/__.-'|_|--|_|\n");

        System.out.println(line
                + "\tHello! I'm Dino\n"
                + "\tWhat can I do for you?\n");
    }

    public void showLoadingError() {
        System.out.println("RAWR! Error loading previous tasks.");
    }

    public void showLine() {
        System.out.print(line);
    }

    public void exit() {
        System.out.println("\tBye bye. Hope to see you again soon!");
    }

    public void emptyDescription() throws DukeException {
        throw new DukeException("RAWR! Empty description.");
    }

    public void invalidTask() throws DukeException {
        throw new DukeException("RAWR! Invalid task.");
    }

    public void missingDate() throws DukeException {
        throw new DukeException("RAWR! Missing date.");
    }

    public void complete(Task myTask) {
        System.out.println("\tHooray! You have completed this task:\n\t" + myTask);
    }

    public void incomplete(Task myTask) {
        System.out.println("\tOh no! You have more things to complete:\n\t" + myTask);
    }

    public void newLine() {
        System.out.print("\n");
    }

    public void add(TaskList taskList, Task task) {
        System.out.println("\tadded: " + task);
        System.out.println("\tYou have " + taskList.size() + " task" + (taskList.size() > 1 ? "s!" : "!"));
    }

}
