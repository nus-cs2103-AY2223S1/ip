public class ListFunction extends Function {

    public ListFunction(String function,boolean exit){
        super(function,exit);
    }

    @Override
    public void run(TaskList tasks,UITextBox textBox, Storage storage) throws CleverNotBotException{
        if (tasks.getSize() < 1) { textBox.chat("There is no task currently assigned.");
        }else {
            int counter = 1;
            StringBuilder op = new StringBuilder();
            for (Task task : tasks.getTaskList()) {
                op.append(counter++);
                op.append(".");
                op.append(task.toString());
                op.append("\n");
            }
            textBox.chat(op.toString());
        }
    }
}
