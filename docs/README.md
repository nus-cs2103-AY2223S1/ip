#  Duke
Duke is a friendly chat bot who will help you **manage your tasks!**
### Contents
1. Quick start
2. Features
	- Add tasks : `todo`, `event`, `deadline`
	- Delete : `delete`
	- Mark : `mark`
	- Un-mark: `unmark`
	- List : `list`
3. FAQ
4. Command summary

# Quick start

1. Ensure you have Java `11`or above installed in your Computer.
2. Download the latest `duke.jar` file.
3. Make a new directory named duke in your desktop and copy duke file there.
4. Double-click the jar file to start duke.
5. Type the command in the command box and press Enter to execute it.
6. Refer to the Features below for details of each command.

# Features

### Adding Tasks : `todo`, `event`, `deadline`

Adds a task to your list of task. Each task is categorized as ToDo, Event, and Deadline.

**Format:** 

1. todo: `todo [description]`
2. deadline: `deadline [description] /by [date and time]`
3. event: `event [description] /at [date and time]`
> `:star:` Tip:  Write  date and time in the format `yyyy-mm-dd`

**Examples:**

- `todo read book` 
- `deadline return book /by 2022-09-10`
- `event finish reflections /at 2022-09-10`

### Delete: `delete`

Delete a task to your list of task.

**Format:** `delete [index]`
> `:star:` Tip:  get the index by using `list`

**Example:** `delete 1` 

### Mark and Un-mark: `mark`, `unmark`

Mark tasks as complete and incomplete in your task list.

**Format:** 
1. Mark as complete: `mark[index]`
2. Mark as incomplete: `unmark [index]`
> `:star:` Tip:  get the index by using `list`

**Example:** 
- `mark 1` 
- `unmark 1`

### List: `list`

Lists the tasks in your task list.

**Format:** `list`

# FAQ
**Q**: How do I transfer my data to another Computer?  
**A**: Install the app in the other computer and copy over the data file in the home directory of your duke program.

# Command Summary
| **Action** | **Format, Examples** |
| - | - |
| `todo` | `todo [description]`, e.g., `todo read book`
| `deadline` | `deadline [description] /by [date and time]`, e.g. `deadline return book /by 2022-09-10`
|`event` | `event [description] /at [date and time]`, e.g. `event finish reflections /at 2022-09-10`
|`delete`|`delete [index]` e.g., `delete 1`
|`mark`|`mark [index]` e.g., `mark 1`
|`unmark`|`unmark [index]` e.g., `unmark 1`
|`list`|`list`
