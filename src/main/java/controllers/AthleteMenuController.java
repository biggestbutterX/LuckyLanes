
package main.java.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.formObjects.personalityTest;
import main.java.Database;
import main.java.LuckyLanes;

/**
 * FXML Controller class
 *
 * This is the controller for the creation of an athlete. It gives the user
 * the option to choose between a list of specific athletes.
 * 
 * @author Mario
 */
public class AthleteMenuController implements Initializable
{
    @FXML
    Button btnBowlTest;
    private Stage stage;                //The window.
    
    private Scene preScene;             //The previous screens scene while using the back button.
    private Scene nextScene;            //The to be next scene.
    private double preMinHeight;        //The previous minimum screens height.
    private double preMinWidth;         //The previous minimum screens width.
    
    private String preTitle;            //The previous screens title.
    protected final String title = "Menu";       //The current stages title.
    String id;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
        checkQuestions();
    }
    
    /**
     * Puts things in the current window. Changes the scene to the current one.
     * @param stage The window.
     */
    public void checkQuestions(){
        btnBowlTest.setDisable(true);
        Database.connect();
        String SQL = "SELECT * FROM TEST;";
        ResultSet turkey = Database.searchQuery(SQL);
        if(turkey !=null){
            try {
                if(turkey.next()){
                    btnBowlTest.setDisable(false);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AthleteMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Database.close();
    }
    public void setId(String id){
        this.id = id;
    }
    protected void setStage(Stage stage)
    {
        preTitle = stage.getTitle();
        this.stage = stage;
        this.stage.setTitle(title);
        
        preMinHeight = stage.getMinHeight();
        preMinWidth = stage.getMinWidth();
        
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }
    
    /**
     * 
     * @param event 
     */
    //open up forms. Takes file path to form

    @FXML
    private void athleteInfo(ActionEvent event)
 {
    	String fxml = "/main/resources/view/bowler.fxml";
        
        BorderPane root;
        
        try
        {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);
            
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try
            {
                root = (BorderPane) loader.load(in);
            }
            finally
            {
                in.close();
            }
            
            Stage stage = new Stage();
            //stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root));
            stage.show();
            
            
            BowlerController newBowler = (BowlerController)((Initializable) loader.getController());
            newBowler.setFromRecord(id);
            System.out.println(id);
            newBowler.setStage(stage);
            newBowler.createListeners();
            
            newBowler.setUpViewWindow();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void bowlTest(ActionEvent event)
 {
    	String fxml = "/main/resources/view/bowlTest.fxml";
        
        AnchorPane root;
        
        try
        {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);
            
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try
            {
                root = (AnchorPane) loader.load(in);
            }
            finally
            {
                in.close();
            }
            
            //Stage stage = new Stage();
            preScene = stage.getScene();
            stage.setScene(new Scene(root));
            stage.show();
            
            
            BowlTestController newBowler = (BowlTestController)((Initializable) loader.getController());
            newBowler.setId(id);
            newBowler.updateScore(id);
            System.out.println(id);
            newBowler.setStage(stage);
            newBowler.setPreScene(preScene);
            

            stage.setOnCloseRequest((WindowEvent we) ->
            {
                ((Stage) (stage.getScene()).getWindow()).show();
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
      private void psychTest(ActionEvent event)
    {
        
    	String fxml = "/main/resources/view/PersonalityTest.fxml";
        
        AnchorPane root;
        
        try
        {
            FXMLLoader loader = new FXMLLoader();
            InputStream in = LuckyLanes.class.getResourceAsStream(fxml);
            
            loader.setBuilderFactory(new JavaFXBuilderFactory());
            loader.setLocation(LuckyLanes.class.getResource(fxml));

            try
            {
                root = (AnchorPane) loader.load(in);
            }
            finally
            {
                in.close();
            }
            
            //Stage stage = new Stage();
            preScene = stage.getScene();
            stage.setScene(new Scene(root));
            stage.show();
            
            
            PersonalityTestController newBowler = (PersonalityTestController)((Initializable) loader.getController());
            newBowler.setId(id);
            newBowler.updateScore(id);
            System.out.println(id);
            newBowler.setStage(stage);
            newBowler.setPreScene(preScene);
            

            stage.setOnCloseRequest((WindowEvent we) ->
            {
                ((Stage) (stage.getScene()).getWindow()).show();
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    /**
     * Adds the previous scene into the object to allow the user to go back to it with the back button.
     * @param pre The previous scene.
     */
    protected void setPreScene(Scene pre)
    {
        preScene = pre;
    }
    
    /**
     * Method called by the FXML after the user pushes the back button.
     * It sets the scene to the previous one.
     * @throws IOException 
     */
    @FXML
    private void goBack() throws IOException
    {
        stage.setMinHeight(preMinHeight);
        stage.setMinWidth(preMinWidth);
        stage.setScene(preScene);
        stage.sizeToScene();
        stage.setTitle(preTitle);
        stage.close();
    }


}






















