package com.hw.asnmt.dto;

import java.io.Serializable;

import com.hw.asnmt.model.ToDoItem;

/**
 * Dto class to hold all the information related to ToDo items.
 *
 */
public class ToDoItemsDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String title;
	private String description;
	private String completionStatus;

	/**
	 * Default Constructor.
	 * 
	 */
	public ToDoItemsDto() {
		super();
	}

	/**
	 * Constructor to initialize this object with passed in parameters.
	 * 
	 * @param item
	 */
	public ToDoItemsDto(ToDoItem item) {
		super();
		this.title = item.getTitle();
		this.description = item.getDescription();
		this.completionStatus = item.getCompletionStatus().getValue();
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the completionStatus
	 */
	public String getCompletionStatus() {
		return completionStatus;
	}

	/**
	 * @param completionStatus
	 *            the completionStatus to set
	 */
	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

}
