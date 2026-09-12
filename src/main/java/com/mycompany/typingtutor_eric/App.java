package com.mycompany.typingtutor_eric;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.KeyCode;




/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage mainStage) {
        
        //Creation of root and keys instances
        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        
        //TextField and Label instances
        Label textPhrase = new Label("E");
        Label keyPressed = new Label();
        Button nextButton = new Button("next");
        Button resetButton = new Button("reset");
        
        
        //Create and add buttons
        Button buttons[] = Create_Add_Buttons(gridPane);
        
        //Add the label and textfield
        gridPane.add(textPhrase, 5, 0);
        gridPane.add(keyPressed, 5, 1);
        gridPane.add(nextButton, 9, 0);
        gridPane.add(resetButton, 0, 0);
        
        //Add the gridPane with all the buttons, textfield and label to the root
        root.getChildren().add(gridPane);
        
        
        Scene scene = new Scene(root, 200, 200);
        
        scene.setOnKeyPressed(event -> {
            String keyText = event.getCode().getName().toUpperCase();
            
            for(Button button : buttons){
                if(keyText.equals(button.getText().toUpperCase())){
                    changeButtonStyle(button, 1);
                }else if(keyText.equals(" ")){
                    if(button.getText().equals("Space")){
                        changeButtonStyle(button, 1);
                    }
                }
            }
        });
        
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
                gridPane.add(buttons[i], i, 2);
            }else if(i >= 10 && i < 19){
                gridPane.add(buttons[i], i-9, 3);
            }else if(i >= 19 && i < 26){
                gridPane.add(buttons[i], i-17, 4);
            }else{
                switch(i){
                    case 27:
                       gridPane.add(buttons[i], i-21, 5);
                       break;
                    default:
                        gridPane.add(buttons[i], i-22, 5);
                }
            }
        }
        
        GridPane.setColumnSpan(buttons[26], 2);
        GridPane.setColumnSpan(buttons[27], 2);
        
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
