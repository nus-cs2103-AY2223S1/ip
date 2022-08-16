public class TaskCreator {
    /**
     * Main class to create new Task
     */
    private static int SIZEOFPREPOSITION = 4;

    public static Task CreateTask(String in) {
        Task task = null;

        if (!in.startsWith("todo") && !in.startsWith("deadline") && !in.startsWith("event")) {
            return task;
        }

        String[] inArr = in.split(" ", 2);
        if (inArr.length < 2) {
            return new Task("");
        }

        String info = inArr[1];

        if (in.startsWith("todo")) {
            task = new Todo(info);

        } else if (in.startsWith("deadline")) {
            int indexOfSplit = info.indexOf("/by");
            if (indexOfSplit == -1) {
                return new ErrorTask("");
            }

            String description = info.substring(0,indexOfSplit);
            String date = info.substring(indexOfSplit + SIZEOFPREPOSITION);

            task = new Deadline(description, date);

        } else if (in.startsWith("event")) {
            int indexOfSplit = info.indexOf("/at");
            if (indexOfSplit == -1) {
                return new ErrorTask("");
            }

            String description = info.substring(0,indexOfSplit);
            String dateTime = info.substring(indexOfSplit + SIZEOFPREPOSITION);

            task = new Event(description, dateTime);
        }

        return task;
    }
}
