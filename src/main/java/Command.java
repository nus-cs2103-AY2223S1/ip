public enum Command {
    DEADLINE("deadline"),
    DELETE("delete"),
    EVENT("event"),
    LIST("list"),
    MARK("mark"),
    TODO("todo"),
    UNMARK("unmark");

    private final String name;
    Command(String name) {
        this.name = name;
    }

    public void run(String args) {
        if (!isCorrectUse(args)) {
            return;
        }
        switch(name) {
            case "delete":
                int index = Integer.parseInt(args) - 1;
                Duke.deleteTask(index);
                break;

            case "list":
                Duke.listTasks();
                break;

            case "deadline":
                String[] temp = args.split(" /by ", 2);
                Duke.addTask(new Deadline(temp[0], temp[1]));
                break;

            case "event":
                temp = args.split(" /at ", 2);
                Duke.addTask(new Event(temp[0], temp[1]));
                break;

            case "todo":
                Duke.addTask(new ToDo(args));
                break;

            case "mark":

            case "unmark":
                index = Integer.parseInt(args) - 1;
                Duke.markTask(Duke.getTasks().get(index), name.equals("mark"));
                break;
        }
    }

    public boolean isCorrectUse(String args) {
        String usage = correctUsage();
        switch(name) {
            case "delete":
                if (args.isEmpty()) {
                    Duke.printBot("----Error----\nPlease specify a task number.\n\n"
                                   + usage);
                    return false;
                }
                return true;

            case "list":
                if (!args.isEmpty()) {
                    Duke.printBot("----Error----\n'list' expects no arguments.");
                    return false;
                }
                return true;

            case "deadline":
                String[] temp = args.split(" /by ", 2);
                if (temp.length < 2) {
                    Duke.printBot("----Error----\nPlease specify a task and deadline.\n\n"
                                   + usage);
                    return false;
                }
                return true;

            case "event":
                temp = args.split(" /at ", 2);
                if (temp.length < 2) {
                    Duke.printBot("----Error----\nPlease specify an event and date.\n\n"
                                   + usage);
                    return false;
                }
                return true;

            case "todo":
                if (args.isEmpty()) {
                    Duke.printBot("----Error----\nPlease specify a task.\n\n" + usage);
                    return false;
                }
                return true;

            case "mark":

            case "unmark":
                if (args.isEmpty()) {
                    Duke.printBot("----Error----\nPlease specify a task number\n\n" + usage);
                } else {
                    try {
                        int index = Integer.parseInt(args) - 1;
                        if (index < 0 || index >= Duke.getCount()) {
                            Duke.printBot("----Error----\nPlease specify a valid task number.\n"
                                           + "There are " + Duke.getCount()
                                           + " task(s) in the list.\n\n" + usage);
                            return false;
                        }
                        return true;
                    } catch (NumberFormatException e) {
                        Duke.printBot("----Error----\nPlease specify a task number.\n\n"
                                + "\"" + args + "\"" + " is not an item number\n" + usage);
                        return false;
                    }
                }
        }
        return false;
    }

    public String correctUsage() {
        String ret = "Correct usage: ";
        switch(name) {
            case "deadline":
                return ret + "deadline [task-description] /by [date]";

            case "delete":
                return ret + "delete [task-number]";

            case "event":
                return ret + "event [task-description] /at [date]";

            case "mark":
                return ret + "mark [task-number]";

            case "todo":
                return ret + "todo [task-description]";

            case "unmark":
                return ret + "unmark [task-number]";
        }
        return "";
    }
}
