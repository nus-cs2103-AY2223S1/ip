# Mort User Guide
***
## Contents
***
- [Features](#features)

  - [Adding a to-do: `todo`](#adding-a-to-do-todo)
  - [Adding a deadline: `deadline`](#adding-a-deadline-deadline)
  - [Adding an event: `event`](#adding-an-event-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task as complete: `mark`](#marking-a-task-as-complete-mark)
  - [Marking a task as incomplete: `unmark`](#unmarking-a-task-unmark)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding tasks: `find`](#finding-tasks-find)
  - [Viewing schedule for a day: `view`](#viewing-schedule-for-a-day-view)
  - [Exiting the program: `bye`](#exiting-the-program-bye)

- [Command Summary](#command-summary)
- [Usage](#usage)

## Features
***
### Create To-Dos

Mort grudgingly creates simple to-dos for less important tasks.

### Schedule Deadlines and Events

Mort reluctantly keeps track of important deadlines and events.

### View Your Tasks For the Day

Mort unenthusiastically shows you the tasks you have scheduled for a particular day.

## Command Summary
***

| Action            | Format                                     |
|-------------------|--------------------------------------------|
| **Add to-do**     | `todo <description>`                       |
| **Add deadline**  | `deadline <description> /by <date> <time>` |
| **Add event**     | `event <description> /at <date> <time>`    |
| **List tasks**    | `list`                                     |
| **Mark task**     | `mark <task number>`                       |
| **Unmark task**   | `unmark <task number>`                     |
| **Delete task**   | `delete <task number>`                     |
| **Find task**     | `find <keyword>`                           |
| **View schedule** | `view <date>`                              |
| **Exit**          | `bye`                                      |

## Usage
***
### Adding a to-do: `todo`

Adds a to-do to the task list.

Format: `todo <description>`

Example of usage: 

`todo buy milk`

Expected outcome: A new to-do is added to the end of task list.
```
Seriously? Another one?
Give me strength...
  [T][ ] buy milk
You have 1 task. Bummer.
```

### Adding a deadline: `deadline`

Adds a deadline with a due date to the task list

Format: `deadline <description> /by <date> <time>`

- Due date is compulsory
- Due time is optional
- Date must be in format `dd/mm/yyyy`
- Time must be in the format `HHMM`

Example of usage (with time):

`deadline spanish project /by 16/9/2022 1200`

Expected outcome: A new deadline with a due date and time is added to the end of task list.
```
Seriously? Another one?
Give me strength...
  [D][ ] spanish project (by: 16 Sep 2022, 12:00 PM)
You have 2 tasks. Bummer.
```
Example of usage (without time):

`deadline spanish project /by 16/9/2022`

Expected outcome: A new deadline with a due date only is added to the end of task list.
```
Seriously? Another one?
Give me strength...
  [D][ ] spanish project (by: 16 Sep 2022)
You have 2 tasks. Bummer.
```

### Adding an event: `event`

Adds a deadline with a due date to the task list

Format: `event <description> /at <date> <time>`

- Due date is compulsory.
- Due time is optional.
- Date must be in format `dd/mm/yyyy`.
- Time must be in the format `HHMM`.

Example of usage (with time):

`event final exam /at 25/11/2022 0900`

Expected outcome: A new event with a date and time is added to the end of task list.
```
Seriously? Another one?
Give me strength...
  [E][ ] final exam (at: 25 Nov 2022, 9:00 AM)
You have 3 tasks. Bummer.
```
Example of usage (without time):

`event final exam /at 25/11/2022`

Expected outcome: A new event with a date only is added to the end of task list.
```
Seriously? Another one?
Give me strength...
  [E][ ] final exam (at: 25 Nov 2022)
You have 3 tasks. Bummer.
```

### Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

### Marking a task as complete: `mark`

Marks a task as complete.

Format: `mark <task number>`

Example of usage:

`mark 2`

Expected outcome: The second task in the task list is marked as complete.
```
You really took your time with this one, didn't you?
  [D][X] spanish project (by: 16 Sep 2022)
```

### Unmarking a task: `unmark`

Marks a task as incomplete.

Format: `unmark <task number>`

Example of usage:

`unmark 2`

Expected outcome: The second task in the task list is marked as incomplete.
```
And here I was thinking you were getting somewhere...
  [D][ ] spanish project (by: 16 Sep 2022)
```

### Deleting a task: `delete`

Deletes a task from the task list.

Format: `delete <task number>`

Example of usage:

`delete 3`

Expected outcome: The third task in the task list is deleted.
```
Good riddance, I say.
  [E][ ] final exam (at: 25 Nov 2022, 9:00 AM)
You have 2 tasks.
```

### Finding tasks: `find`

Finds tasks from the task list.

Format: `find <keyword>`

- Search is case-insensitive.
- `keyword` matches dates and times.

Example of usage:

`find oct`

Expected outcome: A list of tasks containing the input keyword is shown.
```
Really? Find them yourself next time.
Here's what I found for 'oct':
1. [E][ ] CS2106 midterms (at: 1 Oct 2022, 10:00 AM)
2. [T][X] buy octopus
```

### Viewing schedule for a day: `view`

Shows deadlines and events scheduled for a particular day.

Format: `view <date>`

- Date must be in format `dd/mm/yyyy`.

Example of usage:

`view 1/10/2022`

Expected outcome: A list of deadlines and events for the given date is shown.
```
Do I have to?
Whatever. Here are your tasks for 1 Oct 2022:
1. [E][ ] CS2106 midterms (at: 1 Oct 2022, 10:00 AM)
2. [D][ ] lab 2 submission (by: 1 Oct 2022)
```

### Exiting the program: `bye`
Exits Mort.

Format: `bye`
