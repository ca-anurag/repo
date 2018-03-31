package com.hw.asnmt.model;

/**
 * This is a model object to hold the information of a ToDo item.
 *
 */
public class ToDoItem {

	private String title;
	private String description;
	private CompletionStatus completionStatus;

	/**
	 * Default constructor.
	 */
	public ToDoItem() {
		super();
	}

	/**
	 * Parameterized constructor.
	 * 
	 * @param title
	 * @param description
	 * @param completionStatus
	 */
	public ToDoItem(String title, String description, CompletionStatus completionStatus) {
		super();
		this.title = title;
		this.description = description;
		this.completionStatus = completionStatus;
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
	public CompletionStatus getCompletionStatus() {
		return completionStatus;
	}

	/**
	 * @param completionStatus
	 *            the completionStatus to set
	 */
	public void setCompletionStatus(CompletionStatus completionStatus) {
		this.completionStatus = completionStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ToDoItem)) {
			return false;
		}
		ToDoItem other = (ToDoItem) obj;
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}
}
