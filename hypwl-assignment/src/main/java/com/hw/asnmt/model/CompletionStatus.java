package com.hw.asnmt.model;

/**
 * This enum holds all the values for completion status of a ToDo item.
 *
 */
public enum CompletionStatus {

	NEW("New"), IN_PROGRESS("InProgress"), FINISHED("Finished");

	private String value;

	/**
	 * Constructor.
	 */
	private CompletionStatus(String value) {
		this.value = value;
	}

	/**
	 * Returns the value of the enum.
	 * 
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Returns enum for the given id.
	 * 
	 * @param id
	 * @return completionStatus
	 * @exception IllegalArgumentException
	 */
	public static CompletionStatus getCompletionStatus(String id) throws IllegalArgumentException {
		for (CompletionStatus completionStatus : values()) {
			if (completionStatus.value.equals(id)) {
				return completionStatus;
			}
		}
		/*
		 * If control reached here that means the provided id did not match with
		 * any of the values of the enum, so lets throw an exception.
		 */
		throw new IllegalArgumentException();
	}
}
