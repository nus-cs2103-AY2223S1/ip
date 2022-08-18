import java.util.Scanner;

public class ToDo extends Task {
    
    public ToDo(Scanner options) throws NoDescription {
        if (options.hasNext()) {
            String description = options.nextLine().substring(1);
            super.describe(description);
            super.unmark();
            System.out.println("CREATED TASK: " + description);
        } else {
            throw new NoDescription();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
