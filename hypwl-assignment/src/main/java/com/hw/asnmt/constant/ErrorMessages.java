package com.hw.asnmt.constant;

/**
 * This class holds all the error messages which will be displayed to the user
 * for various negative scenarios.
 * 
 */
public class ErrorMessages {

	public static final String SPECIFIC_ITEM_NOT_FOUND = "The requested ToDo item was not found, please provide a valid title or create a new item.";

	public static final String NO_ITEMS_FOUND = "There are no ToDo items available. Please create a new ToDo item.";

	public static final String INVALID_COMPLETION_STATUS = "Invalid completion status. Please choose one of these completion status - New/InProgress/Finished";

	public static final String ITEM_DELETION_ERROR = "Requested ToDo item was not deleted as it does not exist.";

	public static final String ITEM_UPDATE_ERROR = "Requested ToDo item was not updated as it does not exist.";

	public static final String ITEM_PATCH_ERROR = "Requested ToDo item was not patched as it does not exist.";

	public static final String ITEM_ALREADY_EXISTS = "Requested ToDo item already exists.";

}
