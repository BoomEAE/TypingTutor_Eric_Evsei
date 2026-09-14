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




/**
 * JavaFX App
 */
public class App extends Application {
    
    private int counter = 0;

    @Override
    public void start(Stage mainStage) {
        //Phrases
        String phrases[] = {"Try typing this text. Do it as quickly and accurately as you can.",
                            "Next type another line of input data.",
                            "The quick brown fox jumps over the lazy `dog.",
                            "Five big quacking zephyrs jolt my wax bed.",
                            "Sympathizing would fix Quaker objectives.",
                            "A large fawn jumped quickly over white zinc boxes"};
        
        //Creation of root and keys instances
        BorderPane root = new BorderPane();
        GridPane mainGridPane = new GridPane();
        HBox top = new HBox(95);
        GridPane center = new GridPane();
        GridPane bottom = new GridPane();
        
        //TextField and Label instances
        Label textPhrase = new Label(phrases[0]);
        Label textCounter = new Label("1 of 6");
        Label keyPressed = new Label("[Text typed will show here]");
        Button nextButton = new Button("next");
        Button resetButton = new Button("reset");
        
        //Create and add buttons
        Button buttons[] = Create_Add_Buttons(bottom);
        
        //Add the label and textfield
        top.getChildren().addAll(resetButton, textCounter, nextButton);
        center.add(textPhrase, 5, 1);
        center.add(keyPressed, 5, 2);
        mainGridPane.add(top, 0, 0);
        mainGridPane.add(center, 0, 1);
        mainGridPane.add(bottom, 0, 2);
        
        textPhrase.setMinWidth(200);
        
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
            textCounter.setText((counter+1) + " of 6");
            textPhrase.setText(phrases[counter]);
        });
        
        //
        resetButton.setOnAction(event -> {
            counter = 0;
            textCounter.setText("0 of 6");
            textPhrase.setText(phrases[counter]);
        });
        
        //
        Scene scene = new Scene(root, 200, 200);
        
        //
        scene.setOnKeyPressed(event -> {
            String keyText = event.getCode().getName().toUpperCase();
            System.out.println(keyText);
            if(keyPressed.getText().equals("[Text typed will show here]")){
                keyPressed.setText("");
            }
            
            //
            if(keyText.equals("BACKSPACE")){
                int textLength = keyPressed.getText().length();
                if(textLength > 0){
                    String subText = keyPressed.getText().substring(0, textLength-1);
                    
                    if(subText.length() == 0){
                        keyPressed.setText("[Text typed will show here]");
                    }else{
                        keyPressed.setText(subText);
                    }
                }
            }else{
                keyPressed.setText(keyPressed.getText() + keyText);
            }
            
            
            for(Button button : buttons){
                if(keyText.equals(button.getText().toUpperCase())){
                    changeButtonStyle(button, 1);
                }else if(button.getText().equals("Space")){
                    if(keyText.equals(" ")){
                        changeButtonStyle(button, 1);
                    }
                }
            }
        });
        
        //
        scene.setOnKeyReleased(event -> {
            for(Button button : buttons){
                if(event.getCode().getName().toLowerCase().equals(button.getText().toLowerCase())){
                    changeButtonStyle(button, 0);
                }
            }
        });
        
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    //Creates and adds necessary buttons for the typing tutor program
    public static Button[] Create_Add_Buttons(GridPane gridPane){
        //
        char letters[] = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
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

}
