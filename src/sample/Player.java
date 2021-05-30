package sample;

public class Player extends Entity{


    public static int[] stats;
    public static int health;
    public static String characterClass;
    public static String name;
   // public static boolean canRage=false;
    public static int AC;


    public Player(String name, String characterClass) {
        super(name, characterClass);
        this.stats=super.stats;
        this.health=super.health;
        this.name=super.name;
        this.characterClass=super.characterClass;
        this.AC=super.AC;

        //if(characterClass.equalsIgnoreCase("Berserker"))canRage=true;
    }
    public Player() {
        super(name, characterClass);

    }










}
