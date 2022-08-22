public class Decoder {

    private static Task makeTask(String word, String date, char tag) {
        Task newTask;
        if (tag == 'D') {
            newTask = new Deadline(word, date);
        } else if (tag == 'E') {
            newTask = new Event(word, date);
        } else {
            newTask = new Todo(word);
        }
        return newTask;
    }
    public static Task handleTasks(String word) throws DukeException{
        String[] splitted = word.split(" ", 2);
        if (splitted.length < 2) {
            throw new EmptyDescException(splitted[0]);
        }

        if (splitted[0].equals("todo")) {
            return makeTask(splitted[1], null, 'T');
        } else {
            String[] stringAndDate;
            if (splitted[0].equals("deadline")) {
                stringAndDate = splitted[1].split("/by");
            } else {
                stringAndDate = splitted[1].split("/at");
            }

            if (stringAndDate.length < 2) {
                throw new BadFormatException("incorrect format", splitted[0]);
            }
            if (splitted[0].equals("deadline")) {
                return makeTask(stringAndDate[0], stringAndDate[1], 'D');
            } else {
                return makeTask(stringAndDate[0], stringAndDate[1], 'E');
            }
        }
    }
    public static void handleDelete(String word, TasksManager tasksManager) throws DukeException {
        String[] deleteTasks = word.split(" ");

        if (deleteTasks.length != 2) {
            throw new BadFormatException("failed delete", "delete");
        }
        if (!isValidNum(deleteTasks[1])) {
            throw new DukeException("invalid task");
        }
        int taskNo = Integer.parseInt(deleteTasks[1]);
        tasksManager.deleteTask(taskNo);
    }

    public static void handleDone(String word, TasksManager tasksManager) throws DukeException {
        String[] doneTasks = word.split(" ");
        if (doneTasks.length != 2) {
            throw new BadFormatException("done", "done");
        }
        if (!isValidNum(doneTasks[1])) {
            throw new DukeException("invalid task");
        }
        int taskNo = Integer.parseInt(doneTasks[1]);
        tasksManager.markTaskAsDone(taskNo);
    }

    public static boolean isValidNum(String num) {
        char[] charas = num.toCharArray();
        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(charas[i])) {
                return false;
            }
        }
        return true;
    }
}
