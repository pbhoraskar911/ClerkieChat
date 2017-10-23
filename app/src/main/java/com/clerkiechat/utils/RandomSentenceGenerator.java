package com.clerkiechat.utils;

import com.clerkiechat.model.MessageData;

import java.util.Random;

/**
 * Created by Pranav Bhoraskar
 */

public class RandomSentenceGenerator {
    private String userInputText;
    String sentence;

    public String getUserInputText() {
        return userInputText;
    }

    public void setUserInputText(String userInputText) {
        this.userInputText = userInputText;
    }

    public String generateSentence(String currentUsername) {
        sentence = getUserInputText();

        if (sentence.equalsIgnoreCase("hello") || sentence.equalsIgnoreCase("Hi")
                || sentence.equalsIgnoreCase("Hi Clerkie")) {
            sentence = "Hello there.";
            return sentence;
        }

        else if (sentence.equalsIgnoreCase("good morning")
                || sentence.equalsIgnoreCase("goodmorning")) {
            sentence = "Good Morning. Have a great and eventful day ahead.";
            return sentence;
        }

        else if (sentence.equalsIgnoreCase("good afternoon")
                || sentence.equalsIgnoreCase("goodmorning")) {
            sentence = "Good Afternoon. I hope you are planning for a beautiful lunch.";
            return sentence;
        }

        else if (sentence.equalsIgnoreCase("good evening")
                || sentence.equalsIgnoreCase("goodevening")) {
            sentence = "Good Evening. I hope you had a wonderful Tea with Snacks.";
            return sentence;
        }

        else if (sentence.equalsIgnoreCase("good night")
                || sentence.equalsIgnoreCase("goodnight")) {
            sentence = "Good Night. Have a sound sleep.";
            return sentence;
        }

        else if (sentence.equalsIgnoreCase("bye") || sentence.equalsIgnoreCase("goodbye")
                || sentence.equalsIgnoreCase("good bye")) {
            sentence = "Good Bye. Have a great day Ahead.";
            return sentence;
        }

        else if (sentence.equalsIgnoreCase("how are you?")
                || sentence.equalsIgnoreCase("how are you")
                || sentence.equalsIgnoreCase("how are u")
                || sentence.equalsIgnoreCase("how are u?")
                || sentence.equalsIgnoreCase("how are u doing?")
                || sentence.equalsIgnoreCase("how are u doing")) {
            sentence = "I am good. Thank you for asking.";
            return sentence;
        }
        else if (sentence.equalsIgnoreCase("what are you doing?")
                || sentence.equalsIgnoreCase("what are you doing")
                || sentence.equalsIgnoreCase("what are u doing?")
                || sentence.equalsIgnoreCase("what are u doing")) {
            sentence = "I am currently talking to you.";
            return sentence;

        }
        else if (sentence.equalsIgnoreCase("what is the time?")
                || sentence.equalsIgnoreCase("what is the time now?")
                || sentence.equalsIgnoreCase("what is the time")
                || sentence.equalsIgnoreCase("what is the time now")
                || sentence.equalsIgnoreCase("what's the time?")
                || sentence.equalsIgnoreCase("what's the time")
                || sentence.equalsIgnoreCase("time")
                || sentence.equalsIgnoreCase("time?")) {
            MessageData messageData = new MessageData();
            sentence = "The current time is : " + messageData.getFormattedTime();
            return sentence;

        }
        else if (sentence.equalsIgnoreCase("what is the date?")
                || sentence.equalsIgnoreCase("what is the date today?")
                || sentence.equalsIgnoreCase("what is the date")
                || sentence.equalsIgnoreCase("what is the date today")
                || sentence.equalsIgnoreCase("what's the date?")
                || sentence.equalsIgnoreCase("what's the date")
                || sentence.equalsIgnoreCase("date")
                || sentence.equalsIgnoreCase("date?")) {
            MessageData messageData = new MessageData();
            sentence = "Today's date is : " + messageData.getFormattedDate();
            return sentence;

        }
        else if (sentence.equalsIgnoreCase("what is your name?")
                || sentence.equalsIgnoreCase("what is your name")
                || sentence.equalsIgnoreCase("what's your name")
                || sentence.equalsIgnoreCase("what's your name?")
                || sentence.equalsIgnoreCase("your name")
                || sentence.equalsIgnoreCase("your name?")) {
            sentence = "My name is Clerkie Bot. You can call me Clerkie.";
            return sentence;
        }

        else if (sentence.equalsIgnoreCase("what is my name?")
                || sentence.equalsIgnoreCase("what is my name")
                || sentence.equalsIgnoreCase("what's my name")
                || sentence.equalsIgnoreCase("what's my name?")
                || sentence.equalsIgnoreCase("my name")
                || sentence.equalsIgnoreCase("my name?")) {
            sentence = "Your name is " + currentUsername;
            return sentence;
        }

        else if (sentence.equalsIgnoreCase("name?")
                || sentence.equalsIgnoreCase("name")) {
            sentence = "I am confused. Whose name?";
            return sentence;
        }

        else if (sentence.equalsIgnoreCase("thanks")
                || sentence.equalsIgnoreCase("thankyou")
                || sentence.equalsIgnoreCase("thank you.")
                || sentence.equalsIgnoreCase("thank you..")
                || sentence.equalsIgnoreCase("thank you")) {
            sentence = "You are welcome.";
            return sentence;
        }

        else {

            sentence = fetchData();

            System.out.println("the sentence is : " + sentence);
            return sentence;
        }
    }

    private String fetchData() {
        Random r = new Random();
        String[] nouns = {"I", "You", "He", "She", "Preston", "Noah", "Porky"};
        String[] verbs = {" walk", " run", " jump", " swim", " dance", " sing"};
        String[] adj = {" fast", " slow", " funny", " happily", " bad"};
        int n1 = r.nextInt(nouns.length);
        int n2 = r.nextInt(verbs.length);
        int n3 = r.nextInt(adj.length);
        if (n1 >= 2) {
            return nouns[n1] + verbs[n2] + "s" + adj[n3] + ".";
        }
        else {
            return nouns[n1] + verbs[n2] + adj[n3] + ".";
        }
    }


}