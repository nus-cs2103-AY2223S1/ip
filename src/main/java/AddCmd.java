public class AddCmd extends Command {

    public AddCmd (String name){
        super(name);
    }

    @Override
    public void execute(String name, Task[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                tasks[i] = new Task(name);
                break;
            }
        }
        System.out.println("added: " + getCmd());
    }
}
