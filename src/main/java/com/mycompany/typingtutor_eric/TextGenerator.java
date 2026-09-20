/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.typingtutor_eric;
import java.util.Random;

/**
 *
 * @author Eric
 */
public class TextGenerator {
    private String commonWords[] = {"the", "be", "is", "am", "are", "was", "were", "of", "and", "a",
                                    "to", "in", "he", "have", "it", "that", "for", "they", "I", "with",
                                    "as", "nol", "on", "se", "at", "by", "this", "we", "you", "do",
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
    
    int minWords;
    int maxWords;
    int numText;
    
    public TextGenerator(int minWords, int maxWords, int numText){
        this.minWords = minWords;
        this.maxWords = maxWords;
        this.numText = numText;
    }
    
    public String[] CreateTexts(){
        Random rand = new Random();
        String texts[] = new String[numText];
        
        for(int i = 0; i < numText; i++){
            int numWords = rand.nextInt((maxWords+1) - minWords) + minWords;
            String text = "";
            
            for(int j = 0; j < numWords; j++){
                if(j == numWords-1){
                    text += commonWords[rand.nextInt(commonWords.length)];
                }else{
                    text += commonWords[rand.nextInt(commonWords.length)] + " ";
                }
            }
            
            texts[i] = text;
        }
        
        return texts;
    }
}
