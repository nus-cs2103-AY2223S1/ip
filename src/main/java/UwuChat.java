public class UwuChat {
    protected TaskList userTaskList = new TaskList();

    public UwuChat(TaskList userTaskList) {
        this.userTaskList = userTaskList;
    }

    private void printFormattedChat(String uwuChat) {
        String horizontalLine ="-----------------------------------------------------";
        System.out.println(horizontalLine + uwuChat + "\n" + horizontalLine);
    }

    public void greetUsers() {
        String greetings = "\n\thellu!" +
                           "\n\ti am oo woo <:" +
                           "\n\thow can i be of service today?";

        printFormattedChat(greetings);
    }

    public void leaveChat() {
        String farewellWords = "\n\tgood work today!" +
                                "\n\thope to see you again soon~";

        printFormattedChat(farewellWords);
    }

    public void addTask(String userCmd) throws UwuException {
        Task task = null;

        if (userCmd.startsWith("todo")) {
            String[] taskData = userCmd.split(" ", 2);
            task = new ToDos(taskData[1]);
            userTaskList.add(task);
        }

        if (userCmd.startsWith("deadline")) {
            if (!userCmd.contains("/by ") || userCmd.trim().endsWith("/by")) {
                throw new IncorrectFormatException("Incorrect Format given for deadline task.");
            }

            if (userCmd.trim().endsWith("/by")) {
                throw new InvalidDateException("No date entered.");
            }

            int deadlineStart = userCmd.indexOf("/by ");
            int userCmdLen = userCmd.length();
            String description = userCmd.substring(8, deadlineStart);

            if (description.isBlank()) {
                throw new EmptyInputException("No task entered.");
            }

            String by = userCmd.substring(deadlineStart + 3, userCmdLen).trim();

            task = new Deadline(description, by);
            userTaskList.add(task);
        }

        if (userCmd.startsWith("event")) {
            if (!userCmd.contains("/at ") || userCmd.trim().endsWith("/at")) {
                throw new IncorrectFormatException("Incorrect Format given for event task.");
            }

            if (userCmd.trim().endsWith("/at")) {
                throw new InvalidDateException("No date entered.");
            }

            int eventStart = userCmd.indexOf("/at");
            int userCmdLen = userCmd.length();
            String description = userCmd.substring(5, eventStart);

            if (description.isBlank()) {
                throw new EmptyInputException("No task entered.");
            }

            String at = userCmd.substring(eventStart + 3, userCmdLen).trim();

            task = new Event(description, at);
            userTaskList.add(task);
        }

        if (task != null) {
            String addToList = "\n\too new task! ^^" +
                    "\n\t\tadded:\t" + task.toString() +
                    "\n\tyou have " + String.valueOf(userTaskList.size()) + " task(s) <:";
            printFormattedChat(addToList);
        } else {
            throw new NullTaskException("Null Task Exception.");
        }
    }

    public void listTasks() {
        printFormattedChat("\n\there are your tasks~ you've got this!" + userTaskList.taskListToString());
    }

    public void markTasks(String userCommand) throws UwuException {
        if (userCommand.startsWith("mark")) {
            userCommand = userCommand.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(userCommand) - 1;

            if (index >= userTaskList.size()) {
                throw new NullTaskException("No Such Task to be Marked.");
            }

            Task task = userTaskList.get(index);
            task.setIsDone(true);

            String markedAsDone = "\n\tyey! good job~ keep it up <3";
            printFormattedChat(markedAsDone + "\n\t\t" + task.toString());
        } else {
            userCommand = userCommand.replaceAll("[^0-9]", "");
            int index = Integer.parseInt(userCommand) - 1;

            if (index >= userTaskList.size()) {
                throw new NullTaskException("No Such Task to be Unmarked.");
            }

            Task task = userTaskList.get(index);
            task.setIsDone(false);

            String unmarked = "\n\tkeep going~";
            printFormattedChat(unmarked + "\n\t\t" + task.toString());
        }
    }

    public void deleteTask(String userCommand) throws UwuException {
        userCommand = userCommand.replaceAll("[^0-9]", "");
        int index = Integer.parseInt(userCommand) - 1;

        if (index >= userTaskList.size()) {
            throw new NullTaskException("No Such Task to be Deleted.");
        }

        Task task = userTaskList.remove(index);

        printFormattedChat("\n\tremoving this task from your list...\n\t\t" +
                task.toString() +
                "\n\ttask removed~! now you have " + String.valueOf(userTaskList.size()) + " task(s) <:");
    }

    public void unknownCommand() {
        printFormattedChat("\n\tsorry >< i don't know what that means TT");
    }

    public void emptyInput() {
        printFormattedChat("\n\tlooks like there isn't a task for me to add uwu" +
                "\n\tfeed me a task to get started~");
    }

    public void incorrectFormat() {
        printFormattedChat("\n\tplease enter the correct format for your task uwu");
    }

    public void nullTask() {
        printFormattedChat("\n\t no such task found uwu");
    }

    public void invalidDate() {
        printFormattedChat("\n\tplease enter a valid date, using the format: \n\t\t'yyyy-mm-dd HH:mm' \n" +
                "\n\tif you do not have a time, enter the date only~");
    }
}
