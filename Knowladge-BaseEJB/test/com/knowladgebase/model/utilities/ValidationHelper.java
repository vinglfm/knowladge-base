package com.knowladgebase.model.utilities;

/**
 * Provides common messages for validation errors
 * 
 * @author a.pryshchepa(vinglfm@gmail.com)
 */
public final class ValidationHelper {
	/*
	 * On validation failed messages:
	 */
	private static final String INCORRECT_FIELD_MESSAGE = "Field %s is incorrect";

	/*
	 * Validation check messages:
	 */
	public static final String MAY_NOT_BE_NULL = "may not be null";
	private static final String LENGTH_MUST_BE_BETWEEN = "length must be between %d and %d";

	private ValidationHelper() {
	}

	/**
	 * @param fieldName
	 *            filed name that was set incorrectly
	 * @return common description for incorrectly set fields
	 */
	public static String inccorectFieldMessage(String fieldName) {
		return String.format(INCORRECT_FIELD_MESSAGE, fieldName);
	}

	public static String lengthBetweenMessage(int min, int max) {
		return String.format(LENGTH_MUST_BE_BETWEEN, min, max);
	}

}
