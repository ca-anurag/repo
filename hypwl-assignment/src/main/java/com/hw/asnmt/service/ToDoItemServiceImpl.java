package com.hw.asnmt.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hw.asnmt.model.CompletionStatus;
import com.hw.asnmt.model.ToDoItem;

/**
 * Implementation class for ToDoItemService. Contains the business logic for
 * performing operations on the ToDo items.
 *
 */
@Service
public class ToDoItemServiceImpl implements ToDoItemService {

	/*
	 * Since persistence storage is not required, lets store all the ToDo items
	 * in a HashSet(no duplicates).
	 */
	private Set<ToDoItem> toDoItems = new HashSet<ToDoItem>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hw.asnmt.service.ToDoItemService#addToDoItem(com.hw.asnmt.model.
	 * ToDoItem)
	 */
	@Override
	public boolean addToDoItem(ToDoItem item) {
		/*
		 * Add the item to the set and return the result. Returns false if the
		 * item does not get added.
		 */
		boolean isAdded = toDoItems.add(item);
		return isAdded;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hw.asnmt.service.ToDoItemService#retrieveToDoItem(java.lang.String)
	 */
	@Override
	public ToDoItem retrieveToDoItem(String title) {
		if (!CollectionUtils.isEmpty(toDoItems)) {
			for (ToDoItem toDoItem : toDoItems) {
				if (toDoItem != null && title.equals(toDoItem.getTitle())) {
					return toDoItem;
				}
			}
		}
		/*
		 * If control reached here, that means the requested item was not found.
		 * Return null in this case.
		 */
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hw.asnmt.service.ToDoItemService#retrieveAllToDoItems()
	 */
	@Override
	public Set<ToDoItem> retrieveAllToDoItems() {
		return toDoItems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hw.asnmt.service.ToDoItemService#updateToDoItem(java.lang.String,
	 * com.hw.asnmt.model.ToDoItem)
	 */
	@Override
	public boolean updateToDoItem(String title, ToDoItem todoItem) {
		boolean isUpdated = false;
		ToDoItem itemToBeRemoved = null;
		if (!CollectionUtils.isEmpty(toDoItems)) {
			for (ToDoItem item : toDoItems) {
				if (item != null && title.equals(item.getTitle())) {
					itemToBeRemoved = item;
					break;
				}
			}
		}
		/*
		 * Remove the old item and add this new updated item. Since we have
		 * given user to update the unique title also hence the updated item is
		 * as good as new.
		 */
		toDoItems.remove(itemToBeRemoved);
		isUpdated = toDoItems.add(todoItem);
		return isUpdated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hw.asnmt.service.ToDoItemService#updateCompletionStatusOfToDoItem(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public boolean updateCompletionStatusOfToDoItem(String title, String completionStatus) {
		boolean isUpdated = false;
		if (!CollectionUtils.isEmpty(toDoItems)) {
			for (ToDoItem toDoItem : toDoItems) {
				if (toDoItem != null && title.equals(toDoItem.getTitle())) {
					/*
					 * Requested ToDo item exists, so lets update the completion
					 * status.
					 */
					toDoItem.setCompletionStatus(CompletionStatus.getCompletionStatus(completionStatus));
					isUpdated = true;
					break;
				}
			}
		}
		return isUpdated;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hw.asnmt.service.ToDoItemService#deleteToDoItem(java.lang.String)
	 */
	@Override
	public boolean deleteToDoItem(String title) {
		ToDoItem itemToBeDeleted = null;
		if (!CollectionUtils.isEmpty(toDoItems)) {
			for (ToDoItem toDoItem : toDoItems) {
				if (toDoItem != null && title.equals(toDoItem.getTitle())) {
					itemToBeDeleted = toDoItem;
					break;
				}
			}
		}
		// Delete the requested ToDo item.
		boolean isDeleted = toDoItems.remove(itemToBeDeleted);
		return isDeleted;
	}
}
