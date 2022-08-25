public class Ui {

    public static void welcome() {
        String result = "\tHello! I am Bob the Bot, your friendly task manager! \uD83D\uDE0A\n";
        result += "\tWhen using me, please stick to the following commands:\n" +
                "\t\t1. todo - for items that you have to do\n" +
                "\t\t2. deadline - for items which have an upcoming deadline\n" +
                "\t\t3. event - for events with a date and time\n" +
                "\n" +
                "\t\t4. mark - to mark an event as done\n" +
                "\t\t5. unmark - to mark an event as undone\n" +
                "\t\t6. delete - to delete an event\n" +
                "\t\t7. list - to view all the events on your todo list\n" +
                "\t\t8. bye - to wish me a (temporary) farewell";
        formatMessage(result);
    }

    public static void goodbye(ToDoList list) {
        String result = "\tBye! Hope to see you again soon! ";
        String haveMoreTasks = "You still have " + list.getLength() + (list.getLength() == 1 ? " task" : " tasks")
                + " to do! \uD83D\uDE0A";
        String completedAllTasks = "I'm so happy that you've completed all your tasks! \n\tCome back soon " +
                "if you want to accomplish more things! \uD83D\uDE0A";

        formatMessage(list.getLength() == 0 ? result + completedAllTasks : result + haveMoreTasks);
    }

    public static void formatMessage(String s) {
        String result = "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n"
                +
                s + "\n" +
                "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";
        System.out.println(result);
    }

    public static void printErrorMessage(String s) {
        String result = "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n"
                +
                s + "\n" +
                "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";
        System.err.println(result);
    }

    public static void taskAddedMessage(Task t, ToDoList list) {
        String toPrint = "\tGot it. I've added this task: \n";
        toPrint += "\t" + t.toString() + "\n";
        toPrint += "\tYou now have " + list.getLength() + (list.getLength() == 1 ? " task" : " tasks")
                + " in the list.";
        formatMessage(toPrint);
    }

    public static void taskDeletedMessage(int index, ToDoList list) {
        String toPrint = "\tGot it. I've removed this task: \n";
        toPrint += "\t\t" + list.getTask(index).toString() + "\n";
        toPrint += "\tYou now have " + (list.getLength() - 1) + (list.getLength() - 1 == 1 ? " task" : " tasks")
                + " in the list.";
        formatMessage(toPrint);
    }

    /* takes in 0 index */
    public static void markItemDoneMessage(ToDoList list, int index) {
        String toPrint = "\tGOOD JOB! I'm marking this task as done: \n";
        toPrint += "\t" + list.getTask(index).toString();
        formatMessage(toPrint);
    }

    public static void markItemUndoneMessage(ToDoList list, int index) {
        String toPrint = "\tIt's sad that you thought you finished your work but didnt.\n";
        toPrint += "\t" + "But alright, marking this task as undone: \n";
        toPrint += "\t" + list.getTask(index).toString();
        formatMessage(toPrint);
    }

    public static void listMessage(ToDoList list) {
        if (list.getLength() == 0) {
            String toPrint = "\tYAY! There are no items in your list!";
            formatMessage(toPrint);
        } else {
            formatMessage(list.toString());
        }
    }
}
