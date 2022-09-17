# User Guide

Project Olivia is a chatbot calendar program.

----
## Features 

### Add task to calendar
Adding an entry to the calendar.
An entry can have a time spot (deadline), a time period (event), or no time at all (todo).

### List all the entries in the calendar
Show all the entries on the calendar.

### Mark/Unmark entry
Mark an entry as done or mark an entry as not done.

### Delete entry
Remove entry from calendar.

### Search
Search for an entry that contains a specific keyword.
The keyword can also be a time.

### Tag
Mark an entry with a tag.

----
## Usage

### `todo` - add a task with no time information
Add a task with no time information to the calendar.
<br/>
`todo <title of the task> <tags> `
<br/>
Expected output:
A message confirming that the task has been added to the calendar.
<br/><br/>

### `deadline` - add a task with a time spot
Add a task with a time spot to the calendar.
<br/>
`deadline <title> /by <time> <tags>`
<br/>
Expected output:
A message confirming that the task has been added to the calendar.
<br/><br/>

### `event` - add a task with a time spot
Add a task with a time period to the calendar.
<br/>
`event <title> /at <start time> - <end time> <tags>`
<br/>
Expected output:
A message confirming that the task has been added to the calendar.
<br/><br/>

### `list` - show all the entries in the calendar
Display all the entries in the calendar.
<br/>
`list`
<br/><br/>

### `ls` - show all the entries in the calendar
Alias of `list`.
<br/><br/>

### `mark` - mark an entry as done
Mark a specific entry in the calendar as done. 
<br/>
`mark <index of the entry>`
<br/>
For the index of the entry, please check with the `ls` command.
<br/>
Expected output:
A message confirming that the task has been marked as done.
<br/><br/>

### `unmark` - mark an entry as undone
Mark a specific entry in the calendar as undone.
<br/>
`unmark <index of the entry>`
<br/>
For the index of the entry, please check with the `ls` command.
<br/>
Expected output:
A message confirming that the task has been marked as undone.
<br/><br/>

### `delete` - remove an entry from the calendar
Delete the specified entry from the calendar.
<br/>
`delete <index of the entry>`
<br/>
For the index of the entry, please check with the `ls` command.
<br/>
Expected output:
A message confirming that the task has deleted.
<br/><br/>

### `find` - search for entry
Search for entries with specific keywords in the calendar.
<br/>
`find <keyword>`
<br/>
Expected output:
A list of entries that contains the specified keyword. 
Or a message indicating that such entries do not exist.
<br/><br/>

### `help` - display help message
Display the help message.
<br/>
`help`
<br/><br/>

### Syntax for time
The following three time formats are supported:
<br/>
`DD/MM/YYYY hh:mm`
<br/>
`DD/MM/YYYY`
<br/>
`hh:mm`
<br/><br/>

### Syntax for tags
Each tag starts with a `#` followed by the title of that tag.
Multiple tags can be added together separated with spaces.
<br/>
`#<title for tag 1> #<title for tag 2>`
<br/><br/>