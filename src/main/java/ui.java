public class ui {
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String INDENTATION = "     ";

    public static void showError(Exception e) {
        System.out.println(LINE_DIVIDER);
        System.out.println(e);
        System.out.println(LINE_DIVIDER);
    }

    public static void printMessages(String[] messages) {
        System.out.println(LINE_DIVIDER);
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
        System.out.println(LINE_DIVIDER);
    }

    public static void printAddedTask(Task task, TaskList taskList) {
        printMessages(new String[] {
                "Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %d tasks in the list.", taskList.size())
        });
    }

}
