public class Parser {
    public enum RequestType {
        DONE, ONGOING, TODO, EVENT, DEADLINE, DELETE, LIST, EXIT
    }
    public static RequestType checkRequest(String request) throws DukeException {
        if (request.equals("bye")) {
            return RequestType.EXIT;
        } else if (request.equals("list")) {
            return RequestType.LIST;
        } else if (request.matches("mark \\d+")) {
            return RequestType.DONE;
        } else if (request.matches("unmark \\d+")) {
            return RequestType.ONGOING;
        } else if (request.matches("delete \\d+")) {
            return RequestType.DELETE;
        } else if (request.matches("(?i)^(todo)(.*)")) {
            return RequestType.TODO;
        } else if (request.matches("(?i)^(deadline)(.*)")) {
            return RequestType.DEADLINE;
        } else if (request.matches("(?i)^(event)(.*)")) {
            return RequestType.EVENT;
        } else {
            throw new InvalidInputException();
        }
    }

    public static int getDeleteIndex(String request, TaskList taskList) throws DukeException {
        int index = Integer.parseInt(request.substring(7));
        if (index < 1 || index > taskList.getSize()) {
            throw new InvalidIndexException();
        } else {
            return index - 1;
        }
    }

    public static int getMarkIndex(String request, TaskList taskList) throws DukeException {
        int index = Integer.parseInt(request.substring(5));
        if (index < 1 || index > taskList.getSize()) {
            throw new InvalidIndexException();
        } else {
            return index - 1;
        }
    }

    public static int getUnMarkIndex(String request, TaskList taskList) throws DukeException {
        int index = Integer.parseInt(request.substring(7));
        if (index < 1 || index > taskList.getSize()) {
            throw new InvalidIndexException();
        } else {
            return index - 1;
        }
    }

    public static String todoTask(String request) throws DukeException {
        String[] rq = request.split(" ", 2);
        if (rq.length < 2 || rq[1].trim().equals("")) {
            throw new InvalidToDoException();
        } else {
            return rq[1];
        }
    }

    public static String[] deadlineTask(String request) throws DukeException {
        if (request.matches("(?i)^deadline\\s.+\\s\\/(by)\\s.+")) {
            String[] deadline = request.substring(9).split("\\/(by)\\s", 2);
            if (DateConverter.isValidDate(deadline[1])) {
                return deadline;
            } else {
                throw new InvalidDeadlineException("Hmm, You need to use yyyy-mm-dd for date format.");
            }
        } else {
            throw new InvalidDeadlineException();
        }
    }

    public static String[] eventTask(String request) throws DukeException {
        if (request.matches("(?i)^event\\s.+\\s\\/(at)\\s.+")) {
            return request.substring(6).split("\\/(at)\\s", 2);
        } else {
            throw new InvalidEventException();
        }
    }

    public static Command parse(String userInput, TaskList taskList) throws DukeException{
        RequestType rqType = checkRequest(userInput);
        switch (rqType) {
            case LIST:
                return new ListCommand();
            case DONE:
                return new DoneCommand(getMarkIndex(userInput, taskList));
            case ONGOING:
                return new OnGoingCommand(getUnMarkIndex(userInput, taskList));
            case DELETE:
                return new DeleteCommand(getDeleteIndex(userInput, taskList));
            case TODO:
                return new AddCommand(new ToDo(todoTask(userInput)));
            case DEADLINE:
                String[] deadline = deadlineTask(userInput);
                return new AddCommand(new Deadline(deadline[0], deadline[1]));
            case EVENT:
                String[] event = eventTask(userInput);
                return new AddCommand(new Event(event[0], event[1]));
            default:
                return new ExitCommand();
        }
    }
}
