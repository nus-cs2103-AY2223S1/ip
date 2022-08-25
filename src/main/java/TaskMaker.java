import java.util.Map;
import java.util.function.Function;

abstract class TaskMaker {
    static final String noSuchTaskLabelMessage = "No such Task label exists";
    private static final Map<String, Function<String[], Task>> taskMaker = Map.of(
            "todo"
            , (input) -> new TodoTask(input[1])
            , "deadline"
            , (input) -> new DeadlineTask(input[1], input[2])
            , "event"
            , (input) -> new EventTask(input[1], input[2])
    );

    private TaskMaker() {
    }

    static boolean isMakeTaskCmd(String cmd) {
        return taskMaker.containsKey(cmd);
    }

    static Task makeTask(String[] input) {
        return taskMaker.get(input[0]).apply(input);
    }

    static Task createTask(String taskType, String check, String description) throws DukeException {
        Task newTask = null;
        switch (taskType) {
        case "T":
            newTask = new TodoTask(description);
            break;
        case "D":
            String[] splitD = timeSpliter(description);
            newTask = new DeadlineTask(splitD[0], splitD[1]);
            break;
        case "E":
            String[] splitE = timeSpliter(description);
            newTask = new EventTask(splitE[0], splitE[1]);
            break;
        default:
            throw new DukeException(noSuchTaskLabelMessage);
        }
        if (check.equals("1")) {
            newTask.check();
        }
        return newTask;
    }

    static String[] timeSpliter(String description) {
        int index = description.lastIndexOf('/');
        String[] split = new String[2];
        split[0] = description.substring(0, index);
        split[1] = description.substring(index + 1);
        return split;
    }
}
