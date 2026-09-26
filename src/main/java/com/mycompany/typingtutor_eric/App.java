package com.mycompany.typingtutor_eric;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import javafx.scene.control.TextField;



/**
 * JavaFX Typing Tutor app
 * @author Éric André Evsei
 */
public class App extends Application {
    
    /**list of letters*/
    private final static char letters[] = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
    
    /**Keep track of on which prompt the user is on*/
    private int promptNum = 1;

    @Override
    public void start(Stage mainStage) {
        
        //Create random prompts and store them
        PromptGenerator textGen = new PromptGenerator(7, 12, 12);
        String texts[] = textGen.CreatePrompts();
        
        //Creation of root and keys instances
        BorderPane root = new BorderPane();
        GridPane mainGridPane = new GridPane();
        HBox top = new HBox(95);
        GridPane center = new GridPane();
        GridPane bottom = new GridPane();
        
        //TextField and Label instances
        Label promptTracker = new Label("1 of " + texts.length); //Track which prompt the users at
        TextField userText = new TextField(); //To store what the user wants to time in a text field
        Label trackerRight = new Label("RIGHT: 0"); //Track correct characters
        Label trackerWrong = new Label("WRONG: 0"); //Track incorrect characters
        Label isPromptTyped = new Label("NOT DONE"); //Track if the text as been typed up correctly
        isPromptTyped.setId("promptNotDone");
        Label keyPressed = new Label("");
        Label prompt = new Label(texts[0]);
        prompt.setMinWidth(200);
        
        //Create next and reset button
        Button nextButton = new Button("next");
        nextButton.setDisable(true);
        Button resetButton = new Button("reset");
        
        //Create and add keyboard buttons to the bottom pane
        Button buttons[] = Create_Add_Buttons(bottom);
        
        //Add everything to their respective panes
        top.getChildren().addAll(resetButton, promptTracker, nextButton);
        center.add(keyPressed, 0, 0);
        center.add(prompt, 0, 1);
        center.add(userText, 0, 2);
        mainGridPane.add(top, 0, 0);
        mainGridPane.add(center, 0, 2);
        mainGridPane.add(trackerRight, 0, 3);
        mainGridPane.add(trackerWrong, 0, 4);
        mainGridPane.add(isPromptTyped, 0, 5);
        mainGridPane.add(bottom, 0, 6);
        
        //Modifie a few columns so that they're larger and don't disturb other nodes
        GridPane.setColumnSpan(buttons[26], 2);
        GridPane.setColumnSpan(buttons[27], 2);
        
        //Add margins so that each sections have space in between
        GridPane.setMargin(top, new Insets(0, 0, 20, 0));
        GridPane.setMargin(center, new Insets(0, 0, 20, 0));
        
        //Center the main grid pane and add the gridPane with all the buttons, textfields and labels to the root
        mainGridPane.setAlignment(Pos.CENTER);
        root.setCenter(mainGridPane);
        
        //Event handler when nextButton is clicked
        nextButton.setOnAction(event -> {
            
            //Prompt number goes up to keep track of where the user is at which prompt
            promptNum++;
            
            //update promptTracker and change the prompt to the next one
            promptTracker.setText((promptNum) + " of " + texts.length);
            prompt.setText(texts[promptNum]);
            
            //reset the user's text field
            userText.setText("");
            
            //Reset the nomber of right and wrongly typed characters
            trackerRight.setText("RIGHT: 0");
            trackerWrong.setText("WRONG: 0");
            
            //Reset the label that tells the user if they are done or not back to default settings
            isPromptTyped.setText("NOT DONE");
            isPromptTyped.setId("promptNotDone");
            
            //Disable the next button
            nextButton.setDisable(true);
        });
        
        //Event handler when nextReset is clicked
        resetButton.setOnAction(event -> {
            
            //Reset the prompt number
            promptNum = 1;
            
            //reset promptTracker and change the prompt to the first one
            promptTracker.setText(promptNum + " of " + texts.length);
            prompt.setText(texts[promptNum]);
            
            //reset the user's text field
            userText.setText("");
            
            //Reset the nomber of right and wrongly typed characters
            trackerRight.setText("RIGHT: 0");
            trackerWrong.setText("WRONG: 0");
            
            //Reset the label that tells the user if they are done or not back to default settings
            isPromptTyped.setText("NOT DONE");
            isPromptTyped.setId("promptNotDone");
            
            //Disable the next button
            nextButton.setDisable(true);
        });
        
        //Ceate and show the scene
        Scene scene = new Scene(root, 400, 400);
        scene.getStylesheets().add("Styles.css");
        mainStage.setScene(scene);
        mainStage.show();
        
        //Event handler for when keys are pressed when the user is typing in the text field
        userText.setOnKeyPressed(event -> {
            
            //Get the event keycode name as a String
            String keyText = event.getCode().getName();
            
            //Should the key not be handled, then it stays true
            boolean notHandled = true;
            
            //Iterate through the buttons list
            //If the key pressed exists, then update the keyPressed label and put notHandled to false since the program handles it
            //and also change the style of the specific button that represents the key pressed physically 
            for(Button button : buttons){
                if(keyText.equals(button.getText().toUpperCase())){
                    button.setId("keyboardBtnPressed");
                    keyPressed.setText(keyText.toLowerCase());
                    keyPressed.setId("#keyHandled");
                    notHandled = false;
                }else if(button.getText().equals("Space") && keyText.equals("Space")){
                    button.setId("keyboardBtnPressed");
                    keyPressed.setText("Space");
                    keyPressed.setId("#keyHandled");
                    notHandled = false;
                }else if(button.getText().equals("Shift") && keyText.equals("Shift")){
                    button.setId("keyboardBtnPressed");
                    keyPressed.setText("Shift");
                    keyPressed.setId("#keyHandled");
                    notHandled = false;
                }
            }
            
            //If the key isn't handled, then update the keyPressed label with the appropriate text and change its style to red
            if(notHandled){
                keyPressed.setText("Not handled");
                keyPressed.setId("keyNotHandled");
            }
        });
        
        //Event handler for when keys are released when the user is typing in the text field
        scene.setOnKeyReleased(event -> {
            
            //If the key should be handled, then it stays true
            boolean keyIsHandled = true;
            
            //Get the text in the text field
            String userTypedText = userText.getText();
            
            //To change the button's style back to default
            for(Button button : buttons){
                button.setId("keyboardBtnReleased");
            }
            
            //We check if the text field isn't empty
            if(!userTypedText.equals("")){
                
                //Get the last character the user typed
                char targetChar = userTypedText.charAt(userText.getText().length()-1);
                
                //If it is not a space then we check if it's a letter
                if(userTypedText.charAt(userTypedText.length()-1) != ' '){
                    keyIsHandled = false;
                    
                    //Iterate through all 26 letters
                    for(char letter : letters){
                        
                        //Get the letter in uppercase
                        char upperCaseChar = Character.toUpperCase(letter);
                        
                        //Check if the letter is uppercase or lowercase
                        //if it is then it's a handled key and we break out of the loop
                        if( targetChar == letter || targetChar == upperCaseChar){
                            keyIsHandled = true;
                            break;
                        }
                    }
                }
            }
            
            //If the key is not handled then go ahead and remove it from the text field
            if(!keyIsHandled){
                userText.setText(userText.getText().substring(0, userText.getText().length()-1));
            }
            
            //Set the caret position to the last character added to the text field
            userText.positionCaret(userText.getText().length());
            
            //To update the trackers
            Segments(userText, prompt, isPromptTyped, trackerWrong, trackerRight, nextButton);
        });
    }

