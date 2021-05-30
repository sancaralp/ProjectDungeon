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

public class FinalRoomController extends UserInterface {

    @FXML
    private ImageView RogueView;

    @FXML
    private ImageView BerserkerView;

    @FXML
    private ImageView MageView;

    @FXML
    private Label CombatActions;

    @FXML
    private Button RollButton;

    @FXML
    private Label NameClassLabel;

    @FXML
    private Label HealthText;

    @FXML
    private ImageView AnimeBossView;
    @FXML
    private ImageView OrcBossView;
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

        int d20 = D(20,1,0);
        if (d20<=19){
            foe=new Foe("Wild Orc King","Boss");
            AnimeBossView.setOpacity(0);

        }else {
            foe=new Foe("Cute Anime Girl","Boss");
            OrcBossView.setOpacity(0);
        }
    }
    void Combat(){
        Player player=new Player();
        Combat combat1=new Combat();final String[] CombatText = {"As soon as you entered the last room you have encountered a "
                + foe.name+"\nPress \"Roll\" button to attack"};

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
                                       if (Player.health <=0){
                                           player.isDead = true;
                                           CombatText[0] +="\n"+ Player.name +" has been defeated"+"\nPress \"Roll\" button to continue";
                                           RollButton.setOnAction(new EventHandler<ActionEvent>() {
                                               @Override
                                               public void handle(ActionEvent event) {
                                                   GameOver();
                                               }
                                           });
                                       }
                                       else if (foe.health<=0){
                                           foe.isDead = true;
                                           CombatText[0] +="\n"+foe.name+" has been defeated"+"\nPress \"Roll\" button to continue";
                                           RollButton.setOnAction(new EventHandler<ActionEvent>() {
                                               @Override
                                               public void handle(ActionEvent event) {
                                                   EnterCredits();
                                               }
                                           });

                                       }

                                       CombatActions.setText(CombatText[0]);
                                       CombatText[0] = "";
                                   }
                               }
        );
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
    void SetTexts(){
        NameClassLabel.setText(Player.name+"\n"+Player.characterClass);
        StrLabel.setText((Player.stats[0])+"("+mod(Player.stats[0])+")");
        ConLabel.setText((Player.stats[1])+"("+mod(Player.stats[1])+")");
        DexLabel.setText((Player.stats[2])+"("+mod(Player.stats[2])+")");
        ArcLabel.setText((Player.stats[3])+"("+mod(Player.stats[3])+")");
        HealthText.setText(String.valueOf(Player.health));
    }
    void EnterCredits(){
        RollButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/view/CreditsScreen.fxml"));
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
