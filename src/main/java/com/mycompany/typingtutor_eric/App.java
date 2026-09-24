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
import javafx.scene.input.KeyCode;



/**
 * JavaFX App
 */
public class App extends Application {
    
    private final static char letters[] = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
    private int counter = 0;

    @Override
    public void start(Stage mainStage) {
        //Texts
        TextGenerator textGen = new TextGenerator(7, 12, 12);
        String texts[] = textGen.CreateTexts();
        
        //Creation of root and keys instances
        BorderPane root = new BorderPane();
        GridPane mainGridPane = new GridPane();
        HBox top = new HBox(95);
        GridPane center = new GridPane();
        GridPane bottom = new GridPane();
        
        //TextField and Label instances
        Label text = new Label(texts[0]);
        text.setMinWidth(200);
        Label textCounter = new Label("1 of " + texts.length);
        TextField userText = new TextField();
        Label trackerRight = new Label("RIGHT: 0"); //Track correct characters
        Label trackerWrong = new Label("WRONG: 0"); //Track incorrect characters
        Label trackerText = new Label("NOT DONE");
        Label keyPressed = new Label("");
        trackerText.setStyle("-fx-text-fill:red");
        Button nextButton = new Button("next");
        Button resetButton = new Button("reset");
        
        //Create and add buttons
        Button buttons[] = Create_Add_Buttons(bottom);
        
        //Add the label and textfield
        top.getChildren().addAll(resetButton, textCounter, nextButton);
        center.add(keyPressed, 0, 0);
        center.add(text, 0, 1);
        center.add(userText, 0, 2);
        mainGridPane.add(top, 0, 0);
        mainGridPane.add(center, 0, 2);
        mainGridPane.add(trackerRight, 0, 3);
        mainGridPane.add(trackerWrong, 0, 4);
        mainGridPane.add(trackerText, 0, 5);
        mainGridPane.add(bottom, 0, 6);
        
        //Modifie a few columns
        GridPane.setColumnSpan(buttons[26], 2);
        GridPane.setColumnSpan(buttons[27], 2);
        GridPane.setColumnSpan(nextButton, 2);
        GridPane.setColumnSpan(textCounter, 3);
        GridPane.setColumnSpan(resetButton, 2);
        
        
        //Add margins so that each sections have space in between
        GridPane.setMargin(top, new Insets(0, 0, 20, 0));
        GridPane.setMargin(center, new Insets(0, 0, 20, 0));
        
        //Add the gridPane with all the buttons, textfield and label to the root
        mainGridPane.setAlignment(Pos.CENTER);
        root.setCenter(mainGridPane);
        
        //
        nextButton.setOnAction(event -> {
            counter++;
            textCounter.setText((counter+1) + " of " + texts.length);
            text.setText(texts[counter]);
            userText.setText("");
            trackerRight.setText("RIGHT: 0");
            trackerWrong.setText("WRONG: 0");
            trackerText.setText("NOT DONE");
            trackerText.setStyle("-fx-text-fill:red");
        });
        
        //
        resetButton.setOnAction(event -> {
            counter = 0;
            textCounter.setText("1 of " + texts.length);
            text.setText(texts[counter]);
            userText.setText("");
            trackerRight.setText("RIGHT: 0");
            trackerWrong.setText("WRONG: 0");
            trackerText.setText("NOT DONE");
            trackerText.setStyle("-fx-text-fill:red");
        });
        
       
        
        //
        Scene scene = new Scene(root, 400, 400);
        
        mainStage.setScene(scene);
        mainStage.show();
        
        //
        userText.setOnKeyPressed(event -> {
            String keyText = event.getCode().getName();
            boolean notHandled = true;
            
            for(Button button : buttons){
                if(keyText.equals(button.getText().toUpperCase())){
                    changeButtonStyle(button, 1);
                    keyPressed.setText(keyText.toLowerCase());
                    keyPressed.setStyle("-fx-fill-color:white");
                    notHandled = false;
                }else if(button.getText().equals("Space") && keyText.equals("Space")){
                    changeButtonStyle(button, 1);
                    keyPressed.setText("Space");
                    keyPressed.setStyle("-fx-fill-color:white");
                    notHandled = false;
                }else if(button.getText().equals("Shift") && keyText.equals("Shift")){
                    changeButtonStyle(button, 1);
                    keyPressed.setText("Shift");
                    keyPressed.setStyle("-fx-fill-color:white");
                    notHandled = false;
                }
            }
            
            if(notHandled){
                keyPressed.setText("Not handled");
                keyPressed.setStyle("-fx-fill-color:red");
            }
        });
        
        //
        scene.setOnKeyReleased(event -> {
            boolean keyIsHandled = true;
            
            //To change the button's style back to default
            for(Button button : buttons){
                changeButtonStyle(button, 0);
            }
            
            //
            if(!userText.getText().equals("")){
                if(userText.getText().charAt(userText.getText().length()-1) != ' '){
                    keyIsHandled = false;
                    for(char character : letters){
                        if(userText.getText().charAt(userText.getText().length()-1) == character){
                            keyIsHandled = true;
                            break;
                        }
                    }
                }
            }
            
            //
            if(!keyIsHandled){
                userText.setText(userText.getText().substring(0, userText.getText().length()-1));
                
                //
                userText.positionCaret(userText.getText().length());
            }
            
            //
            Segments(userText, text, trackerText, trackerWrong, trackerRight);
        });
    }

