import java.util.Scanner;

public class ToDo extends Task {

	public ToDo(Scanner options) throws MissingOptions {
        super(options);
	}

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    
}
