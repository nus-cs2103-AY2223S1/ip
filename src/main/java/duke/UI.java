package duke;

public class UI {
    private static final String TEXT_ART = "\n" +
            "\t███████████████████████████████████\n" +
            "\t█▄─▄▄─█▄─▄███─▄▄─█─▄▄─█▄─▄▄─█▄─█─▄█\n" +
            "\t██─▄▄▄██─██▀█─██─█─██─██─▄▄▄██▄─▄██\n" +
            "\t▀▄▄▄▀▀▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▄▀▄▄▄▀▀▀▀▄▄▄▀▀";

    private static final String TOP_WINDOW = "╔════════════════════════════════════════════════════╗";
    private static final String BOTTOM_WINDOW = "╚════════════════════════════════════════════════════╝";

    private static final String GREETING = "Hi, I'm duke.Ploopy! Nice to meet you!\n\tWhats up?";
    private static final String FAREWELL = "Okay then, see ya later :)";

    private static final String COMPLETED_TASK = "Nice! You've completed this task. I'll mark it as done.";
    private static final String INCOMPLETE_TASK = "Alright this task has been marked as undone.";
    private static final String ADDED_TASK = "I've added this task to your list.\n\tHere you go: ";

    //Exceptions:
    private static final String NO_INPUT_MESSAGE = "You didn't say what I should do! (ಠ_ʖಠ)";
    private static final String EMPTY_COMMAND_MESSAGE = "What should I do with the ";
    private static final String NONSENSE_INPUT_MESSAGE = "I can't do that right now, sorry ┐(‘～`；)┌";
    private static final String IO_ERROR = "Something went wrong creating your files!";

    public void greeting() {
        System.out.println(messageFormatter(TEXT_ART));
        System.out.println(messageFormatter(GREETING));
    }


    public void bye() {
        System.out.println(messageFormatter(FAREWELL));
    }

    public void promptUser() {
        System.out.println(messageFormatter("What do you wanna do to your list?"));
    }

    public void addTaskMessage(Task task, int totalTasks) {
        String message = ADDED_TASK + task.toString() + "\n\tYou have " + totalTasks + " tasks in your list.";
        System.out.println(messageFormatter(message));
    }

    public void markTaskMessage(Task task) {
        System.out.println(messageFormatter(COMPLETED_TASK + "\n\t" + " " + task));
    }

    public void unmarkTaskMessage(Task task) {
        System.out.println(messageFormatter(INCOMPLETE_TASK + "\n\t" + " " + task));
    }

    public void deleteTaskMessage(Task task, int index) {
        String message = "Deleted: " + task + "\n\tYou have "
                + index+ " task(s) remaining.";
        System.out.println(messageFormatter(message));
    }

    public void createFilesMessage() {
        System.out.println(messageFormatter("Creating necessary files..."));
    }

    public void addingFilesMessage() {
        System.out.println(messageFormatter("Adding existing tasks..."));
    }

    public void exceptionMessage(String type) {
        switch (type) {
            case "nonsense":
                System.out.println(messageFormatter(NONSENSE_INPUT_MESSAGE));
                break;
            case "blank":
                System.out.println(messageFormatter(NO_INPUT_MESSAGE));
                break;
            case "IO":
                System.out.println(messageFormatter(IO_ERROR));
            default:
                System.out.println(messageFormatter(EMPTY_COMMAND_MESSAGE + type));
        }
    }

    private String messageFormatter(String input) {
        return TOP_WINDOW + "\n\t" + input + "\n" + BOTTOM_WINDOW;
    }

    public void showTopWindow() {
        System.out.println(TOP_WINDOW);
    }

    public void showBottomWindow() {
        System.out.println(BOTTOM_WINDOW);
    }

}
