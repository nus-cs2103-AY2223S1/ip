# User Guide
![Duke](docs/Ui.png)
## How to Start
1. Download the latest version of duke.jar [here](https://github.com/dreammac3816547290/ip/releases).
2. Open command prompt and navigate to the download folder.
3. Run `java -jar duke.jar`.
4. The Duke GUI will open, and you can type in commands in the command box.
## Command
### `bye` exits Duke
> bye
### `list` lists all tasks
> list
### `free`  finds the nearest date without task
> free
### `mark` marks a task as done
`> mark <task index>`
> mark 1
### `unmark` marks a task as not done
`> unmark <task index>`
> unmark 1
### `find` finds all tasks which description matches a string
`> find <string>`
> find read book
### `todo` creates a todo task
`> todo <description>`
> todo read book at the library
### `deadline` creates a task with deadline
`> deadline <description> /by <deadline YYYY-MM-DD>`
> deadline finish math homework /by 2022-09-19
### `event` creates a task with time
`> event <description> /at <time YYYY-MM-DD>`
> event go fishing /at 2022-09-20
### `delete` deletes a task
`> delete <task index>`
> delete 1