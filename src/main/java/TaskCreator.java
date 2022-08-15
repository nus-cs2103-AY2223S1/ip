public class TaskCreator {
    /**
     * Main class to create new Task
     */
    private static int SIZEOFPREPOSITION = 4;

    public static Task CreateTask(String in) {
        Task task = null;

        if (in.startsWith("todo")) {
            task = new Todo(in.split(" ", 2)[1]);

        } else if (in.startsWith("deadline")) {
            String info = in.split(" ", 2)[1];
            int indexOfSplit = info.indexOf("/by");
            String description = info.substring(0,indexOfSplit);
            String date = info.substring(indexOfSplit + SIZEOFPREPOSITION);

            task = new Deadline(description, date);

        } else if (in.startsWith("event")) {
            String info = in.split(" ", 2)[1];
            int indexOfSplit = info.indexOf("/at");
            String description = info.substring(0,indexOfSplit);
            String dateTime = info.substring(indexOfSplit + SIZEOFPREPOSITION);

            task = new Event(description, dateTime);
        }

        return task;
    }
}
