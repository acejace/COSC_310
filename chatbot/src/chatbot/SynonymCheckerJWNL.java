package chatbot;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.data.IndexWord;
import net.sf.extjwnl.data.IndexWordSet;
import net.sf.extjwnl.data.POS;
import net.sf.extjwnl.data.PointerType;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.Word;
import net.sf.extjwnl.data.list.PointerTargetNodeList;
import net.sf.extjwnl.data.list.PointerTargetTree;
import net.sf.extjwnl.data.relationship.AsymmetricRelationship;
import net.sf.extjwnl.data.relationship.Relationship;
import net.sf.extjwnl.data.relationship.RelationshipFinder;
import net.sf.extjwnl.data.relationship.RelationshipList;
import net.sf.extjwnl.dictionary.Dictionary;
import net.sf.extjwnl.utilities.Examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import net.sf.extjwnl.dictionary.*;

public class SynonymCheckerJWNL {
	/*
	 * Get synonyms for verbs based on input
	 */
	public static ArrayList<String> getMatchingVerbs(Dictionary d, String input) {
		try {
			ArrayList<String> matches = new ArrayList<>();
			IndexWord word = d.lookupIndexWord(POS.VERB, input);
			Object[] synset = word.getSenses().toArray();
			for (int i = 0; i < synset.length; i++) {
				Synset temp = (Synset) synset[i];
				List<Word> words = temp.getWords();
				for (Word w : words) {
					matches.add(w.getLemma());
				}
			}
			return matches;

		} catch (Exception e) {
			
		}
		return null;
	}
	/*
	 * Get synonyms for nouns based on input
	 */
	public static ArrayList<String> getMatchingNouns(Dictionary d, String input) {
		try {
			ArrayList<String> matches = new ArrayList<>();
			IndexWord word = d.lookupIndexWord(POS.NOUN, input);
			Object[] synset = word.getSenses().toArray();
			for (int i = 0; i < synset.length; i++) {
				Synset temp = (Synset) synset[i];
				List<Word> words = temp.getWords();
				for (Word w : words) {
					matches.add(w.getLemma());
				}
			}
			return matches;

		} catch (Exception e) {
			
		}
		return null;
	}
	/*
	 * Get synonyms for adjectives based on input
	 */
	public static ArrayList<String> getMatchingAdjs(Dictionary d, String input) {
		try {
			ArrayList<String> matches = new ArrayList<>();
			IndexWord word = d.lookupIndexWord(POS.ADJECTIVE, input);
			Object[] synset = word.getSenses().toArray();
			for (int i = 0; i < synset.length; i++) {
				Synset temp = (Synset) synset[i];
				List<Word> words = temp.getWords();
				for (Word w : words) {
					matches.add(w.getLemma());
				}
			}
			return matches;

		} catch (Exception e) {
			
		}
		return null;
	}
	/*
	 * Get synonyms for verbs,nouns and adjectives based on input. Removes duplicate words and returns an ArrayList of synonyms.
	 */
	public static ArrayList<String> getSynonyms(String input) throws JWNLException {
		try {
			Dictionary d = Dictionary.getDefaultResourceInstance();
			ArrayList<String> matches = new ArrayList<>();
			
			ArrayList<String> verbs = getMatchingVerbs(d, input);
			ArrayList<String> nouns = getMatchingNouns(d, input);
			ArrayList<String> adjs = getMatchingAdjs(d, input);
			
			matches.add(input);
			if (verbs!=null) matches.addAll(verbs);
			
			if (nouns!=null) matches.addAll(nouns);
			
			if (adjs!=null) matches.addAll(adjs);

	         
	        ArrayList<String> listWithoutDuplicates = new ArrayList<>();
	        for (String word: matches) {
	        	if (!listWithoutDuplicates.contains(word)) {
	        		listWithoutDuplicates.add(word);
	        	}
	        }
			return listWithoutDuplicates;
			
		} catch (Exception e) {
			System.out.println("Retrieving synonyms failed.");
			System.exit(-1);
		}
		return null;
	}
	
}
