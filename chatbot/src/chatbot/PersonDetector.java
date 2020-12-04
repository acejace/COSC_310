package chatbot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class PersonDetector{
	// Declarations
	InputStream inputStream;
	TokenNameFinderModel model;
	NameFinderME nameFinder;
	String[] wordArray;
	Span[] nameSpans;
	
	// Constructors
	public PersonDetector(String[] userInput) throws Exception {
		// Instantiate PersonDetector			
		//Loading the NER - Person model
		inputStream = new FileInputStream("models/en-ner-person.bin");
		model = new TokenNameFinderModel(inputStream);
	
		//Instantiating the NameFinder class
		nameFinder = new NameFinderME(model);
		
		// Getting string array
		wordArray = userInput;
		
		// Finding the names in the sentence
		nameSpans = nameFinder.find(wordArray);
	}
	
	// Prints the persons in user responses
	public void getPersons() {
		for(Span s: nameSpans) {
			System.out.println(s.toString()); 
		 } 
	}
} 