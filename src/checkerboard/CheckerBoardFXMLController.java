/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author mattstehnach
 */
public class CheckerBoardFXMLController implements Initializable, Startable{
    
    @FXML
    private VBox vbox;
    
    @FXML
    private MenuBar menuBar;
    
    private Board currentBoard;
    
    @FXML
    public void size16x16(ActionEvent event) {
        resizeBoard(16);
    }
    
    @FXML
    public void size10x10(ActionEvent event) {
        resizeBoard(10);
    }
    
    @FXML
    public void size8x8(ActionEvent event) {
        resizeBoard(8);
    }
    
    @FXML
    public void size3x3(ActionEvent event) {
        resizeBoard(3);
    }
    
    @FXML
    public void defaultColor(ActionEvent event) {
        changeColor(Color.RED, Color.BLACK);
    }
    
    @FXML
    public void blueColor(ActionEvent event) {
        changeColor(Color.SKYBLUE, Color.DARKBLUE);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void changeColor(Color lightColor, Color darkColor) {
        Board newBoard = new Board(currentBoard.getNumRows(), currentBoard.getNumCols(), vbox.getWidth(), vbox.getHeight() - menuBar.getHeight(), lightColor, darkColor);
        vbox.getChildren().remove(currentBoard.getBoard());
        vbox.getChildren().add(newBoard.build());
        currentBoard = newBoard;
    }
    
    private void resizeBoard(int rows) {
        Board newBoard = new Board(rows, rows, vbox.getWidth(), vbox.getHeight() - menuBar.getHeight(), currentBoard.getLightColor(), currentBoard.getDarkColor());
        vbox.getChildren().remove(currentBoard.getBoard());
        vbox.getChildren().add(newBoard.build());
        currentBoard = newBoard;
    }
    
    public void start(Stage stage) {
        currentBoard = new Board(8, 8, vbox.getWidth(), vbox.getHeight() - menuBar.getHeight());
        vbox.getChildren().add(currentBoard.build());
        ChangeListener<Number> sizeChangeListener = (a, b, c) -> {
            Board newBoard = new Board(currentBoard.getNumRows(), currentBoard.getNumCols(), vbox.getWidth(), vbox.getHeight() - menuBar.getHeight(), currentBoard.getLightColor(), currentBoard.getDarkColor());
            vbox.getChildren().remove(currentBoard.getBoard());
            vbox.getChildren().add(newBoard.build());
            currentBoard = newBoard;
        };
       
        vbox.getScene().widthProperty().addListener(sizeChangeListener);
        vbox.getScene().heightProperty().addListener(sizeChangeListener);
    }
}