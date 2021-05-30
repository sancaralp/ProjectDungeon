package sample;

import sample.UserInterface;

public class Combat extends UserInterface {
    int rogueCrit=0;
    int rage = 0;

    public String d6Dice ="";
    public String d20Dice ="";
    public String SpecialAttack="";



    public void attack(Player attacker, Foe defender){
        SpecialAttack = "";
        System.out.println("\nIt is time for "+attacker.name+"'s attack");
        int dice = D(20,1,mod("dex",attacker));

        if (dice>=defender.AC&&dice!=20) {
            d20Dice=("Attack successful");
            attackAction(attacker, defender,false);
            if(attacker.characterClass.equalsIgnoreCase("rogue")&&rogueCrit==0){
                rogueCrit++;
                int d8 = D(8,1,mod("str",attacker));
               // d6Dice+=("+"+mod("str",attacker)+"="+d6);
                d6Dice+= ("\n"+attacker.name+"'s first attack dealt another damage of "+d8);
                defender.health -= Math.max(d8, 4);
            }
        }
        else if (dice==20){
            d20Dice=("CRITICAL HIT!"+" ");
            if(attacker.characterClass.equalsIgnoreCase("rogue")&&rogueCrit==0){
                rogueCrit++;
                int d6 = D(6,1,mod("str",attacker));

                d6=D(6,1,mod("str",attacker));
                // d6Dice+=("+"+mod("str",attacker)+"="+d6);
                d6Dice+= ("\n"+attacker.name+"'s first attack dealt another damage of "+d6);
                defender.health -= Math.max(d6, 1);
            }
            attackAction(attacker,defender,true);
        }
        else {d20Dice=("Missed!"+"\n");d6Dice="";}

        if(defender.characterClass.equals("Boss")){defender.health+=mod("arc",defender);
            SpecialAttack=("But "+defender.name+" managed to heal themselves for "+mod("arc",defender)+"points their new hp is "+defender.health);}
    }

    public void attackToPlayer(Foe attacker){
        SpecialAttack = "";
        Player defender=new Player();
        System.out.println("\nIt is time for "+attacker.name+"'s attack");
        int dice = D(20,1,mod("dex",attacker));

        if (dice>=defender.AC&&dice!=20) {
            d20Dice=("Attack successful");
            attackAction(attacker, defender,false);
        }
        else if (dice==20){
            d20Dice=("CRITICAL HIT!");
            attackAction(attacker,defender,true);
        }
       else {d20Dice=("Missed!"+"\n");d6Dice="";}
        System.out.println(defender.name+"'s new hp is "+defender.health);

        if(defender.characterClass.equals("Mage")){defender.health+=mod(defender.stats[3]);
            SpecialAttack=("But "+defender.name+" managed to heal themselves for "+mod(defender.stats[3])+" their new hp is "+defender.health);}

        if(rage==0 &&Player.characterClass.equals("Berserker")&& defender.health<=((double)defender.health*40.0/100.0)){
            defender.health+=5;
            defender.stats[0]+=3;
            defender.stats[2]-=2;
            rage ++;
           // defender.canRage=false;
            SpecialAttack=(defender.name+"'s veins are becoming visible, and he starts to enlarge! Apparently he is on the RAGE! \n"+defender.name+"'s health is restored for 5 points, strength is increased by 3 points and their dexterity is decreased by 1 points");
        }
    }




    private void attackAction(Player attacker, Foe defender, Boolean crit){
        int d6=D(6,1,mod("str",attacker));

        defender.health -= Math.max(d6, 1);
        d6Dice=(attacker.name+"'s attack dealt 1d6="+Math.max(d6,1)+" damage to "+ defender.name);


        if(crit) { d6=D(6,1,mod("str",attacker));
            defender.health -= Math.max(d6, 1);
            d6Dice+=("\n"+attacker.name+"'s attack dealt extra 1d6="+Math.max(d6,1)+" damage to "+ defender.name);}

        if(defender.health<=0) {
            defender.isDead = true;
            System.out.println(defender.name + " is dead, " + attacker.name + " won the fight");
        }
    }


    private void attackAction(Foe attacker,Player defender, Boolean crit){
        int d6=D(6,1,mod("str",attacker));
        // If you're reading this you should know that you are special for us.
        defender.health -= Math.max(d6, 1);
        d6Dice=(attacker.name+"'s attack dealt 1d6="+Math.max(d6,1)+" damage to "+ defender.name);


        if(crit) { d6=D(6,1,mod("str",attacker));
            defender.health -= Math.max(d6, 1);
            d6Dice+=(attacker.name+"'s attack dealt extra 1d6="+Math.max(d6,1)+" damage to "+ defender.name);
        }

        if(defender.health<=0) {
            defender.isDead = true;
            System.out.println(defender.name + " is dead, " + attacker.name + " won the fight");
        }
    }


}
