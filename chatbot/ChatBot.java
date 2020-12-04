package chatbot;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ChatBot {
	public ArrayList<String> profanityFilter = new ArrayList<String>();
	public Responder exceptionResponder;
	public ArrayList<Responder> responders = new ArrayList<Responder>();

	/*
	 * Loads textfiles located in a fixed directory textfiles/.. and creates
	 * responders which are then added to the arraylist.
	 */
	public void load() {
		try {
			System.out.println("Loading...");
			BufferedReader brQuestions = new BufferedReader(new FileReader("textfiles/questions.txt"));
			BufferedReader brResponses = new BufferedReader(new FileReader("textfiles/responses.txt"));
			BufferedReader brProfanity = new BufferedReader(new FileReader("textfiles/profanity.txt"));
			
			String questions = brQuestions.readLine();
			String responses = brResponses.readLine();		

			// Initialize Responders
			while (questions != null && responses != null) {
				ArrayList<String> additionalWords = new ArrayList<>();
				String[] q = questions.split("\\|");
				String[] r = responses.split("\\|");
				
				if (!q[0].contentEquals("#")) {

					responders.add(new Responder(q, r));

				}
				//read next line
				
				for (String word:q) {
					additionalWords.addAll(SynonymCheckerJWNL.getSynonyms(word));
 				}
				String[] newQ = new String[additionalWords.size()];
				for (int i=0; i<additionalWords.size();i++) {
					newQ[i] = additionalWords.get(i);
				}
				
				
				if (q[0].equals("*exceptions*")) {
					exceptionResponder = new Responder(q,r);
				}
				else {
					responders.add(new Responder(newQ, r));
				}
				// For next round

				questions = brQuestions.readLine();
				responses = brResponses.readLine();
			}
			String profanity = brProfanity.readLine();
			// add profanity words to arraylist
			while (profanity != null) {
				profanityFilter.add(profanity.toLowerCase());
				profanity = brProfanity.readLine();
			}
			brQuestions.close();
			brResponses.close();
			brProfanity.close();
			System.out.println("Loading Complete.");
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}

	/*
	 * Returns a responder based on the input string. The responder with the most
	 * matches will be returned. Returns: Responder that contains the most amount of
	 * matches within its strings.
	 */
	public Responder getResponder(String input) {
		int max = 0;
		Responder select = null;
		
		String[] wordsArr = input.split(" ");
		for(String s: wordsArr) System.out.print(s+", ");
		
		// Run words through PorterStemmer
		if (wordsArr.length > 0) {
			for (int i = 0; i < wordsArr.length; i++) {
				String wLetterOnly = wordsArr[i].replaceAll("[^a-zA-Z ]", "");
				char[] wordChars = wLetterOnly.toLowerCase().toCharArray();
				for (char c: wordChars) System.out.print(c+", ");

				PorterStemmer ps = new PorterStemmer(wordChars); // Create PorterStemmer object
				ps.stem(); // Stemming process
				String processedWord = ps.toString();

				if (processedWord.length() > 0)
					wordsArr[i] = processedWord; // Replace processed word back into match
			}
		}
		
		for (Responder r : responders) {
			if (r.check(wordsArr) > max) {
				max = r.check(wordsArr);
				select = r;
			}
		}
		return select; // Returns null if max==0
	}

	/*
	 * Returns a response for chatbot object.
	 */
	public String respond(String input) {
		try {
			String[] temp = input.split(" ");
			
			// Run words through PersonDetector
			PersonDetector pd = new PersonDetector(temp);
			pd.getPersons();	// Print persons detected in console
			
			for (String s : temp) {
				// Cusswords filter
				if (profanityFilter.contains(s)) {
					return "Please stop cussing.";
				}
				
				// Check for POSTagger option
				if (s.toLowerCase().contains("tagthis")) {
					String response = input.replace("tagthis", "");
					POSTagger tagger = new POSTagger(response);
					return tagger.getPOSTags();
				}
			}
			Responder r = getResponder(input.toLowerCase());
			if (r != null)
				return r.respond();
			else
				return exceptionResponder.respond(); // if no matches are found
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Respond method error.");
			return "I don't know what just happened. My response function broke down.";
		}

	}
}
