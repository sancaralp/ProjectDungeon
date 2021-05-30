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
import sample.UserInterface;
import sample.Player;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SetStatsController extends UserInterface {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private ImageView StatsMageView;
    @FXML
    private ImageView StatsRogueView;
    @FXML
    private ImageView StatsBerserkerView;
    @FXML
    private ImageView BerserkerFont;
    @FXML
    private ImageView MageFont;
    @FXML
    private ImageView RogueFont;
    @FXML
    private Label NicknameFont;
    @FXML
    private Label Str;
    @FXML
    private Label Cons;
    @FXML
    private Label Dex;
    @FXML
    private Label Arc;
    @FXML
    private Label Hp;
    @FXML
    private Label LeftStats;
    @FXML
    private Button StrAdd;
    @FXML
    private Button ConsAdd;
    @FXML
    private Button DexAdd;
    @FXML
    private Button ArcAdd;
    @FXML
    private Button EnterDungeonButton;
    @FXML
    void initialize() {
        getStats();
        CharacterView();
        setNickname();
        statDistribution();
        EnterDungeonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                enterRoomOne();
            }
        });

    }
    int d6=(D(6,1,0));
    void getStats(){

        Str.setText(String.valueOf(Player.stats[0])+ "" +" ("+mod(Player.stats[0])+")");
        Cons.setText(String.valueOf(Player.stats[1])+ "" +" ("+mod(Player.stats[1])+")");
        Dex.setText(String.valueOf(Player.stats[2])+ "" +" ("+mod(Player.stats[2])+")");
        Arc.setText(String.valueOf(Player.stats[3])+ "" +" ("+mod(Player.stats[3])+")");
        Hp.setText(String.valueOf(Player.health));
        LeftStats.setText(d6+"");


    }

    void CharacterView (){
        String CharacterClass = Player.characterClass;
        if (CharacterClass.equals("Mage")){
            StatsBerserkerView.setOpacity(0.0);
            StatsRogueView.setOpacity(0.0);
            BerserkerFont.setOpacity(0);
            RogueFont.setOpacity(0);
        }else if (CharacterClass.equals("Rogue")) {
            StatsBerserkerView.setOpacity(0);
            StatsMageView.setOpacity(0);
            BerserkerFont.setOpacity(0);
            MageFont.setOpacity(0);
        }else{
            // Berserker
            StatsMageView.setOpacity(0);
            StatsRogueView.setOpacity(0);
            RogueFont.setOpacity(0);
            MageFont.setOpacity(0);
        }
    }
    void setNickname(){
        String Nickname = Player.name;
        NicknameFont.setText(Nickname);
    }
    //final String[] input = new String[1];

    void statDistribution(){
        StrAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(d6!=0){
                    String strPlus = (Player.stats[0] + 1) + "" +" ("+(mod(Player.stats[0]+1))+")";
                    Str.setText(strPlus);
                    Player.stats[0]++;
                    d6--;
                    LeftStats.setText(d6 + "");
                }
            }
        });
        ConsAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(d6!=0){
                    Player.health-=mod(Player.stats[1]);
                    String consPlus=(Player.stats[1]+1)+"" +" ("+(mod(Player.stats[1]+1))+")";
                    Cons.setText(consPlus);
                    Player.stats[1]++;
                    Player.health+=mod(Player.stats[1]);
                    Hp.setText(Player.health+"");
                    d6--;
                    LeftStats.setText(d6+"");}
            }
        });
        DexAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(d6!=0) {
                    String dexPlus = (Player.stats[2] + 1) + "" +" ("+(mod(Player.stats[2]+1))+")";
                    Dex.setText(dexPlus);
                    Player.stats[2]++;
                    d6--;
                    LeftStats.setText(d6 + "");
                }
            }
        });
        if(Player.characterClass.equals("Mage"))
        ArcAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(d6!=0) {
                    String arcPlus = (Player.stats[3] + 1) + "" +" ("+(mod(Player.stats[3]+1))+")";
                    Arc.setText(arcPlus);
                    Player.stats[3]++;
                    d6--;
                    LeftStats.setText(d6 + "");
                }
            }
        });
    }
        void enterRoomOne() {
        if(d6==0) {
            EnterDungeonButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/view/RoomOne.fxml"));
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




