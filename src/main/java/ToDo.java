import javax.swing.plaf.synth.SynthDesktopIconUI;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
        System.out.println("To do has been added\n" + this.toString());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
