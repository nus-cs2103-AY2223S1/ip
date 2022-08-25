public class Parser {
    public Parser() {
    }

    public Task parseSave(String save) throws Exception {
        String[] taskElements = save.split("\\|");
        Task newTask = null;
        boolean isDone = taskElements[1].equals("0");
        try {
            switch (taskElements[0]) {
                case "D": {
                    newTask = new Deadline(taskElements[2], taskElements[3]);
                    break;
                }
                case "T": {
                    newTask = new ToDo(taskElements[2], isDone);
                    break;
                }
                case "E": {
                    newTask = new Event(taskElements[2], taskElements[3]);
                    break;
                }
                default: {
                    throw new DukeException("Task type cannot be parsed");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return newTask;
    }

}
