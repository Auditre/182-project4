import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Image;
import java.awt.MediaTracker;
import java.io.*;
import java.util.*;
import java.lang.*;
 
public class Project4 extends Application {
	
	private Room currentRoom;
	
//	greenRoom = new Room("green"),pinkRoom = new Room("pink"),
//			brownRoom = new Room("brown"),blueRoom = new Room("blue"),redRoom = new Room("red"),
//			yellowRoom = new Room("yellow"), goldRoom = new Room("gold");
	
	private Scene scene;
	
	private Stack stack;
	
	private boolean hasKey = false, gameWon = false;
 
    private static int xpos = 0, ypos = 0; // place window at this position
    private static int xsize = 700, ysize = 500; // set window to this size
 
    private Button pushButton, popButton, dumpButton, arrayButton, linkButton, javaButton, exitButton,
    getKeyButton;
    private TextField colorField, codeField;
//    private TextArea outputArea;
    private VBox vbox;
    private Label txt, colorLabel;
    private HBox topPane = new HBox();
    private boolean usingArray = false, usingList = false, usingStack = false;
 
    @Override
    public void start(Stage primaryStage) {
    	
    	
    	
    	
    	
    	
    	

    	stack = new Stack();
        // this pane contains the buttons and text fields that the user will interract with
        
        topPane.setPadding(new Insets(10, 5, 5, 5)); // sets padding around the topPane
        topPane.setSpacing(10.0); // sets spacing between items in pixels
        topPane.setAlignment(Pos.CENTER_LEFT); // sets the alignment of items added to topPane
 
        
        
        
      //Ask which stack they want to use
        arrayButton = new Button("Array");
        arrayButton.setOnAction(new ButtonHandler());
        topPane.getChildren().add(arrayButton);
        
        linkButton = new Button("Link");
        linkButton.setOnAction(new ButtonHandler());
        topPane.getChildren().add(linkButton);
        
        javaButton = new Button("Java Stack");
        javaButton.setOnAction(new ButtonHandler());
        topPane.getChildren().add(javaButton);
        
        
        exitButton = new Button("Exit");
        exitButton.setOnAction(new ExitHandler()); // note that this is a different class to handle the button events
        topPane.getChildren().add(exitButton);
        
        
        vbox = new VBox();
        txt = new Label("Welcome to Programmer Jones! Select a data structure.");
        vbox.getChildren().add(txt);
        
        // this pane contains the organization for the other panes
        BorderPane mainPane = new BorderPane();
        mainPane.setTop(topPane);
        mainPane.setCenter(vbox);
 
        // this scene makes up the main window of the project
        scene = new Scene(mainPane, xsize, ysize);
        
        
 
        primaryStage.setTitle("Project 4");
        primaryStage.setScene(scene); // adds the above scene to the window
        primaryStage.setX(xpos); // sets the window to open at this x position
        primaryStage.setY(ypos); // sets the window to open at this y position
        primaryStage.show();
        
        
    }
 
    // ButtonHandler is one implementation of event handling.
    // Multiple buttons are registered to it, and the class itself determines the
    //   necessary action based on who called it.
    // This approach benefits from global buttons, because the handler has to tell where the event came from.
    class ButtonHandler implements EventHandler<ActionEvent> {
 
