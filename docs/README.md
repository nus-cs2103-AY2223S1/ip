# User Guide

## Quick Start

Get the JAR file, and place it in the directory of your choice. Run the JAR and you are set! On Windows and Mac, you may double-click the JAR to run it.

Console mode is also provided. You may run `java -jar ./duke.jar console` in Windows if you prefer using the console.

## Features

### Chatbot

Anthea is chatbot-styled, where the commands are with specified words.

### Tasks/Notes

You can track your tasks, be them events, todos or deadlines. You can add notes too!

## Usage

### Task Commands

#### Making tasks

##### `deadline DESCRIPTION [/by TIME]`

Adds a deadline to track that has to be done by a certain `TIME`.

**Example of usage:** `deadline Rush the holiday homework... /by 8-9 2018`

This would make the deadline:

```
Good luck with the deadline, here's the task:
[D][ ] Rush the holiday homework... (by: 08 Sep 2018 2359)
```

##### `event DESCRIPTION [/at TIME]`

Adds an event to track that happens at a certain `TIME`.

**Example of usage:** `event Prepare for the holiday season dinner /at 21st dec 2012`

This would make the event:
```
That's going to happen at some time later:
[E][ ] Prepare for the holiday season dinner (at: 21 Dec 2012 2359)
```

##### `todo DESCRIPTION`

Adds a todo to track.

**Example of usage:** `todo Plan holiday dinner`

This would make the todo:
```
I've recorded this thing you need to do:
[T][ ] Plan holiday dinner
```

#### Modifying tasks

##### `delete INDEX`

Delete task with index `INDEX`.

**Example of usage:**
```
event Prepare for the holiday season dinner /at 21st dec 2012
delete 1
```

This would delete the task:
```
It seems you didn't need this task anymore, so I removed it:
[E][ ] Prepare for the holiday season dinner (at: 21 Dec 2012 2359)
You have 0 tasks left.
```

##### `mark INDEX`

Mark the task with index `INDEX` as completed.

**Example of usage:**
```
event Prepare for the holiday season dinner /at 21st dec 2012
mark 1
```

This will mark the task as done:
```
Marked your task as done:
[E][X] Prepare for the holiday season dinner (at: 21 Dec 2012 2359)
```

##### `reschedule INDEX [/at TIME] [/by TIME]`

Reschedule the task. If the task is a deadline, `/by TIME` is used to reschedule. If the task is an event, `/at TIME` is used to reschedule. If the task is a todo, it doesn't have a time, so it cannot be rescheduled. You cannot reschedule your notes.

**Example of usage:**
```
event Prepare for the holiday season dinner /at 21st dec 2012
reschedule 1 /at 2012 20/12
```

This would reschedule the task:
```
I have rescheduled your task!
[E][ ] Prepare for the holiday season dinner (at: 20 Dec 2012 2359)
```

##### `unmark INDEX`

Unmark the task with index `INDEX`.

**Example of usage:**
```
event Prepare for the holiday season dinner /at 21st dec 2012
unmark 1
```

This would unmark the task:
```
Aw... it's not done yet:
| [E][ ] Prepare for the holiday season dinne. (at: 21 Dec 2012 2359)
```

#### Viewing tasks

##### `find DESCRIPTION`

Find all tasks matching `DESCRIPTION`. The number next to the task is preserved.

**Example of usage:**
```
event Prepare for the holiday season dinner /at 21st dec 2012
find holiday
```

This would find these tasks:
```
Here are the tasks that you might be looking for:
1.[E][X] Prepare for the holiday season dinner (at: 21 Dec 2012 2359)
```

##### `list`

List all tasks.

**Example of usage:**
```
event Prepare for the holiday season dinner /at 21st dec 2012
list
```

This would list all tasks:
```
Here, your tasks:
1.[E][X] Prepare for the holiday season dinner (at: 20 Dec 2012 2359)
```

### Note commands

##### `delete note INDEX`

Delete note with index `INDEX`.

**Example of usage:**
```
note Delete this /content Something to forget.
delete note 1
```

This would delete the note:
```
I removed this note:
Delete this
```

##### `find notes DESCRIPTION`

Find all notes where the title matches `DESCSRIPTION`. The number next to the note is preserved.

**Example of usage:**
```
note Find this /content Something to find.
find notes Find
```

This would find the note:
```
These notes match your query:
1.Find this
```

##### `list notes`

List all notes.

**Example of usage:**
```
note Find this /content Something to find.
list notes
```

This would list your notes:
```
Here, your notes:
1.Find this
```

##### `note DESCRIPTION [/content CONTENT]`

Adds a note titled `DESCRIPTION` with content `CONTENT`.

**Example of usage:**
```
note Find this /content Something to find.
```

This would add a note:
```
Added your note about Find this.
```

##### `view note INDEX`

View note with index `INDEX`. This shows the title and content.

**Example of usage:**
```
note Find this /content Something to find.
view note 1
```

This would view the note:
```
Here's the note:
Find this
Something to find.
```

### Other commands

##### `bye`
Close the application.

### Command Glossary

`CONTENT`/`DESCRIPTION`: Any ASCII string works, but ensure none of the words start with a forward slash, else it would be interpreted as a command modifier. Example: `grocery list` is fine but `groceries meat /fish` is not.

`INDEX`: Any number works, as long it is the index of a task/note. Deleting a task/note will not preserve the index of other tasks/note behind it.

`TIME`: Any string which contains the following keywords would be interpreted as a time if Anthea can resolve the time.
* A time written as HH:MM (24 hours) or HH:MMam or HH:MMpm, or with a period instead of a colon.
* A day of the week, full or abbreviated.
* An ordinal, interpreted as the day of a month (e.g. 1st, 23rd).
* A month name, full or abbreviated.
* A date, written as D/M/Y or D-M-Y.
* A year, written as Y.

Time resolution proceeds as follows:
* A time at or after the present is attempted to be found which matches all the keywords
* If no such time exists, a time before the present but after 1st Jan 1AD 00:00
* If no such time exists, the string is left intact for the benefit of the user.

### Command Summary

|Command|Description|
|-|-|
|`bye`|Close the application.|
|`deadline DESCRIPTION [/by TIME]`|Adds a deadline.|
|`delete INDEX`|Delete task.|
|`delete note INDEX`|Delete note.|
|`event DESCRIPTION [/at TIME]`|Adds an event.|
|`find DESCRIPTION`|Find tasks.|
|`find notes DESCRIPTION`|Find notes.|
|`list`|List all tasks.|
|`list notes`|List all notes.|
|`todo DESCRIPTION`|Adds a todo to track.|
|`mark INDEX`|Mark task as completed.|
|`note DESCRIPTION [/content CONTENT]`|Adds a note.|
|`reschedule INDEX [/at TIME] [/by TIME]`|Reschedule the task.|
|`unmark INDEX`|Unmark task.|
|`view note INDEX`|View note.|


### Data
The data for the application is stored in `duke.txt` and `dukeNotes.txt` in CSV using base64. They may be deleted to purge data, or backups may be made of them.
