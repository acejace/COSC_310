package chatbot;

import java.io.FileInputStream; 
import java.io.InputStream;  

import opennlp.tools.postag.POSModel; 
import opennlp.tools.postag.POSSample; 
import opennlp.tools.postag.POSTaggerME; 
import opennlp.tools.tokenize.WhitespaceTokenizer;  

public class POSTagger {
	// Declarations
	InputStream inputStream;
	POSModel model;
	POSTaggerME tagger;
	WhitespaceTokenizer whitespaceTokenizer;
	String sentence;
	String[] tokens, tags;
	POSSample sample;
	
	// Constructor
	public POSTagger(String userInput) throws Exception {
		
      // Load POS model  
      inputStream = new FileInputStream("models/en-pos-maxent.bin"); 
      model = new POSModel(inputStream); 
       
      //Instantiate POSTaggerME class 
      tagger = new POSTaggerME(model);
      
      // Load sentence
      sentence = userInput;
	
      //Tokenizing the sentence using WhitespaceTokenizer class  
      whitespaceTokenizer= WhitespaceTokenizer.INSTANCE; 
      tokens = whitespaceTokenizer.tokenize(sentence); 
       
      //Generating tags 
      tags = tagger.tag(tokens);
      
      //Instantiating the POSSample class 
      sample = new POSSample(tokens, tags); 
	}
	
	// Retrieve POS Tagger output
	public String getPOSTags() {
		String taggedSentence = sample.toString();
		if (taggedSentence!=null) return taggedSentence;
		else return "Error. POS Tagger is returning a null value.";
	}
	
}   
