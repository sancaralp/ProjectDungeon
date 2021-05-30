package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.Player;
import sample.UserInterface;

import java.io.IOException;

public class TreasureRoomController extends UserInterface {
    @FXML
    private ImageView MageView;

    @FXML
    private ImageView BerserkerView;

    @FXML
    private ImageView RogueView;

    @FXML
    private Label TextOne;

    @FXML
    private Label TextTwo;

    @FXML
    private Button DrinkButton;

    @FXML
    private Button ThrowButton;

    @FXML
    private ImageView Potion;

    @FXML
    private Label IncreasedLabel;

    @FXML
    private Label DecreasedLabel;
    @FXML
    private Label ThrowText;
    @FXML
    private ImageView DrinkButtonView;

    @FXML
    private ImageView ThrowButtonView;
    @FXML
    private ImageView NextRoomView;

    @FXML
    private Button NextRoomButton;

    @FXML
    void initialize() {
        CharacterView();
        Drink();
    }
    void CharacterView (){
        String CharacterClass = Player.characterClass;
        if (CharacterClass.equals("Mage")){
            BerserkerView.setOpacity(0.0);
            RogueView.setOpacity(0.0);
        }else if (CharacterClass.equals("Rogue")) {
            BerserkerView.setOpacity(0);
            MageView.setOpacity(0);
        }else{
            // Berserker
            MageView.setOpacity(0);
            RogueView.setOpacity(0);
        }
    }
    void Drink(){
        DrinkButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextOne.setOpacity(0);
                TextTwo.setOpacity(0);
                Potion.setOpacity(0);
                DrinkButtonView.setOpacity(0);
                ThrowButtonView.setOpacity(0);
                DrinkButton.setPrefSize(0,0);
                ThrowButton.setPrefSize(0,0);
                NextRoomView.setOpacity(1);

                int d6 = D(6,1,0);
                if(d6>2){
                    Player.stats[0]+=2;
                    Player.stats[2]+=2;
                    IncreasedLabel.setOpacity(1);
                }else {
                    Player.stats[0]-=2;
                    Player.stats[2]-=2;
                    DecreasedLabel.setOpacity(1);
                }
                NextRoomButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        EnterFinalRoom();
                    }
                });

                }
            }
        );
        ThrowButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextOne.setOpacity(0);
                TextTwo.setOpacity(0);
                Potion.setOpacity(0);
                DrinkButtonView.setOpacity(0);
                ThrowButtonView.setOpacity(0);
                DrinkButton.setPrefSize(0,0);
                ThrowButton.setPrefSize(0,0);
                ThrowText.setOpacity(1);
                NextRoomView.setOpacity(1);
                NextRoomButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        EnterFinalRoom();
                    }
                });
                }
            }
        );

    }
    void EnterFinalRoom(){
        NextRoomButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/FinalRoom.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setTitle("Project Dungeon");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
