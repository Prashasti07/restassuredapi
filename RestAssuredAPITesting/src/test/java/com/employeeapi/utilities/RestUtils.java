package com.employeeapi.utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String empName() {

		String empName = RandomStringUtils.randomAlphabetic(1);
		return ("abc" + empName);

	}

	public static String empAge() {
		String empAge = RandomStringUtils.randomNumeric(2);
		return empAge;

	}

	public static String empSal() {
		String empSal = RandomStringUtils.randomNumeric(5);
		return empSal;

	}

}
