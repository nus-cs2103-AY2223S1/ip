import java.util.Arrays;

public class AddFunctionToDo extends Function {

    public AddFunctionToDo(String function, boolean exit) {
        super(function, exit);
    }

    public void run(TaskList tasks, UITextBox textBox, Storage storage) throws CleverNotBotException {
        String[] desc = getFunction().split(" ");
        try {
            if (desc.length != 1) {
                //removes the function name "todo" eg. [todo,borrow,book] -> [borrow,book]
                String[] descOnly = Arrays.copyOfRange(desc, 1, desc.length);
                String joinDesc = String.join(" ", descOnly);
                Task newTask = new ToDo(joinDesc, false);
                tasks.addTask(newTask);
                storage.writeToFile(tasks.getTaskList());
                textBox.chat(String.format(
                        "Got it. I've added this task:" +
                                "\n  %s" +
                                "\nNow you have %d tasks in the list."
                        , newTask.toString(), tasks.getSize()));
            } else {
                throw new CleverNotBotException("Please fill in the description of ToDo!", textBox);
            }
        } catch(CleverNotBotException e){
            throw new CleverNotBotException("Please fill in the description of ToDo!", textBox);
        }

    }

}
