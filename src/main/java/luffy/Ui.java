package luffy;

public class Ui {
    public void printDivider() {
        System.out.println("------------------------------------------------------");
    }

    public void printWelcome() {
        String logo = "██╗░░░░░██╗░░░██╗███████╗███████╗██╗░░░██╗\n"
                + "██║░░░░░██║░░░██║██╔════╝██╔════╝╚██╗░██╔╝\n"
                + "██║░░░░░██║░░░██║█████╗░░█████╗░░░╚████╔╝░\n"
                + "██║░░░░░██║░░░██║██╔══╝░░██╔══╝░░░░╚██╔╝░░\n"
                + "███████╗╚██████╔╝██║░░░░░██║░░░░░░░░██║░░░\n"
                + "╚══════╝░╚═════╝░╚═╝░░░░░╚═╝░░░░░░░░╚═╝░░░";
        printDivider();
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        printDivider();
    }

    public void showLoadingError() {
        System.out.println("☹ OOPS!!! Something went wrong loading the save file!");
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println("☹ OOPS!!! " + errorMessage);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printTaskListStatus(TaskList tasks) {
        if (tasks.getSize() > 1) {
            System.out.println("Now you have " + tasks.getSize() + " tasks in the list.");
        } else {
            System.out.println("Now you have " + tasks.getSize() + " task in the list.");
        }
    }
}
