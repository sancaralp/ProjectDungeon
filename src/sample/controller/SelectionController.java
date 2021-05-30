package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SelectionController {
    String character;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BerserkerSelectButton;
    @FXML
    private Button RogueSelectButton;
    @FXML
    private Button MageSelectButton;
    @FXML
    private TextField NicknameInput;

    @FXML
    void initialize() {
        BerserkerSelectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setPlayer("Berserker");
                character = "Berserker";
                enterSetStats();
            }
        });
        RogueSelectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setPlayer("Rogue");
                character = "Rogue";
                enterSetStats();
            }
        });
        MageSelectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setPlayer("Mage");
                character = "Mage";
                enterSetStats();
            }
        });
    }
    void setPlayer(String characterClass_) {
        String nickname;

        if (!NicknameInput.getText().toString().trim().equals("")&&!NicknameInput.getText().toString().trim().equals("Nickname")) {

            nickname = NicknameInput.getText();
        } else {
            nickname = "Player";
        }
        Player player = new Player(nickname,characterClass_);
    }

    void enterSetStats(){
        BerserkerSelectButton.getScene().getWindow().hide();
        RogueSelectButton.getScene().getWindow().hide();
        MageSelectButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/SetStats.fxml"));
        try{
            loader.load();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Project Dungeon");
        stage.setScene(new Scene(root));
        stage.show();
    }
}