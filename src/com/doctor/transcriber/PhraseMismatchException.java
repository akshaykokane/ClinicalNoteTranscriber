package com.doctor.transcriber;

class PhraseMismatchException extends Exception
{
	// Parameterless Constructor
      public PhraseMismatchException() {}

      // Constructor that accepts a message
      public PhraseMismatchException(String message)
      {
         super(message);
      }
 }