    public static void main(String[] args) {
        launch();
    }
    
    /**
     * Creates and adds necessary buttons to create the keyboard for the typing tutor program
     * @param gridPane represents the main gridPane where everything is placed
     * @return buttons which is the set of all the buttons used to make the keyboard
     */
    public static Button[] Create_Add_Buttons(GridPane gridPane){
        
        //New list of 28 buttons that will represent the 28 keys that the user can use on a physical keyboard
        Button buttons[] = new Button[28];
        
        //For loop that creates button instances corresponding to different keys on a keyboard
        //and then stores them into a list
        for(int i = 0; i < buttons.length; i++){
            switch(i){
                case 26:
                    buttons[i] = new Button("Shift");
                    buttons[i].setMinWidth(50);
                    break;
                case 27:
                    buttons[i] = new Button("Space");
                    buttons[i].setMinWidth(50);
                    break;
                default:
                    buttons[i] = new Button("" + letters[i]);
                    buttons[i].setMinWidth(30);
                    break;
            }
            buttons[i].setId("keyboardBtnReleased");
        }
        
        //For loop to iterate through the buttons list and adds them to the gridPane following the Label and TextField
        for(int i = 0; i < buttons.length; i++){
            if(i >= 0 && i < 10){
                gridPane.add(buttons[i], i, 0);
            }else if(i >= 10 && i < 19){
                gridPane.add(buttons[i], i-9, 1);
            }else if(i >= 19 && i < 26){
                gridPane.add(buttons[i], i-17, 2);
            }else{
                switch(i){
                    case 27:
                       gridPane.add(buttons[i], i-21, 3);
                       break;
                    default:
                        gridPane.add(buttons[i], i-22, 3);
                }
            }
        }
        
        return buttons;
    }
    
