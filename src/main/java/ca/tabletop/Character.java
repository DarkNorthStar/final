package ca.tabletop;

public class Character 
{
    public Character(String characterName, String characterClass, int level, String alignment, int xp, int hp,
            String bio, int strength, int dexterity, int constitution, int intelligence, int wisdom, int charisma) {
        this.characterName = characterName;
        this.characterClass = characterClass;
        this.level = level;
        this.alignment = alignment;
        this.xp = xp;
        this.hp = hp;
        this.bio = bio;
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
    }
    // Attributes
    // Character information
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String characterName;
    public String getCharacterName() {
        return characterName;
    }
    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    private String characterClass;
    public String getCharacterClass() {
        return characterClass;
    }
    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    private int level;
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    private String alignment;
    public String getAlignment() {
        return alignment;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }

    private int xp;
    public int getXp() {
        return xp;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }

    private int hp;
    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

    private String bio;
    public String getBio() {
        return bio;
    }
    public void setBio(String bio) {
        this.bio = bio;
    }

    // Main stats
    private int strength;
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }

    private int dexterity;
    public int getDexterity() {
        return dexterity;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    private int constitution;
    public int getConstitution() {
        return constitution;
    }
    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    private int intelligence;
    public int getIntelligence() {
        return intelligence;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    private int wisdom;
    public int getWisdom() {
        return wisdom;
    }
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
    
    private int charisma;
    public int getCharisma() {
        return charisma;
    }
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    //Skill proficiency's
    boolean acrobatics;
    public boolean isAcrobatics() {
        return acrobatics;
    }
    public void setAcrobatics(boolean acrobatics) {
        this.acrobatics = acrobatics;
    }

    boolean animalHandling;
    public boolean isAnimalHandling() {
        return animalHandling;
    }
    public void setAnimalHandling(boolean animalHandling) {
        this.animalHandling = animalHandling;
    }

    boolean arcana;
    public boolean isArcana() {
        return arcana;
    }
    public void setArcana(boolean arcana) {
        this.arcana = arcana;
    }

    boolean athletics;
    public boolean isAthletics() {
        return athletics;
    }
    public void setAthletics(boolean athletics) {
        this.athletics = athletics;
    }

    boolean deception;
    public boolean isDeception() {
        return deception;
    }
    public void setDeception(boolean deception) {
        this.deception = deception;
    }

    boolean history;
    public boolean isHistory() {
        return history;
    }
    public void setHistory(boolean history) {
        this.history = history;
    }

    boolean insight;
    public boolean isInsight() {
        return insight;
    }
    public void setInsight(boolean insight) {
        this.insight = insight;
    }

    boolean intimidation;
    public boolean isIntimidation() {
        return intimidation;
    }
    public void setIntimidation(boolean intimidation) {
        this.intimidation = intimidation;
    }

    boolean investigation;
    public boolean isInvestigation() {
        return investigation;
    }
    public void setInvestigation(boolean investigation) {
        this.investigation = investigation;
    }

    boolean medicine;
    public boolean isMedicine() {
        return medicine;
    }
    public void setMedicine(boolean medicine) {
        this.medicine = medicine;
    }

    boolean nature;
    public boolean isNature() {
        return nature;
    }
    public void setNature(boolean nature) {
        this.nature = nature;
    }

    boolean perception;
    public boolean isPerception() {
        return perception;
    }
    public void setPerception(boolean perception) {
        this.perception = perception;
    }

    boolean performance;
    public boolean isPerformance() {
        return performance;
    }
    public void setPerformance(boolean performance) {
        this.performance = performance;
    }

    boolean persuasion;
    public boolean isPersuasion() {
        return persuasion;
    }
    public void setPersuasion(boolean persuasion) {
        this.persuasion = persuasion;
    }

    boolean religion;
    public boolean isReligion() {
        return religion;
    }
    public void setReligion(boolean religion) {
        this.religion = religion;
    }

    boolean sleightOfHand;
    public boolean isSleightOfHand() {
        return sleightOfHand;
    }
    public void setSleightOfHand(boolean sleightOfHand) {
        this.sleightOfHand = sleightOfHand;
    }

    boolean stealth;
    public boolean isStealth() {
        return stealth;
    }
    public void setStealth(boolean stealth) {
        this.stealth = stealth;
    }

    boolean survival;
    public boolean isSurvival() {
        return survival;
    }
    public void setSurvival(boolean survival) {
        this.survival = survival;
    }  

    // Getters

    // Setters
}
