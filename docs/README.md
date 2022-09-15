# User Guide

TEDTalk is a bot to manage your Todos, Events and Deadlines. Never forget any task again by using TEDTalk now.

## Features 

### Adding a new Todo: ```todo```

Adds a new Todo into the list.

Format: ```todo TODO```

e.g. ```todo read book``` adds ```[T][ ] read book``` into the list.

### Adding a new Event: ```event```

Adds a new event into the list.

Format: ```event EVENT /at TIME```

e.g. ```event book meet /at 2069-06-09``` adds ```[E][ ] book meet (at: 9 Jun 2069)``` into the list.

### Adding a new Deadline: ```deadline```

Adds a new deadline into the list.

Format: ```deadline DEADLINE /by TIME```

e.g. ```deadline return book /by 2069-06-09``` adds ```[D][ ] return book (by: 9 Jun 2069)``` into the list.

### Marking a task as done: ```mark```

Marks the task as done.

Format: ```mark NUMBER```

e.g. ```mark 1``` marks the first task in the list as done, such as ```[T][X] read book```.

### Marking a task as undone: ```unmark```

Marks the task as undone.

Format: ```unmark NUMBER```

e.g. ```unmark 1``` marks the first task in the list as undone, such as ```[T][ ] read book```.

### Show the whole list: ```list```

Returns the current list.

Format: ```list```

e.g. ```list``` returns whatever task you have put into the list.

### Delete a task from the list: ```delete```

Deletes the task from the list.

Format: ```delete NUMBER```

e.g. ```delete 1``` deletes the first task from the list.

### Find all tasks that fall on a certain date: ```date```

Returns all tasks that have that date.

Format: ```date TIME```

e.g. ```date 2069-06-09``` returns ```[E][ ] book meet (at: 9 Jun 2069)``` and ```[D][ ] return book (by: 9 Jun 2069)```.

### Finds a task from the list by its keyword: ```find```

Finds the tasks that correspond to the keyword.

Format: ```find WORD```

e.g. ```find meet``` returns ```[E][ ] book meet (at: 9 Jun 2069)```.

### Update a task: ```update```

Update the task with the new input.

Format: ```update NUMBER /update UPDATE```

e.g. ```update 2 /update book meet /at 2069-09-06``` returns ```[E][ ] book meet (at: 6 Sep 2069)```.

### Exit the program: ```bye```

Closes the program.

Format: ```bye```

e.g. ```bye``` returns ```"Goodbye! I hope to see you again soon."```, before closing.
