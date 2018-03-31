package com.hw.asnmt.service;

import java.util.Set;

import com.hw.asnmt.model.ToDoItem;

/**
 * This is service layer which contains logic for performing operations on ToDo
 * items.
 *
 */
public interface ToDoItemService {

	/**
	 * Method to add a given ToDo item to the data structure.
	 * 
	 * @param item
	 * @return boolean
	 */
	boolean addToDoItem(ToDoItem item);

	/**
	 * Method to retrieve a ToDo item for a given title from the data structure.
	 * 
	 * @param title
	 * @return ToDoItem
	 */
	ToDoItem retrieveToDoItem(String title);

	/**
	 * Method to retrieve all the ToDo items from the data structure.
	 * 
	 * @return List<ToDoItem>
	 */
	Set<ToDoItem> retrieveAllToDoItems();

	/**
	 * Method to update a given ToDo item in the data structure.
	 * 
	 * @param title
	 * @param todoItem
	 * @return boolean
	 */
	boolean updateToDoItem(String title, ToDoItem todoItem);

	/**
	 * Method to update completion status of a given ToDo item in the data
	 * structure.
	 * 
	 * @param title
	 * @param completionStatus
	 * @return boolean
	 */
	boolean updateCompletionStatusOfToDoItem(String title, String completionStatus);

	/**
	 * Method to delete a given ToDo item from the data structure.
	 * 
	 * @param title
	 * @return boolean
	 */
	boolean deleteToDoItem(String title);

}
