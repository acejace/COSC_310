package chatbot;

import javax.swing.SwingUtilities;

public class Driver {

	public static void main(String[] args) {
		try {
			// Instantiate chatbot
			ChatBot cb = new ChatBot();
			cb.load();
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new ChatbotGUI(cb);
				}
			});

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
