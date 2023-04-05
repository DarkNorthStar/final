package ca.tabletop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TabletopDatabase 
{
    private static Connection connection;

    public TabletopDatabase() throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:sqlite:TabletopDatabase.db");
    }

    public Character getCharacter(int id) throws SQLException
    {
        String getCharacterStatment = "Select * FROM Characters WHERE ID =?";
        PreparedStatement preparedGetCharacter = connection.prepareStatement(getCharacterStatment);
        preparedGetCharacter.setInt(0, id);
        ResultSet results = preparedGetCharacter.executeQuery();
        Character character = new Character(results.getString("characterName"), results.getString("characterClass"), results.getInt("level"), results.getString("alignment"),
         results.getInt("xp"), results.getInt("hp"), results.getString("bio"), results.getInt("strength"), results.getInt("dexterity"), results.getInt("constitution"), results.getInt("intelligence"),
         results.getInt("wisdom"),results.getInt("charisma"));
        return character;
    }
    public void setCharacter(Character character) throws SQLException
    {
        String setCharcterStatment = "INSERT INTO Characters(characterName, characterClass, level, alignment, xp, hp, bio, strength, dexterity, constitution, intelligence, wisdom, charisma) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedSetCharacter = connection.prepareStatement(setCharcterStatment);
        preparedSetCharacter.setString(1, character.getCharacterName());
        preparedSetCharacter.setString(2, character.getCharacterClass());
        preparedSetCharacter.setInt(3, character.getLevel());
        preparedSetCharacter.setString(4, character.getAlignment());
        preparedSetCharacter.setInt(5, character.getXp());
        preparedSetCharacter.setInt(6,character.getHp());
        preparedSetCharacter.setString(7, character.getBio());
        preparedSetCharacter.setInt(8,character.getStrength());
        preparedSetCharacter.setInt(9,character.getDexterity());
        preparedSetCharacter.setInt(10,character.getConstitution());
        preparedSetCharacter.setInt(11,character.getIntelligence());
        preparedSetCharacter.setInt(12,character.getWisdom());
        preparedSetCharacter.setInt(13,character.getCharisma());

        preparedSetCharacter.executeUpdate();
    }

    public Character getNPC(int id) throws SQLException
    {
        String getNPCStatment = "Select * FROM NPCs WHERE ID =?";
        PreparedStatement preparedGetNPC = connection.prepareStatement(getNPCStatment);
        preparedGetNPC.setInt(0, id);
        ResultSet results = preparedGetNPC.executeQuery();
        Character character = new Character(results.getString("characterName"), results.getString("characterClass"), results.getInt("level"), results.getString("alignment"),
         results.getInt("xp"), results.getInt("hp"), results.getString("bio"), results.getInt("strength"), results.getInt("dexterity"), results.getInt("constitution"), results.getInt("intelligence"),
         results.getInt("wisdom"),results.getInt("charisma"));
        return character;
    }
    public void setNPC(Character character) throws SQLException
    {
        String setNPCStatment = "INSERT INTO NPCs(characterName, characterClass, level, alignment, xp, hp, bio, strength, dexterity, constitution, intelligence, wisdom, charisma) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedSetNPC = connection.prepareStatement(setNPCStatment);
        preparedSetNPC.setString(1, character.getCharacterName());
        preparedSetNPC.setString(2, character.getCharacterClass());
        preparedSetNPC.setInt(3, character.getLevel());
        preparedSetNPC.setString(4, character.getAlignment());
        preparedSetNPC.setInt(5, character.getXp());
        preparedSetNPC.setInt(6,character.getHp());
        preparedSetNPC.setString(7, character.getBio());
        preparedSetNPC.setInt(8,character.getStrength());
        preparedSetNPC.setInt(9,character.getDexterity());
        preparedSetNPC.setInt(10,character.getConstitution());
        preparedSetNPC.setInt(11,character.getIntelligence());
        preparedSetNPC.setInt(12,character.getWisdom());
        preparedSetNPC.setInt(13,character.getCharisma());

        preparedSetNPC.executeUpdate();
    }

    public Monster getMonster(int id) throws SQLException
    {
        String getMonsterStatment = "Select * FROM Monsters WHERE ID =?";
        PreparedStatement preparedGetMonster = connection.prepareStatement(getMonsterStatment);
        preparedGetMonster.setInt(0, id);
        ResultSet results = preparedGetMonster.executeQuery();
        Monster monster = new Monster(results.getString("monsterName"), results.getInt("hp"), results.getString("traits"), results.getString("bio"));
        return monster;
    }
    public void setMonster(Monster monster) throws SQLException
    {
        String setMonsterStatment = "INSERT INTO Monsters(monsterName, hp, traits, bio) VALUES(?,?,?,?)";
        PreparedStatement preparedSetMonster = connection.prepareStatement(setMonsterStatment);
        preparedSetMonster.setString(1, monster.getMonsterName());
        preparedSetMonster.setInt(2, monster.getHp());
        preparedSetMonster.setString(3, monster.getTraits());
        preparedSetMonster.setString(4, monster.getBio());

        preparedSetMonster.executeUpdate();
    }

    public void addQuest(Quest quest) throws SQLException
    {
        String addQuestStatment = "INSERT INTO Quests(title, details) VALUES(?,?)";
        PreparedStatement preparedAddQuest = connection.prepareStatement(addQuestStatment);
        preparedAddQuest.setString(1, quest.getTitle());
        preparedAddQuest.setString(2, quest.getDetails());
        preparedAddQuest.executeUpdate();   
    }

    public void addItem(Item item) throws SQLException
    {
        String addItemStatment = "INSERT INTO Items(name, weight, value) VALUES(?,?,?)";
        PreparedStatement preparedAddQuest = connection.prepareStatement(addItemStatment);
        preparedAddQuest.setString(1, item.getName());
        preparedAddQuest.setInt(2, item.getWeight());
        preparedAddQuest.setInt(3, item.getValue());

        preparedAddQuest.executeUpdate();   
    }
}
