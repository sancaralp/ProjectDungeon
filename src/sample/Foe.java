package sample;

public class Foe extends Entity{
    int[] stats;
    public int health;
    String characterClass;
    public String name;

    public Foe(String name, String characterClass) {
        super(name, characterClass);
        this.stats=super.stats;
        this.health=super.health;
        this.name=super.name;
        this.characterClass=super.characterClass;
    }

}
