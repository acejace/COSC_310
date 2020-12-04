package chatbot;

import java.util.ArrayList;

public class Responder {
	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> responses = new ArrayList<String>();

	public Responder(String[] q, String[] a) {
		for (String question : q) {
			questions.add(question);
		}

		for (String response : a) {
			responses.add(response);
		}
	}

	/*
	 * Checks user-entered input and
	 * 1. Runs words through PersonDetector
	 * 2. Runs words through PorterStemmer
	 * 3. Counts number of matches to questions.txtx
	 * Returns: count of matches to questions
	 */
	public int check(String[] q) {

		try {
			int count = 0;

			// Count matches
			for (String word : q) {
				if (questions.contains(word))
					count++;

			}
			return count;
		} catch (Exception e) {
			e.printStackTrace();;
			return -1;
		}
	}

	// Returns a string randomly selected from the array of responses.
	public String respond() {
		int rand = (int) (Math.random() * responses.size());
		String response = responses.get(rand);
		String temp;
		rand = (int) (Math.random() * 3);
		temp = response;

		return temp;
	}
}