    public static void main(String[] args) {
        launch();
    }
    
    //Creates and adds necessary buttons for the typing tutor program
    public static Button[] Create_Add_Buttons(GridPane gridPane){
        //
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
            changeButtonStyle(buttons[i], 0);
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
    
    //Sets a style sheet to a button
    public static void changeButtonStyle(Button button, int sheetNum /*0 being the default stylesheet and 1 being the alternative*/){
        String defaultCssSheet = "-fx-background-radius: 15; -fx-background-color: #FFD700";
        String alternativeCssSheet = "-fx-background-radius: 15; -fx-background-color: #CC5500";
        
        //We check which sheet as been selected by the program
        //and set the appropriate style sheet to the button according to the sheet number selected
        switch(sheetNum){
            case 0:
                button.setStyle(defaultCssSheet);
                break;
            case 1:
                button.setStyle(alternativeCssSheet);
                break;
        }
    }
    
    public static void Segments(TextField userText, Label text, Label trackerText, Label trackerWrong, Label trackerRight){
        
        String e = "";
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
            
        //
        for(int i = 0; i < text.getText().length(); i++){
            if(text.getText().charAt(i) == ' '){
                list1.add(e);
                e = "";
            }else{
                e += text.getText().charAt(i);
            }
        }
                
        //
        list1.add(e);
        e = "";
                
        //
        for(int i = 0; i < userText.getText().length(); i++){
            if(userText.getText().charAt(i) == ' '){
                list2.add(e);
                e = "";
            }else{
                e += userText.getText().charAt(i);
            }
        }
                
        //
        list2.add(e);
                
        TEST1(list1, list2, text, trackerText, trackerRight, trackerWrong, userText);
    }
    
    public static void TEST1(ArrayList<String> list1, ArrayList<String> list2, Label text, Label trackerText, Label trackerRight, Label trackerWrong, TextField userText){

        //
        int indexOf = Math.min(list1.size(), list2.size());
        int right = 0;
        int wrong = 0;
        
        //
        for(int i = 0; i < indexOf; i++){
            int minLen = Math.min(list1.get(i).length(), list2.get(i).length());
                
            for(int j = 0; j < minLen; j++){
                if(list1.get(i).charAt(j) == list2.get(i).charAt(j)){
                    right++;
                }else{
                    wrong++;
                }
            }
            
            if(list1.get(i).length() < list2.get(i).length()){
                wrong += list2.get(i).length() - list1.get(i).length();
            }
        }
            
        //
        trackerRight.setText("RIGHT: " + right);
        trackerWrong.setText("WRONG: " + wrong);
        
        if(text.getText().equals(userText.getText())){
                trackerText.setText("DONE");
                trackerText.setStyle("-fx-text-fill:green");
            }else{
                trackerText.setText("NOT DONE");
                trackerText.setStyle("-fx-text-fill:red");
            }
        
    }
}
