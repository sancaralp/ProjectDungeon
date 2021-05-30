package sample;

public class Entity extends UserInterface {
    String name;
    String characterClass;

    int[] stats= new int[4];
    int health;
    int AC;
    public boolean isDead=false;



    Entity(String name, String characterClass){
        switch (characterClass) {
            // [0] = Str
            // [1] = Cons
            // [2] = Dex
            // [3] = Arc
            case "Tank" -> {
                stats[0] = 7;
                stats[1] = 16;
                stats[2] = 8;
                stats[3] = 10;
                this.AC=10+mod("dex",this);
            }
            case "Average" -> {
                stats[0] = 9;
                stats[1] = 10;
                stats[2] = 10;
                stats[3] = 10;
                this.AC=10+mod("dex",this);
            }
            case "Agile" -> {
                stats[0] = 7;
                stats[1] = 6;
                stats[2] = 14;
                stats[3] = 10;
                this.AC=10+mod("dex",this);
            }
            case "Boss" -> {
                stats[0] = 12;
                stats[1] = 12;
                stats[2] = 8;
                stats[3] = 12;
                this.AC=10+mod("dex",this);
            }
            case "Rogue" -> {
                stats[0] = 10;
                stats[1] = 8;
                stats[2] = 14;
                stats[3] = 10;
                this.AC=10+mod("dex",this);
            }
            case "Berserker" -> {
                stats[0] = 10;
                stats[1] = 14;
                stats[2] = 10;
                stats[3] = 10;
                this.AC=10+mod("dex",this);
            }
            case "Mage" -> {
                stats[0] = 7;
                stats[1] = 6;
                stats[2] = 7;
                stats[3] = 12;
                this.AC=10+mod("dex",this);
            }
        }
        this.health=(6+D(6,1,0)+mod("con",this));
        this.characterClass=characterClass;
        this.name=name;
    }

}
