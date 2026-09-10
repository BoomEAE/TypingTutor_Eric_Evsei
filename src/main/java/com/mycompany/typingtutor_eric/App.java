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
        //
        char letters[] = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm'};
        
        //Creation of root and keys instances
        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        
        //TextField and Label instances
        Label keyPressed = new Label("TEST TEST TEST");
        TextField inputField = new TextField();
        
        //A list that will contain every button needed
        Button buttons[] = new Button[28];
        
        //Add the label and textfield
        gridPane.add(keyPressed, 5, 0);
        gridPane.add(inputField, 5, 1);
        
        //For loop that creates button instances corresponding to different keys on a keyboard
        //and then stores them into a list
        for(int i = 0; i < buttons.length; i++){
            switch(i){
                case 26:
                    buttons[i] = new Button("Shift");
                    break;
                case 27:
                    buttons[i] = new Button("Space");
                    break;
                default:
                    buttons[i] = new Button("" + letters[i]);
                    break;
            }
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
                gridPane.add(buttons[i], i-22, 5);
            }
        }
        
        //Add the gridPane with all the buttons, textfield and label to the root
        root.getChildren().add(gridPane);
        
        
        Scene scene = new Scene(root, 200, 200);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
