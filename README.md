# COSC310 Project

__Members:__

Ryan Dobi<br>Jace Lai<br>Miguel Villarreal<br>Leif Coopman<br>Hudson Bishop<br>Denise Khoo

# Overview

Our project is a rule-based chatbot that enjoys conversing with you about hockey, basketball and climbing. It is able to carry out dialogue with the user for at least 30 turns.

## How to Use

Our chatbot can be used through a simple GUI by running the executable `chatbot` jar file. The user enters a response at the bottom of the window and the bot prints its reply accordingly.
Alternatively, you can run the Driver.java file in an IDE.

# Structure

The chatbot accepts text-based inputs from the user and matches it to the most probable question in its repertoire (`questions.txt`). It then returns a corresponding randomly selected canned response from `responses.txt` to simulate conversation.

`Chatbot.java` is the main program that executes our chatbot. It processes text files `questions.txt`, `responses.txt`, and `profanity.txt` and initializes a Responder instance from `Responder.java` to handle the matching of user input to a suitable response.

# Features

## Simple GUI

Our chatbot uses a simple java-based GUI for a more user-friendly experience and the ability for the user to track the recent history of the conversation.

## Various Topics

Topics of conversation include hockey, basketball and climbing.

## Synonym Recognition

Takes words retrieved from the text files, and automatically finds common synonyms for keywords with the use of JWNL API. Helps with recognizing different words which enables better conversation undertandability.

## Profanity Filter

Profanity filter is a feature that recognizes when the user has entered any profanity. The program will then remind the user to stop using profanity. This feature is used to help keep the chatbot kid friendly and help with overall flow. A sample example of a reply from the chatbot when profanity is entered is “Please do not swear!”.

## No-match Responses

For user inputs beyond the scope of understanding of the chatbot, automated responses like "I'm sorry. I don't understand." and "Sorry, could you repeat yourself?" are printed to as a 'catch' feature for such exceptions.

## Stemming

Stemming reduces inflected words entered by the user down to their root words to allow related words to map to the same stem. For instance, the words "played", "playing", "plays" all map to the stem "play". The algorithm used to implement this is the Porter Stemmer algorithm adapted from https://tartarus.org/martin/PorterStemmer/.

## Spell Checker

Spell check is a feature used to attempt any spelling mistakes that the user enters. This feature assumes that the user got the first letter of the word correct, but will change further characters. This feature is important to make sure the user is entering accurate information. An example of this feature is if the user enters “helli”. In this case the program would correct the world to “hello”. Refer to: https://github.com/boyter/java-spelling-corrector/blob/master/src/com/boyter/SpellingCorrector/SpellingCorrector.java

## Named Entity Recognition (NER) - Name Detector

If names appear in the user's input, the chatbot uses tools and the NER-Person model from the OpenNLP library to detect them. The required libraries and models were obtained from https://opennlp.apache.org/download.html and http://opennlp.sourceforge.net/models-1.5/ respectively.

## Part-of-speech (POS) Tagging

Users can opt to display the POS tagging of their inputs if the keyword "TagThis" is entered alongside the statement they wish to be POS-tagged. The POS tagging model was provided by OpenNLP and may not be 100% accurate - as with all POS tagging models at the time of this writing. The required libraries and models were obtained from https://opennlp.apache.org/download.html and http://opennlp.sourceforge.net/models-1.5/ respectively.

# Limitations

- Unable to handle incorrect spellings (Eg. "hockye" isn't assumed to be the same as "hockey")
- Doesn't utilize AI to generate "truly intelligent" responses (Eg. Unable to discuss topics outside the scope of keywords in `questions.txt`, Unable to understand humour or sarcasm)
- Only handles pure text (ie. No links, images, emojis etc.)
- Requires an initial load time of approximately 20 seconds
