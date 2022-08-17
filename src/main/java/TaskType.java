public enum TaskType {
    TODO {
        @Override
        public ToDo validateCommand(String cmd) throws DukeException {
            if (cmd.matches("(?i)^todo\\s.+")) {
                return new ToDo(cmd.substring(5));
            } else {
                throw new DukeException("Hm... Duke's confused. Are you trying to create a todo?" +
                        "\nMake sure you follow the format: todo [description].\n" +
                        "The description of a todo cannot be empty!");
            }
        }
    }, EVENT {
        @Override
        public Event validateCommand(String cmd) throws DukeException {
            if (cmd.matches("(?i)^event\\s.+\\s\\/(at)\\s.+")) {
                String[] sp = cmd.substring(6).split("\\/(at)\\s", 2);
                return new Event(sp[0], sp[1]);
            } else {
                throw new DukeException("Hm... Duke's confused. Are you trying to create an event?" +
                        "\nMake sure you follow the format: event [description] /at [event datetime]");
            }
        }
    }, DEADLINE {
        @Override
        public Deadline validateCommand(String cmd) throws DukeException {
            if (cmd.matches("(?i)^deadline\\s.+\\s\\/(by)\\s.+")) {
                String[] sp = cmd.substring(9).split("\\/(by)\\s", 2);
                return new Deadline(sp[0], sp[1]);
            } else {
                throw new DukeException("Hm... Duke's confused. Are you trying to create a deadline?" +
                        "\nMake sure you follow the format: deadline [description] /by [deadline]");
            }
        }
    };

    public abstract Task validateCommand(String cmd) throws DukeException;
}