    /**
     * Cuts the text in the textField and prompt text into segments and stores them into their respective list
     * @param userText is the text field where the user types
     * @param prompt is the label that contains the prompt that the user is expected to type
     * @param trackerText is the label that tells the user if they're done with the prompt or not
     * @param trackerWrong is the label that keeps counts of the characters that are wrong
     * @param trackerRight is the label that keeps counts of the characters that are right
     * @param nextButton is the button to go to the next prompt
     */
    public static void Segments(TextField userText, Label prompt, Label trackerText, Label trackerWrong, Label trackerRight, Button nextButton){
        
        String word = "";
        
        //List of words from the prompt label
        ArrayList<String> promptWords = new ArrayList<>();
        
        //List of words from the text field
        ArrayList<String> textFieldWords = new ArrayList<>();
            
        //For loop to extract words from the prompt text and add them to the 1st list
        for(int i = 0; i < prompt.getText().length(); i++){
            if(prompt.getText().charAt(i) == ' '){
                promptWords.add(word);
                word = "";
            }else{
                word += prompt.getText().charAt(i);
            }
        }
                
        //Add the last word from the prompt
        promptWords.add(word);
        word = "";
                
        //For loop to extract words from the text field and add them to the 2nd list
        for(int i = 0; i < userText.getText().length(); i++){
            if(userText.getText().charAt(i) == ' '){
                textFieldWords.add(word);
                word = "";
            }else{
                word += userText.getText().charAt(i);
            }
        }
                
        //Add the last word from the text field
        textFieldWords.add(word);
                
        //Update the wrong and right label
        UpdateTrackers(promptWords, textFieldWords, prompt, trackerText, trackerRight, trackerWrong, userText, nextButton);
    }
    
    /**
     * Updates the wrong and right tracking labels
     * @param promptWords is a list of 'words' extracted from the prompt label
     * @param textFieldWords is a list of 'words' extracted from the text field
     * @param prompt is the label that contains the prompt that the user is expected to type
     * @param trackerText is the label that tells the user if they're done with the prompt or not
     * @param trackerRight is the label that keeps counts of the characters that are right
     * @param trackerWrong is the label that keeps counts of the characters that are wrong
     * @param userText is the text field where the user types
     * @param nextButton is the button to go to the next prompt
     */
    public static void UpdateTrackers(ArrayList<String> promptWords, ArrayList<String> textFieldWords, Label prompt, Label trackerText, Label trackerRight, Label trackerWrong, TextField userText, Button nextButton){

        //Gets the minimum index between the two arraylists 
        int indexOf = Math.min(promptWords.size(), textFieldWords.size());
        
        int right = 0;
        int wrong = 0;
        
        //For loop that iterates through the arraylists
        for(int i = 0; i < indexOf; i++){
            
            //Gets the minimum length of the two words
            int minLen = Math.min(promptWords.get(i).length(), textFieldWords.get(i).length());
                
            //With the help of minLen, we can iterate through each word from the lists and compare their characters without going out of bounds
            for(int j = 0; j < minLen; j++){
                if(promptWords.get(i).charAt(j) == textFieldWords.get(i).charAt(j)){
                    right++;
                }else{
                    wrong++;
                }
            }
            
            //If the word at iteration i is bigger than the one in the prompt then add more wrong characters to the tracker
            if(promptWords.get(i).length() < textFieldWords.get(i).length()){
                wrong += textFieldWords.get(i).length() - promptWords.get(i).length();
            }
        }
            
        //Update the labels that track the right and wrong characters
        trackerRight.setText("RIGHT: " + right);
        trackerWrong.setText("WRONG: " + wrong);
        
        //Check if the user typed all off the right characters in order to say that the user is done with the prompt
        if(prompt.getText().equals(userText.getText())){
            trackerText.setText("DONE");
            trackerText.setId("promptDone");
            nextButton.setDisable(false);
        }else{
            trackerText.setText("NOT DONE");
            trackerText.setId("promptNotDone");
            nextButton.setDisable(true);
        }
        
    }
}
