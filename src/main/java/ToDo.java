public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static ToDo addTask(String name) {
        ToDo newToDo = new ToDo(name);
        System.out.println("       " + newToDo.printSelf());
        return newToDo;
    }

    @Override
    public String printSelf() {
        return "[T]" + super.printSelf();
    }


}
