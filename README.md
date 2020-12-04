# COSC310 Project

__Members:__

Ryan Dobi<br>Jace Lai<br>Miguel Villarreal<br>Leif Coopman<br>Hudson Bishop<br>Denise Khoo

# Overview

Our project is a rule-based chatbot that enjoys conversing with you about hockey, basketball and climbing. It is able to carry out dialogue with the user for at least 30 turns.

## How to Use

Our chatbot can be used through a simple GUI by running the executable `run-chatbot` jar file. The user enters a response at the bottom of the window and the bot prints its reply accordingly.

# Structure

The chatbot accepts text-based inputs from the user and matches it to the most probable question in its repertoire (`questions.txt`). It then returns a corresponding randomly selected canned response from `responses.txt` to simulate conversation.

`Chatbot.java` is the main program that executes our chatbot. It processes text files `questions.txt`, `responses.txt`, and `profanity.txt` and initializes a Responder instance from `Responder.java` to handle the matching of user input to a suitable response.

# Features

## Simple GUI

Our chatbot uses a simple java-based GUI for a more user-friendly experience and the ability for the user to track the recent history of the conversation.

## Topics

Topics of conversation include hockey, basketball and climbing.

## No-match Responses

For user responses that are beyond the scope of understanding of the chatbot, statements like "I'm sorry. I don't understand." and "Sorry, could you repeat yourself?" are made to prompt the user to make an appropriate response (ie. that the chatbot would understand).

## Stemming

Stemming reduces inflected words down to their root words to allow related words to map to the same stem. For instance, the words "played", "playing", "plays" all map to the stem "play". The algorithm used to implement this is the Porter Stemmer algorithm adapted from https://tartarus.org/martin/PorterStemmer/.

## Spell Checker

https://github.com/boyter/java-spelling-corrector/blob/master/src/com/boyter/SpellingCorrector/SpellingCorrector.java

# Limitations

- Unable to handle incorrect spellings (Eg. "hockye" isn't assumed to be the same as "hockey")
- Doesn't utilize AI to generate "truly intelligent" responses (Eg. Unable to discuss topics outside the scope of keywords in `questions.txt`, Unable to understand humour or sarcasm)
- Only handles pure text (ie. No links, images, emojis etc.)
