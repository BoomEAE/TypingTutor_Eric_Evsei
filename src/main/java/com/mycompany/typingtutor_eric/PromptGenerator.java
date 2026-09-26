/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.typingtutor_eric;
import java.util.Random;

/**
 *Generates random prompts according to a list of words
 * @author Eric
 */
public class PromptGenerator {
    
    /**A list of 200 common words*/
    private String commonWords[] = {"the", "be", "is", "am", "are", "was", "were", "of", "and", "a",
                                    "to", "in", "he", "have", "it", "that", "for", "they", "I", "with",
                                    "as", "nol", "on", "see", "at", "by", "this", "we", "you", "do",
                                    "but", "from", "or", "which", "one", "would", "all", "will", "there", "say",
                                    "who", "make", "when", "can", "more", "if", "no", "man", "out", "other",
                                    "so", "what", "time", "up", "go", "about", "than", "into", "could", "state",
                                    "only", "new", "year", "some", "take", "come", "these", "know", "see", "use",
                                    "get", "like", "then", "first", "any", "work", "now", "may", "such", "give",
                                    "over", "think", "most", "even", "find", "day", "also", "after", "way", "many",
                                    "must", "look", "before", "great", "back", "through", "long", "where", "much", "should",
                                    "well", "people", "down", "own", "just", "because", "good", "each", "those", "feel",
                                    "seem", "how", "high", "too", "place", "little", "world", "very", "still", "",
                                    "nation", "hand", "old", "life", "tell", "write", "become", "here", "show", "house",
                                    "both", "between", "need", "mean", "call", "develop", "under", "last", "right", "move",
                                    "thing", "general", "school", "never", "same", "another", "begin", "while", "number", "part",
                                    "turn", "real", "leave", "might", "want", "point", "form", "off", "child", "few",
                                    "small", "since", "against", "ask", "late", "home", "interest", "large", "person", "end",
                                    "open", "public", "follow", "during", "present", "without", "again", "hold", "govern", "around",
                                    "possible", "head", "consider", "word", "program", "problem", "however", "lead", "system", "set",
                                    "order", "eye", "plan", "run", "keep", "face", "fact", "group", "play", "stand"};
    
    /**The minimum words that can exist in a prompt*/
    int minWords;
    
    /**The maximum words that can exist in a prompt*/
    int maxWords;
    
    /**The number of prompts for it to generate*/
    int numPrompts;
    
    /**
     * TextGenerator constructor where you can choose the minimum and maximum of words that can be incuded in a prompt and the number of prompts to generate
     * @param minWords is the minimum words that can exist in a prompt
     * @param maxWords The maximum words that can exist in a prompt
     * @param numPrompts The number of prompts for it to generate
     */
    public PromptGenerator(int minWords, int maxWords, int numPrompts){
        this.minWords = minWords;
        this.maxWords = maxWords;
        this.numPrompts = numPrompts;
    }
    
    /**
     * Method to create the prompts
     * @return prompts list with newly created prompts
     */
    public String[] CreatePrompts(){
        
        //Random
        Random rand = new Random();
        
        //A new list of size equal to the number of prompts chosen
        String prompts[] = new String[numPrompts];
        
        //Iterate through the list of blank prompts
        for(int i = 0; i < numPrompts; i++){
            
            //The program chooses a random number of words to include into the prompt according to the min and max of words chosen
            int numWords = rand.nextInt((maxWords+1) - minWords) + minWords;

            String prompt = "";
            
            //For each word, the program chooses a random word from the list of words
            //When it reaches the last word to add, don't add a space after adding the word
            for(int j = 0; j < numWords; j++){
                if(j == numWords-1){
                    prompt += commonWords[rand.nextInt(commonWords.length)];
                }else{
                    prompt += commonWords[rand.nextInt(commonWords.length)] + " ";
                }
            }
            
            //Add the newly made prompt to the list of prompts
            prompts[i] = prompt;
        }
        
        //return every prompt created
        return prompts;
    }
}
