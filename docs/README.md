# User Guide
**aRC** is a desktop application that comes in a visually-appealing and pretty Graphical User Interface (GUI). 
It is a task and note management system that allows users to quickly and easily add their data onto the system.

## Features & Usages

### General

aRC boasts a permanent storage system. Tasks/notes that you added will be automatically saved in your local computer.
Subsequent usage of the aRC application will load these tasks/notes seamlessly and automatically.

#### General Commands

`bye` 

Exits the application

![bye.png](bye.png)

`[Invalid Command]`

Displays a list of valid aRCommands and their respective syntax

![invalidcommand.png](invalidcommand.png)

### Task

There are currently 3 types of Tasks that aRC can handle :
- Todo
- Deadline
- Event

All Tasks must have a non-empty `title` field.

#### Commands

`list`

Lists all available Tasks

![list.png](list.png)

`todo [title]`

Adds a Todo Task. `title` field cannot be empty.

Example usage : `todo complete User Guide`

Expected outcome :

![todo.png](todo.png)

`deadline [title] /by [deadline (dd/mm/yyyy)]`

Adds a Deadline Task. `title` field cannot be empty. `deadline` field must be in the format `(dd/mm/yyyy)`.

Example usage : `deadline submit User Guide /by 15/09/2022`

Expected outcome :

![deadline.png](deadline.png)

`event [title] /at [time]`

Adds an Event Task. `title` field cannot be empty. `time` field cannot be empty.

Example usage : `event birthday celebration /at 21/09/2022`

Expected outcome :

![event.png](event.png)

`mark [index]`

Sets a Task as done. `index` field counts starting from 1.

![mark.png](mark.png)

`unmark [index]`

Sets a Task as not done. `index` field counts starting from 1.

![unmark.png](unmark.png)

`delete [index]`

Deletes a Task. `index` field counts starting from 1.

![delete.png](delete.png)

`find [keyword]`

Lists all Tasks whose `title` matches `keyword`. `keyword` cannot be empty.

![find.png](find.png)

### Note Management

Notes have a mandatory `title` field and an optional `description` field.

#### Commands

`notes`

Lists all available Notes

![notes.png](notes.png)

`note [title] /desc [description (optional)]` 

Adds a Note. `title` field cannot be empty. `description` field is optional.

Example usage : `note remember to complete User Guide /desc this is urgent!`

Expected outcome :

![note.png](note.png)

`deletenote [index]`

Deletes a Note. `index` field counts starting from 1.

![deletenote.png](deletenote.png)

## Acknowlegements
- [GUI adaptation](https://se-education.org/guides/tutorials/javaFx.html)
- [JavaFX library](https://openjfx.io/)