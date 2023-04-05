package ca.tabletop;

public class Monster 
{
    public Monster(String monsterName, int hp, String traits, String bio) {
        this.monsterName = monsterName;
        this.hp = hp;
        this.traits = traits;
        this.bio = bio;
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String monsterName;
    public String getMonsterName() {
        return monsterName;
    }
    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }
    private int hp;
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }
    
    private String traits;
    public String getTraits() {
        return traits;
    }
    public void setTraits(String traits) {
        this.traits = traits;
    }

    private String bio;
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }  
}
