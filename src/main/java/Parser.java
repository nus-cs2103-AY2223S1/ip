public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public boolean parse(String request) {
        if (request.equals("bye")) { // Terminates Greg
            return true;
        }
        else if (request.equals("list")) { // Lists out tasks
            taskList.printList();
        }

        // 3. Mark task as done
        else if (request.startsWith("mark")) {
            int taskNumber = Integer.parseInt(request.split(" ")[1]) - 1;
            taskList.markTask(taskNumber);
        }

        // 4. Mark task as undone
        else if (request.startsWith("unmark")) {
            int taskNumber = Integer.parseInt(request.split(" ")[1]) - 1;
            taskList.unmarkTask(taskNumber);
        }

        // 5. Adding in tasks
        // Todo
        else if (request.startsWith("todo")) {
            String todoTask = request.replace("todo ", "");
            Todo todo = new Todo(todoTask);
            taskList.addTask(todo);
        }

        // Deadline
        else if (request.startsWith("deadline")) {
            String[] deadlineTask = (request.replace("deadline ", "")).split(" /by ");
            Deadline deadline;
            if (deadlineTask[1].length() > 10) { // Deadline has a time 
                String[] dlDateAndTime = deadlineTask[1].split(" ");
                String time = dlDateAndTime[1].substring(0, 2) + ":" + dlDateAndTime[1].substring(2,4);
                deadline = new Deadline(deadlineTask[0], dlDateAndTime[0], time);
            } else { // Deadline without at specific time
                deadline = new Deadline(deadlineTask[0], deadlineTask[1]); 
            }
            taskList.addTask(deadline);
        }

        // Event
        else if (request.startsWith("event")) {
            String[] eventTask = request.replace("event ", "").split(" /at ");
            String[] eventDateAndTime = eventTask[1].split(" ");
            String eventTime = eventDateAndTime[1].substring(0,2) + ":" + eventDateAndTime[1].substring(2,4);
            Event event = new Event(eventTask[0], eventDateAndTime[0], eventTime);
            taskList.addTask(event);
        }

        // 6. Deleting tasks
        else if (request.startsWith("delete")) {
            String[] deleteTask = request.split(" ");
            int taskIndex = Integer.parseInt(deleteTask[1]);
            taskList.deleteTask(taskIndex);
        }

        // Inappropriate input
        else {
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
        return false;
    }
}
