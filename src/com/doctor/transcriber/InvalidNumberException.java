package com.doctor.transcriber;

class InvalidNumberException extends Exception
{
	// Parameterless Constructor
      public InvalidNumberException() {}

      // Constructor that accepts a message
      public InvalidNumberException(String message)
      {
         super(message);
      }
 }