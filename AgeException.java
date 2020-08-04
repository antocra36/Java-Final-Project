package com.cognixia.jump.advancedjava.finalproject;

	class AgeException extends Exception {
		private static final long serialVersionUID = 1L;
		public static final int MIN = 16;
		
		public AgeException(int age) {
			super("You need to be at least " + MIN + " years old.");
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	