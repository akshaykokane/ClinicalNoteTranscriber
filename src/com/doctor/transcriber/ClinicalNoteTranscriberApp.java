package com.doctor.transcriber;

import java.util.Scanner;

public class ClinicalNoteTranscriberApp {

	public static void main(String[] args) throws PhraseMismatchException, InvalidNumberException {
	    
		Scanner sc = new Scanner(System.in);  // Create a Scanner object
	    System.out.println("Enter the string input");
	    String input = sc.nextLine();  // Read user input
	    
		String result = TransformText(input);
		System.out.println("\nTransformed String : \n");
		System.out.println(result);
		sc.close();
	}
	
	private static final String NUMBER = "Number";
	private static final String NEXT = "Next";
	
	/*
	 * @param  rawInput  Raw Input to transciber
	 * @return the prarsed string, which has the numbered list. 
	 * */
	public static String TransformText(String rawInput) throws PhraseMismatchException, InvalidNumberException {
		Integer currentNumber = null;
		
		if (rawInput == null) {
			throw new IllegalArgumentException("Input cannot be null");
		}
		
		StringBuilder parsedString = new StringBuilder();
		String[] inputWords = rawInput.split(" ");
		boolean isNewLine = false;
		
		for (int i = 0; i < inputWords.length; i++) 
		{
			String word = inputWords[i];
			
			// In the case word is number, there are two possibilies, it can be NUMBER NEXT or NUMBER N.
			if (word.equalsIgnoreCase(NUMBER)) 
			{
				String nextWord = inputWords[i + 1];
				
				if (nextWord.equalsIgnoreCase(NEXT)) 
				{
					// if NUMBER NEXT is called before NUMBER N, then its a error
					if (currentNumber == null) {
						throw new PhraseMismatchException("Couldn't find the starting number for the list. I am not sure");
					}
					currentNumber++;
				}
				else 
				{
					// initalizes the numbered list
					if (currentNumber == null) {
					  currentNumber = convertWordToInt(nextWord);
					}
					else 
					{
						// numbered list already initalized, this is the word from the sentence, can be appened as it is.
						parsedString.append(word + " ");
						continue;
					}
					
				}
				parsedString.append("\n" + currentNumber.toString() + ". ");
				isNewLine = true;
				i++;
			}
			else
			{
				// if its a new line, the first letter needed to be convert to UpperCase
				if (isNewLine) {
					word = word.substring(0, 1).toUpperCase() + word.substring(1);
				}
				
				parsedString.append(word + " ");
				isNewLine = false;
			}

		}
		
		return parsedString.toString();
		
	}
	
	/*
	 * @param  word  Number in word
	 * @return the integer value for the number
	 * */
	private static Integer convertWordToInt(String word) throws InvalidNumberException {
		
		if(word.equalsIgnoreCase("one")) {
            return 1;
        }
        else if(word.equalsIgnoreCase("two")) {
        	return 2;
        }
        else if(word.equalsIgnoreCase("three")) {
        	return 3;
        }
        else if(word.equalsIgnoreCase("four")) {
        	return 4;
        }
        else if(word.equalsIgnoreCase("five")) {
        	return 5;
        }
        else if(word.equalsIgnoreCase("six")) {
        	return 6;
        }
        else if(word.equalsIgnoreCase("seven")) {
        	return 7;
        }
        else if(word.equalsIgnoreCase("eight")) {
        	return 8;
        }
        else if(word.equalsIgnoreCase("nine")) {
        	return 9;
        }
		
		throw new InvalidNumberException("Couldn't parse the number. Did you forgot to tell the starting number?");
		
	}

}
