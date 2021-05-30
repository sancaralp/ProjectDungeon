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

public class LevelUpController extends UserInterface {
    @FXML
    private ImageView BerserkerView;

    @FXML
    private ImageView MageView;

    @FXML
    private ImageView RogueView;

    @FXML
    private Label StrLabel;

    @FXML
    private Label ConsLabel;

    @FXML
    private Label DexLabel;

    @FXML
    private Label ArcLabel;

    @FXML
    private Label HealthLabel;

    @FXML
    private Button StrButton;

    @FXML
    private Button DexButton;

    @FXML
    private Button ConsButton;

    @FXML
    private Button ArcButton;
    @FXML
    private Button EnterNextRoom;

    @FXML
    void initialize() {
        getStats();
        CharacterView();
        statDistribution();
        EnterNextRoom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enterRoomTwo();
            }
        });
    }

    void getStats() {

        StrLabel.setText(String.valueOf(Player.stats[0])+ "" +" ("+mod(Player.stats[0])+")");
        ConsLabel.setText(String.valueOf(Player.stats[1])+ "" +" ("+mod(Player.stats[1])+")");
        DexLabel.setText(String.valueOf(Player.stats[2])+ "" +" ("+mod(Player.stats[2])+")");
        ArcLabel.setText(String.valueOf(Player.stats[3])+ "" +" ("+mod(Player.stats[3])+")");
        HealthLabel.setText(String.valueOf(Player.health));

    }

    void CharacterView() {
        String CharacterClass = Player.characterClass;
        if (CharacterClass.equals("Mage")) {
            BerserkerView.setOpacity(0.0);
            RogueView.setOpacity(0.0);
        } else if (CharacterClass.equals("Rogue")) {
            BerserkerView.setOpacity(0);
            MageView.setOpacity(0);
        } else {
            // Berserker
            MageView.setOpacity(0);
            RogueView.setOpacity(0);
        }
    }

    final int[] statNumber = {1};

    void statDistribution() {


        StrButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (statNumber[0] != 0) {
                    String strPlus = (Player.stats[0] + 1) + "" +" ("+(mod(Player.stats[0]+1))+")";
                    StrLabel.setText(strPlus);
                    Player.stats[0]++;
                    Player.health += Math.max(D(4, 1, mod(Player.stats[1])), 0);
                    HealthLabel.setText(Player.health + "");
                    statNumber[0]--;

                }
            }
        });
        ConsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (statNumber[0] != 0) {
                    Player.health -= mod(Player.stats[1]);
                    String consPlus = (Player.stats[1] + 1) + "" +" ("+(mod(Player.stats[1]+1))+")";
                    ConsLabel.setText(consPlus);
                    Player.stats[1]++;
                    Player.health += mod(Player.stats[1]);
                    Player.health += Math.max(D(4, 1, mod(Player.stats[1])), 0);
                    HealthLabel.setText(Player.health + "");
                    statNumber[0]--;
                }
            }
        });
        DexButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (statNumber[0] != 0) {
                    String dexPlus = (Player.stats[2] + 1) + "" +" ("+(mod(Player.stats[2]+1))+")";
                    DexLabel.setText(dexPlus);
                    Player.stats[2]++;
                    Player.health += Math.max(D(4, 1, mod(Player.stats[1])), 0);
                    HealthLabel.setText(Player.health + "");
                    statNumber[0]--;
                }
            }
        });
        if (Player.characterClass.equals("Mage"))
            ArcButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (statNumber[0] != 0) {
                        String arcPlus = (Player.stats[3] + 1) + "" +" ("+(mod(Player.stats[3]+1))+")";
                        ArcLabel.setText(arcPlus);
                        Player.stats[3]++;
                        Player.health += Math.max(D(4, 1, mod(Player.stats[1])), 0);
                        HealthLabel.setText(Player.health + "");
                        statNumber[0]--;
                    }
                }
            });
    }

    void enterRoomTwo() {
        if (statNumber[0] == 0) {
            EnterNextRoom.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/RoomTwo.fxml"));
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
}
