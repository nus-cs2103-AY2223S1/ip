package duke;
public class Parser {
    String stringToParse;
    Parser(String stringToParse) {
        this.stringToParse = stringToParse;
    }

     Command parse() throws DukeUnknownWordException {
        if (isSubstringForDeleteCommand()){
            return new DeleteCommand();
        } else if (isSubstringForMarkCommand()){
            return new MarkCommand();
        } else if (isSubstringForUnmarkCommand()){
            return new UnmarkCommand();
        } else if (isSubstringForDeadlineCommand()){
            return new DeadlineCommand();
        } else if (isSubstringForEventCommand()) {
            return new EventCommand();
        } else if (isSubstringForTodoCommand()){
            return new TodoCommand();
        } else if (isSubstringForShowListCommand()) {
            return new ShowListCommand();
        } else if (isSubstringForFindCommand()) {
            return new FindCommand();
        }
        //} else if (isSubStringForExitCommand()){
          //  return new ExitCommand();
         else {
            throw new DukeUnknownWordException();
        }
    }

    boolean isSubStringForExitCommand() {
        return this.stringToParse.equals("bye");
    }
    boolean isSubstringForShowListCommand() {
        return this.stringToParse.equals("list");
    }
    boolean isSubstringForMarkCommand(){
       return this.stringToParse.length() > 5 && this.stringToParse.substring(0, 4).equals("mark");
    }

    boolean isSubstringForUnmarkCommand() {
        return this.stringToParse.length() > 7 && this.stringToParse.substring(0, 6).equals("unmark");
    }

    boolean isSubstringForDeadlineCommand() {
        return this.stringToParse.length() >= 8 && this.stringToParse.substring(0, 8).equals("deadline");
    }

    boolean isSubstringForEventCommand() {
        return this.stringToParse.length() >= 5 && this.stringToParse.substring(0, 5).equals("event");
    }

    boolean isSubstringForTodoCommand() {
        return this.stringToParse.length() >= 4 && this.stringToParse.substring(0, 4).equals("todo");
    }

    boolean isSubstringForDeleteCommand() {
        return this.stringToParse.length() > 7 && this.stringToParse.substring(0, 6).equals("delete");
    }

    boolean isSubstringForFindCommand() {
        return this.stringToParse.substring(0,4).equals("find");
    }
}

