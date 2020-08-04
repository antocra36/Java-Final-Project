package com.cognixia.jump.advancedjava.finalproject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {

		private String firstName;
		private String lastName;
		private Date birthday;


		public Person (String firstName, String lastName, String birthday) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.setBirthday(birthday);
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		@Override
		public String toString() {
			return "Person [firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday + "]";
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public Date getBirthday() {
			return this.birthday;
		}

		public String getBirthdayString() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
            String strDate = dateFormat.format(this.birthday);  
            return strDate;
		}
		
		public void setBirthday(String birthday) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				date = sdf.parse(birthday);
				this.birthday = date;
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

	}
