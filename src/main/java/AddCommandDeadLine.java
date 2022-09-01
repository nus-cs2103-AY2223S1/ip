public class AddCommandDeadLine extends Command {

    public AddCommandDeadLine(String commandName, boolean exit) {
        super(commandName, exit);
    }

    public void run(TaskList tasks, UI textBox, Storage storage) throws CleverNotBotException {
        String[] desc = getCommandName().split(" ");
        try {
            if (desc.length == 1) {
                throw new CleverNotBotException("Please fill in the description of Deadline!", textBox);
            } else if (!getCommandName().contains("/by")) {
                throw new CleverNotBotException("Please include a /by in your description of Deadline! ", textBox);
            } else {
                String searchWord = " /by";
                int start = "deadline ".length();
                int mid = getCommandName().indexOf(searchWord);
                //deadline desc /by datetime -> "desc"
                String commandName = getCommandName().substring(start, mid);
                String dateTime = getCommandName().substring(mid + searchWord.length() + 1); // to remove the space
                Deadline.validDateTime(dateTime);
                Task newTask = new Deadline(commandName, false, dateTime);
                tasks.addTask(newTask);
                storage.writeToFile(tasks.getTaskList());
                textBox.chat(String.format(
                        "Got it. I've added this task:" +
                                "\n  %s" +
                                "\nNow you have %d tasks in the list."
                        , newTask.toString(), tasks.getSize()));
            }
        } catch (CleverNotBotException e){
            throw new CleverNotBotException("Deadline description must not be empty or must contain /by!", textBox);
        } catch (Exception ex){
            throw new CleverNotBotException("Incorrect date format! Please enter DD-MM-YYYY HH:mm" +
                    "\nFor example, 22-09-2022 19:40", textBox);
        }

    }


}
