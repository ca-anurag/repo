package com.hw.asnmt.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hw.asnmt.constant.ErrorMessages;
import com.hw.asnmt.dto.ToDoItemsDto;
import com.hw.asnmt.model.CompletionStatus;
import com.hw.asnmt.model.ToDoItem;
import com.hw.asnmt.service.ToDoItemService;

/**
 * This class is a rest controller which will perform CRUD operations on the
 * ToDo items.
 *
 */
@RestController
@RequestMapping("/rest")
public class ToDoController {

	@Autowired
	private ToDoItemService todoItemService;

	/**
	 * This is a rest API which will return a ToDo item based on the given
	 * title. It will return an error message if the requested item is not
	 * found.
	 * 
	 * @param title
	 * @return responseEntity
	 */
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getToDoItem(@PathVariable("id") String title) {

		ToDoItem item = todoItemService.retrieveToDoItem(title);
		ResponseEntity<?> responseEntity = null;
		if (item != null) {
			// Convert the model to a DTO to send back as response.
			ToDoItemsDto dto = new ToDoItemsDto(item);
			responseEntity = new ResponseEntity<ToDoItemsDto>(dto, HttpStatus.OK);
		} else {
			// Item was not found, so display an error message to the client.
			responseEntity = new ResponseEntity<String>(ErrorMessages.SPECIFIC_ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/**
	 * This is a rest API which will return all the ToDo items. It will return
	 * an error message if there are no items found.
	 * 
	 * @return responseEntity
	 */
	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public ResponseEntity<?> getAllToDoItems() {

		Set<ToDoItem> items = todoItemService.retrieveAllToDoItems();
		Set<ToDoItemsDto> dtoList = convertListOfItemsToDto(items);
		ResponseEntity<?> responseEntity = null;
		/*
		 * Return all the ToDo items. Return error message if no items were
		 * found.
		 */
		if (CollectionUtils.isEmpty(dtoList)) {
			responseEntity = new ResponseEntity<String>(ErrorMessages.NO_ITEMS_FOUND, HttpStatus.NOT_FOUND);
		} else {
			responseEntity = new ResponseEntity<Set<ToDoItemsDto>>(dtoList, HttpStatus.OK);
		}
		return responseEntity;
	}

	/**
	 * This is a rest API which will create a new ToDo item. It will return an
	 * error message if the completion status is invalid.
	 * 
	 * @param request
	 * @return responseEntity
	 */
	@RequestMapping(value = "/todo", method = RequestMethod.POST)
	public ResponseEntity<?> createToDoItem(@RequestBody ToDoItemsDto request) {

		ResponseEntity<?> responseEntity = null;
		try {
			ToDoItem item = new ToDoItem(request.getTitle(), request.getDescription(),
					CompletionStatus.getCompletionStatus(request.getCompletionStatus()));
			boolean isAdded = todoItemService.addToDoItem(item);
			if (isAdded) {
				responseEntity = new ResponseEntity<String>(HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<String>(ErrorMessages.ITEM_ALREADY_EXISTS, HttpStatus.FOUND);
			}
		} catch (IllegalArgumentException exception) {
			/*
			 * This is exceptional case when the completion status provided is
			 * not valid. Display an error message to the client and ask them to
			 * provide a valid completion status.
			 */
			responseEntity = new ResponseEntity<String>(ErrorMessages.INVALID_COMPLETION_STATUS, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/**
	 * This is a rest API which will update a ToDo item. It will return an error
	 * message if item to be updated is not found or completion status provided
	 * is invalid.
	 * 
	 * @param title
	 * @param request
	 * @return responseEntity
	 */
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateToDoItem(@PathVariable("id") String title, @RequestBody ToDoItemsDto request) {

		ResponseEntity<?> responseEntity = null;
		try {
			CompletionStatus completionStatus = CompletionStatus.getCompletionStatus(request.getCompletionStatus());
			ToDoItem item = new ToDoItem(request.getTitle(), request.getDescription(), completionStatus);
			boolean isUpdated = todoItemService.updateToDoItem(title, item);
			if (isUpdated) {
				responseEntity = new ResponseEntity<String>(HttpStatus.OK);
			} else {
				// Display error message if item was not found.
				responseEntity = new ResponseEntity<String>(ErrorMessages.ITEM_UPDATE_ERROR, HttpStatus.NOT_FOUND);
			}
		} catch (IllegalArgumentException exception) {
			/*
			 * Display the error message if an invalid completion status was
			 * provided.
			 */
			responseEntity = new ResponseEntity<String>(ErrorMessages.INVALID_COMPLETION_STATUS, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/**
	 * This is a rest API which will update the completion status of a ToDo
	 * item. It will return an error message if item to be updated is not found
	 * or completion status is invalid. * @param title
	 ** 
	 * @param title
	 * @param request
	 * @return responseEntity
	 */
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> patchToDoItem(@PathVariable("id") String title, @RequestBody ToDoItemsDto request) {

		ResponseEntity<?> responseEntity = null;
		try {
			boolean isUpdated = todoItemService.updateCompletionStatusOfToDoItem(title, request.getCompletionStatus());
			// Display an error message if the item was not found.
			if (isUpdated) {
				responseEntity = new ResponseEntity<String>(HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<String>(ErrorMessages.ITEM_PATCH_ERROR, HttpStatus.NOT_FOUND);
			}
		} catch (IllegalArgumentException exception) {
			/*
			 * Display the error message if an invalid completion status was
			 * provided.
			 */
			responseEntity = new ResponseEntity<String>(ErrorMessages.INVALID_COMPLETION_STATUS, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/**
	 * This is a rest API which will delete a ToDo item. It will return an error
	 * message if item to be deleted is not found.
	 * 
	 * @param title
	 * @return responseEntity
	 */
	@RequestMapping(value = "/todo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteToDoItem(@PathVariable("id") String title) {
		ResponseEntity<?> responseEntity = null;
		boolean isDeleted = todoItemService.deleteToDoItem(title);
		// Display an error message if the item was not found.
		if (isDeleted) {
			responseEntity = new ResponseEntity<String>(HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<String>(ErrorMessages.ITEM_DELETION_ERROR, HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}

	/**
	 * This method converts a Set of ToDoItem to a Set of ToDoItemsDto.
	 * 
	 * @param items
	 * @return dtoList
	 */
	private Set<ToDoItemsDto> convertListOfItemsToDto(Set<ToDoItem> items) {
		Set<ToDoItemsDto> dtoList = null;
		if (!CollectionUtils.isEmpty(items)) {
			dtoList = new HashSet<ToDoItemsDto>();
			for (ToDoItem item : items) {
				if (item != null) {
					ToDoItemsDto dto = new ToDoItemsDto(item);
					dtoList.add(dto);
				}
			}
		}
		return dtoList;
	}
}
