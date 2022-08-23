package duke.task;

import duke.Parser;
import duke.exception.ReadAttributeException;

import java.util.ArrayList;

public class Todo extends Task {
    protected static final String SYMBOL = "T";

    protected Todo(String name) {
        super(name);
    }

    public static Todo parseTodo(String formattedString) {
        ArrayList<String> attributes = Parser.separateAttributes(formattedString);
        if (attributes.size() < 3) {
            throw new ReadAttributeException("Todo", formattedString, "Number of attributes less than 3");
        }
        Todo result = todo(attributes.get(2));
        if (convertIntToBool(Integer.parseInt(attributes.get(1))) == true) {
            result.markAsDone();
        }
        return result;
    }

    @Override
    public String toString() {
        return "[" + this.SYMBOL + "]" + super.toString();
    }

    @Override
    public String toFormattedString() {
        return Parser.combineAttributes(this.SYMBOL,
                Integer.toString(convertBoolToInt(this.getIsDone())),
                this.getName());
    }
}
