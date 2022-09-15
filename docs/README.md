# User Guide

Duke Aemon Of Old is a free, simple-to-use organization tool that can help you find order in your day.

## Features 

### Feature-Add Todo

Add a task with no deadline or time associated with it as a todo in your list. Upon addition, all tasks are marked as incomplete. 

### Feature-Add Event

Add an event along with it start and end timings to your list. Upon addition, all tasks are marked as unattended. 

### Feature-Add Deadline

Add a task along with the deadline for its completion to your list. Upon addition, all tasks are marked as incomplete. 

### Feature-Delete Tasks

Delete tasks from your list permanently.

### Feature-Mark Complete

Mark tasks as complete and see it relfected in your list.

### Feature-Mark Incomplete

Unmark tasks (or, mark them incomplete) and see it reflected in your list.

### Feature-Sort List

Sort your list based on tasks' deadline or time of occurence, if any and description otherwise.

### Feature-Search For Words

Search for tasks in your list specifically by words they must contain in their description.

### Feature-Display List

See your list, along with number of items, task description, status and time (if any) displayed.

## Usage

### `Keyword` - Describe action

Add tasks as todos, events and deadlines by adding task description and time (if applicable)

Example of usage: 

`todo study`
`event study\10-10-2022 1800 2000`
`event CS2103 submission\10-10-2022`

Expected outcome:
Aemon indicates the item is added to the list and the new number of items in the list

List all items

Example of usage: 

`list`

Expected outcome:
Aemon displays the whole list

Delete tasks by specifying item number on list

Example of usage: 

`delete 2`

Expected outcome:
Aemon indicates the item is deleted from the list and the new number of items in the list

Mark item as complete by specifying its position on list

Example of usage: 

`mark 3`

Expected outcome:
Aemon indicates the item is marked complete

Mark item as incomplete(unmark item) by specifying its position on list

Example of usage: 

`unmark 3`

Expected outcome:
Aemon indicates the item is marked incomplete

Search for an item by a word in its description 

Example of usage: 

`search CS2103`

Expected outcome:
Aemon displays a list of all the items with the word mentioned in their task descriptions

Sort the items on the list

Example of usage: 

`sort`

Expected outcome:
Aemon displays a list sorted by deadlines and time (if any), and descriptions otherwise
