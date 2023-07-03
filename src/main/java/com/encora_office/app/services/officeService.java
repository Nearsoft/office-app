package com.encora_office.app.services;

import org.apache.catalina.User;

public class officeService extends UserService {
	// What properties should this class have?

	// What methods should this class have?

	public officeService(UserService user) {

	}

	public boolean isOfficeAvailable(UserService user) {
		/*
		 * Would Check if the user already has an appointment for the office.
		 * This method should return true if the office is available and false if the
		 * Would check in the database if max people is reached for the office.
		 */

		boolean userHasAppointment = UserService.getUser(user.getEmail()) != null;

		if (userHasAppointment) {
			throw new Exception("User already has an appointment for the office");
		}

		return true;
	}

	public void bookOffice(String officeName) {
		/*
		 * This method should book the office for the user.
		 * Would check in the database if max people is reached for the office.
		 */
	}

	public void cancelOfficeAppointment(UserService user) {
	}
}
