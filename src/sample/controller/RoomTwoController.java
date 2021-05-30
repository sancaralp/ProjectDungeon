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
import sample.Combat;
import sample.Foe;
import sample.Player;
import sample.UserInterface;

import java.io.IOException;

public class RoomTwoController extends UserInterface {
    @FXML
    private ImageView BerserkerView;

    @FXML
    private ImageView RogueView;

    @FXML
    private ImageView MageView;

    @FXML
    private ImageView FoeAgile;
    @FXML
    private ImageView FoeAverege;
    @FXML
    private ImageView FoeTank;
    @FXML
    private Label CombatActions;

    @FXML
    private Button RollButton;
    @FXML
    private Label NameClassLabel;
    @FXML
    private Label StatsTexts;
    @FXML
    private Label HealthText;
    @FXML
    private Label ConLabel;

    @FXML
    private Label DexLabel;

    @FXML
    private Label StrLabel;

    @FXML
    private Label ArcLabel;

    @FXML
    void initialize() {
        CharacterName();
        GetEnemy();
        SetTexts();
        Combat();
    }
    void CharacterName (){
        String CharacterClass = Player.characterClass;
        if (CharacterClass.equals("Mage")){
            BerserkerView.setOpacity(0.0);
            RogueView.setOpacity(0.0);
        }else if (CharacterClass.equals("Rogue")) {
            BerserkerView.setOpacity(0);
            MageView.setOpacity(0);
        }else{
            MageView.setOpacity(0);
            RogueView.setOpacity(0);
        }
    }
    Foe foe;
    void GetEnemy(){
        int d6 = D(6,1,0);
        if (d6<3){
            foe=new Foe("Ugly Goblin","Agile");
            FoeAverege.setOpacity(0);
            FoeTank.setOpacity(0);
        }
        else if (d6<5){
            foe=new Foe("Orc Warrior","Average");
            FoeAgile.setOpacity(0);
            FoeTank.setOpacity(0);
        }else {foe=new Foe("Wild Orc Bruiser","Tank");
            FoeAgile.setOpacity(0);
            FoeAverege.setOpacity(0);
        }
    }
    void Combat(){
        Player player=new Player();
        Combat combat1=new Combat();final String[] CombatText = {"As soon as you entered the second room you have encountered a(n) "
                + foe.name+"\nPress \"Roll\" button to attack"};
        //System.out.println("Odaya girdiğin anda bir düşman ile karşılaştın. Ne yapmak istiyorsun?");
        CombatActions.setText(CombatText[0]);
        RollButton.setOnAction(new EventHandler<ActionEvent>() {
                                   @Override
                                   public void handle(ActionEvent event) {
                                       if (foe.health >0 && Player.health > 0 ){
                                           combat1.attack(player, foe);
                                           CombatText[0]+="\n"+combat1.d20Dice+" "+combat1.d6Dice+"\n"+combat1.SpecialAttack;
                                           combat1.attackToPlayer(foe);
                                           CombatText[0]+="\n"+combat1.d20Dice+" "+combat1.d6Dice+"\n"+combat1.SpecialAttack;
                                           combat1.SpecialAttack="";
                                           SetTexts();
                                       }
                                       if (player.health<=0){
                                           player.isDead = true;
                                           CombatText[0] +="\n"+player.name+" has been defeated";
                                           RollButton.setOnAction(new EventHandler<ActionEvent>() {
                                               @Override
                                               public void handle(ActionEvent event) {
                                                   GameOver();
                                               }
                                           });
                                       }
                                       else if (foe.health<=0){
                                           foe.isDead = true;
                                           CombatText[0] +="\n"+foe.name+" has been defeated";
                                           RollButton.setOnAction(new EventHandler<ActionEvent>() {
                                               @Override
                                               public void handle(ActionEvent event) {
                                                   EnterRoomThree();
                                               }
                                           });

                                       }

                                       CombatActions.setText(CombatText[0]);
                                       CombatText[0] = "";
                                   }
                               }
        );
    }
    void SetTexts(){
        NameClassLabel.setText(Player.name+"\n"+Player.characterClass);
        StrLabel.setText((Player.stats[0])+"("+mod(Player.stats[0])+")");
        ConLabel.setText((Player.stats[1])+"("+mod(Player.stats[1])+")");
        DexLabel.setText((Player.stats[2])+"("+mod(Player.stats[2])+")");
        ArcLabel.setText((Player.stats[3])+"("+mod(Player.stats[3])+")");
        HealthText.setText(String.valueOf(Player.health));
    }
    void GameOver(){
        RollButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/GameOver.fxml"));
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
    void EnterRoomThree(){
        RollButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/LevelUp2.fxml"));
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
