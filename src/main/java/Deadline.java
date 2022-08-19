public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline addTask(String name, String by) {
        Deadline newDDL = new Deadline(name, by);
        System.out.println("       " + newDDL.printSelf());
        return newDDL;
    }

    @Override
    public String printSelf() {
        return "[D]" + super.printSelf() + " (by: " + this.by + ")";
    }

}
