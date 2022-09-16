package duke.command;

import duke.exception.DukeException;

/**
 * Encapsulates statistic commands (pie chart and bar chart).
 */
public abstract class StatsCommand extends Command {
    public static StatsCommand getStatsCommand(String userInput) throws DukeException {
        userInput = userInput.trim();
        // if no flag is provided, default to pie chart stat
        if (userInput.isBlank()) {
            return new PieChartStatsCommand();
        }

        if (userInput.startsWith("-p")) {
            return new PieChartStatsCommand();
        } else if (userInput.startsWith("-b")) {
            String barGraphConstructString = userInput.substring(2);
            return new BarGraphStatsCommand(barGraphConstructString);
        } else {
            throw new DukeException("woops- bobo doesn't understand the flag "
                                    + userInput);
        }
    }
}