        @Override
        public void handle(ActionEvent e) {
        	
        	if(e.getSource() == getKeyButton) {
        		hasKey = true;
        		txt.setText("You got the Golden Keyboard!!!");
        		topPane.getChildren().remove(getKeyButton);
        	}
        	
        	
        	if(e.getSource() == arrayButton) {
        		topPane.getChildren().remove(arrayButton);
        		topPane.getChildren().remove(linkButton);
        		topPane.getChildren().remove(javaButton);
        		topPane.getChildren().remove(exitButton);
        		
        		
        		colorLabel = new Label(); // default empty label constructor
                colorLabel.setText("Enter a Color: "); // setting the label text after creation
                topPane.getChildren().add(colorLabel); // adding the label to the HBox
         
                colorField = new TextField(); // default empty text field constructor
                colorField.setPrefWidth(150.0); // sets size of colorField in pixels
                topPane.getChildren().add(colorField); // adding the textfield to the HBox
         
                Label codeLabel = new Label("Enter a Code: "); // label with values constructor
                topPane.getChildren().add(codeLabel); // adding
         
                codeField = new TextField(""); // textfield with starting value constructor (it's empty in this case)
                codeField.setPrefWidth(50.0);
                topPane.getChildren().add(codeField);
        		
        		Room greenRoomStart = new Room("green");
        	    currentRoom = greenRoomStart;
        	    scene.getStylesheets().add("green.css");
        	    txt.setText("Who dares enter... The Temple of Gloom! You are in the Green room.\n" + 
        	    		"Before you stands three doors of Brown Blue and Pink.");
        		
        		
        		pushButton = new Button(); // default empty button constructor
                pushButton.setText("Push"); // setting the button text after creation
                pushButton.setOnAction(new ButtonHandler()); // assigning a class to handle events from this button
                topPane.getChildren().add(pushButton);
         
                popButton = new Button("Pop"); // button with text constructor
                popButton.setOnAction(new ButtonHandler());
                topPane.getChildren().add(popButton);
         
                dumpButton = new Button("Dump");
                dumpButton.setOnAction(new ButtonHandler());
                topPane.getChildren().add(dumpButton);
                
                topPane.getChildren().add(exitButton);
        	}
        	
        	if(e.getSource() == linkButton) {
        		txt.setText("This one does not work yet. ");
        		
        	}
        	
        	if(e.getSource() == javaButton) {
        		txt.setText("This one doesn't work sorry");
        	}
        	
        	
        	

            if (e.getSource() == popButton) {
                String newcolor = colorField.getText();
                int code;
                //CATCH ANY NUMBERS THAT AREN'T THREE DIGITS, OR AREN'T A NUMBER
                try {
	                code = Integer.parseInt(codeField.getText());
	                if((code < 100 || code > 999)) {
	                	txt.setText("Enter a 3 digit number!");
	                	return;
	                }
                }
                catch(Exception e1) {
                	txt.setText("Enter a 3-digit number!");
                	return;
                }
                
                
                
                
                txt.setText("Pop returning to " + newcolor);
                if(stack.empty() != true) {
                	if(stack.peek().getColor().toLowerCase().equals(newcolor.toLowerCase())
                			&& stack.peek().getKey() == code) {
                		
                		scene.getStylesheets().remove(currentRoom.getColor() + ".css");
                		currentRoom = stack.pop();
                		
                		switch(currentRoom.getColor().toLowerCase())
                        {
                        case "green": scene.getStylesheets().add("green.css");
                        
                        //THIS IS THE WIN CONDITION. Remove all buttons off the screen
	                        if(stack.empty() && hasKey) {
	                    		topPane.getChildren().remove(popButton);
	                    		topPane.getChildren().remove(pushButton);
	                    		topPane.getChildren().remove(colorField);
	                    		topPane.getChildren().remove(codeField);
	                    		txt.setText("YOU WON!!!! Restart to play again :)");
	                    		
	                    	}
                        
                        
                        
                    		break;
        	            case "pink": scene.getStylesheets().add("pink.css");
        	            	break;
        	            case "brown": scene.getStylesheets().add("brown.css");
        	            	break;
        	            case "blue": scene.getStylesheets().add("blue.css");
        	            	break;
        	            case "red": scene.getStylesheets().add("red.css");
        	            	break;
        	            case "yellow": scene.getStylesheets().add("yellow.css");
        	            	break;
        	            case "gold": scene.getStylesheets().add("gold.css");
                    		break;

                        }
                			
                	}else {
                		//DEATH.
                		txt.setText("You died.");
                		topPane.getChildren().remove(pushButton);
                		topPane.getChildren().remove(popButton);
                		
                	}
                }else {
                	//DEATH.
                	txt.setText("You died.");
                	topPane.getChildren().remove(pushButton);
            		topPane.getChildren().remove(popButton);
                }
                
                
            }
 
            if (e.getSource() == pushButton) {
                String newcolor = colorField.getText().toLowerCase();
                int code;
                
                try {
	                code = Integer.parseInt(codeField.getText());
	                if((code < 100 || code > 999)) {
	                	txt.setText("Enter a 3 digit number!");
	                	return;
	                }
                }
                catch(Exception e1) {
                	txt.setText("Enter a 3-digit number!");
                	return;
                }
                if(!stack.empty())
	                if(code == stack.peek().getKey()) {
	                	txt.setText("You used that code for the last room!");
	                	return;
	                }
       
                	
                txt.setText("Push entering " + newcolor);
                
                
                //THE INPUTED COLOR AND CODE IS ASSIGNED TO THE NEW ROOM AND PUSHED TO THE STACK
                
                
                switch(newcolor) 
                {
                case "green": 
                	//Logic for being able to enter the right rooms
                		if(!(currentRoom.getColor().equals("blue") ||
                				currentRoom.getColor().equals("brown") ||
                				currentRoom.getColor().equals("pink"))) {
                			txt.setText("That door isn't here.");
                			return;
                		}
                	scene.getStylesheets().remove(currentRoom.getColor() + ".css");
                	txt.setText("Before you stands Brown Blue and Pink doors.");
                	currentRoom.setKey(code);
                	stack.push(currentRoom);
                	Room greenRoom = new Room("green");
                	currentRoom = greenRoom;
                	break;
                	
                	
                	
                case "pink": 
                	//Logic for being able to enter the right rooms
                		if(!(currentRoom.getColor().equals("brown") ||
                				currentRoom.getColor().equals("red") ||
                				currentRoom.getColor().equals("blue"))) {
                			txt.setText("That door isn't here.");
                			return;
                		}
                	scene.getStylesheets().remove(currentRoom.getColor() + ".css");
                	txt.setText("Before you stands Brown Blue and Green doors.");
                	currentRoom.setKey(code);
                	stack.push(currentRoom);
                	Room pinkRoom = new Room("pink");
                	currentRoom = pinkRoom;
                	break;
                	
                	
                	
                case "brown": 
                	//Logic for being able to enter the right rooms
                		if(!(currentRoom.getColor().equals("green") ||
                				currentRoom.getColor().equals("red") ||
                				currentRoom.getColor().equals("pink"))) {
                			txt.setText("That door isn't here.");
                			return;
                		}
                	scene.getStylesheets().remove(currentRoom.getColor() + ".css");
                	txt.setText("Before you stands Green Red and Pink doors.");
                	currentRoom.setKey(code);
                	stack.push(currentRoom);
                	Room brownRoom = new Room("brown");
                	currentRoom = brownRoom;
                	break;
                	
                	
                	
                case "blue": 
                	//Logic for being able to enter the right rooms
                		if(!(currentRoom.getColor().equals("green") ||
                				currentRoom.getColor().equals("yellow") ||
                				currentRoom.getColor().equals("pink"))) {
                			txt.setText("That door isn't here.");
                			return;
                		}
                	scene.getStylesheets().remove(currentRoom.getColor() + ".css");
                	txt.setText("Before you stands Green Yellow and Pink doors.");
                	currentRoom.setKey(code);
                	stack.push(currentRoom);
                	Room blueRoom = new Room("blue");
                	currentRoom = blueRoom;
                	break;
                	
                	
                	
                case "red": 
                	//Logic for being able to enter the right rooms
                		if(!(currentRoom.getColor().equals("brown") ||
                				currentRoom.getColor().equals("yellow"))) {
                			txt.setText("That door isn't here.");
                			return;
                		}
                	scene.getStylesheets().remove(currentRoom.getColor() + ".css");
                	txt.setText("Before you stands Brown and Yellow doors.");
                	currentRoom.setKey(code);
                	stack.push(currentRoom);
                	Room redRoom = new Room("red");
                	currentRoom = redRoom;
                	break;
                	
                	
                	
                case "yellow": 
                	//Logic for being able to enter the right rooms
                		if(!(currentRoom.getColor().equals("blue") ||
                				currentRoom.getColor().equals("red") ||
                				currentRoom.getColor().equals("gold"))) {
                			txt.setText("That door isn't here.");
                			return;
                		}
                	scene.getStylesheets().remove(currentRoom.getColor() + ".css");
                	txt.setText("Before you stands Red Blue and Gold doors.");
                	currentRoom.setKey(code);
                	stack.push(currentRoom);
                	Room yellowRoom = new Room("yellow");
                	currentRoom = yellowRoom;
                	break;
                	
                	
                	
                case "gold": 
                	//Logic for being able to enter the right rooms
                		if(!(currentRoom.getColor().equals("yellow"))) {
                			txt.setText("That door isn't here.");
                			return;
                		}
                	txt.setText("Before you stands the Yellow door.");
                	getKeyButton = new Button("Keyboard!!!!");
                	getKeyButton.setOnAction(new ButtonHandler());
                	topPane.getChildren().add(getKeyButton);
                	scene.getStylesheets().remove(currentRoom.getColor() + ".css");
                	currentRoom.setKey(code);
                	stack.push(currentRoom);
                	Room goldRoom = new Room("gold");
                	currentRoom = goldRoom;
                	break;
                	
                	
                	
                default: txt.setText("That's not a room! You are in the " + currentRoom.getColor() 
                + " room.");
                }
                
                
                
                

                //CHANGE THE ROOM COLOR
                
                switch(currentRoom.getColor().toLowerCase())
                {
                case "green": scene.getStylesheets().add("green.css");
            		break;
	            case "pink": scene.getStylesheets().add("pink.css");
	            	break;
	            case "brown": scene.getStylesheets().add("brown.css");
	            	break;
	            case "blue": scene.getStylesheets().add("blue.css");
	            	break;
	            case "red": scene.getStylesheets().add("red.css");
	            	break;
	            case "yellow": scene.getStylesheets().add("yellow.css");
	            	break;
	            case "gold": scene.getStylesheets().add("gold.css");
            		break;

                }
                     
                
            }
 
            if (e.getSource() == dumpButton) {
            	Stack temp = new Stack();
                System.out.println("Stack Contents Dump: ");
                // add code to print contents of Stack to the CONSOLE
                if(stack.empty())
                	System.out.println("The Stack is empty.");
                else 
                	for(int i = stack.getTop(); i > -1; i--) { 
                		System.out.println(i + ": " + stack.peek().getColor() + " " + stack.peek().getKey());
                		temp.push(stack.pop());
                	}
                
                for(int i = temp.getTop(); i > -1; i--) 
                	stack.push(temp.pop());
                
                if(hasKey)
                	System.out.println("You have the keyboard.");
                else
                	System.out.println("You don't have the keyboard");
                
            }
        }
    }
 
    // ExitHandler is an alternate implementation of event handling
    // Each button is registered to a different handler, which performs the desired actions
    // This approach does not require global buttons.
    class ExitHandler implements EventHandler<ActionEvent> {
 
        @Override
        public void handle(ActionEvent e) {
            System.exit(0);
        }
    }
 
    public static void main(String[] args) {
        launch(args);
    }
    
 
}


