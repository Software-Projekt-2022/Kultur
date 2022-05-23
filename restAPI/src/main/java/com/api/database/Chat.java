package com.api.database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Chat() {
    }

    public Chat(String name) {
        this.name = name;
    }

    public void addToDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            this.id = conn.createStatement().executeUpdate(
                    "INSERT INTO chat (name) " +
                    "VALUES ('" + name + "') " +
                            "RETURNING id");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Chat> getList() {
        Connection conn = Database.getDBConnection();
        ArrayList<Chat> chats = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM chat");
            while (rs.next()) {
                Chat chat = new Chat();
                chat.id = rs.getInt("id");
                chat.name = rs.getString("name");
                chats.add(chat);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats;
    }

    public static Chat getById(int id) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM chat WHERE id = " + id);
            if (rs.next()) {
                Chat chat = new Chat();
                chat.id = rs.getInt("id");
                chat.name = rs.getString("name");
                return chat;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addMessage(Message message) {
        Connection conn = Database.getDBConnection();
        try {
            conn.createStatement().executeUpdate(
                    "INSERT INTO message (content, date, chat) " +
                    "VALUES ('" + message.getContent() + "', '" + message.getDate() + "', '" + this.id + "')");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
