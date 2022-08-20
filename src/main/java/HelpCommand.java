public class HelpCommand extends Command {
    @Override
    public void execute() {
        System.out.println("These are the commands I know.");
        for (Commands e : Commands.values()) {
            switch (e) {
                case bye:
                    System.out.println("Ends my service.");
                    break;
                case list:
                    System.out.println("Lists all the tasks I have been given to track.");
                    break;
                case help:
                    System.out.println("Lists all the commands I know.");
                    break;
                case mark:
                    System.out.println("Format: mark x, where x is an integer." +
                            "\nMarks the task that is index x on the list as done.");
                    break;
                case unmark:
                    System.out.println("Format: unmark x, where x is an integer." +
                            "\nMarks the task that is index x on the list as not done.");
                    break;
                case delete:
                    System.out.println("Format: delete x, where x is an integer." +
                            "\nMarks the task that is index x on the list as done.");
                    break;
                case todo:
                    System.out.println("Format: todo <task>" +
                            "\nI will add the <task> to the list of tasks.");
                    break;
                case deadline:
                    System.out.println("Format: todo <task> /by <time/date>" +
                            "\nI will add the <task> to the list of tasks." +
                            "\nThe <task> will also display its deadline at <time/date>.");
                    break;
                case event:
                    System.out.println("Format: todo <task> /at <time/date" +
                            "\nI will add the <task> to the list of tasks." +
                            "\nThe <task> will also display the <time/date> the task should be done.");
                    break;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
