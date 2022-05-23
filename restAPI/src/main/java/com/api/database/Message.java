package com.api.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {

    private int id;
    private String content;
    private String date;
    private int chat_id;
    //private User sender;

    public Message() {
    }

    public Message(int chat_id, String content, String date) {
        this.chat_id = chat_id;
        this.content = content;
        this.date = date;
    }

    public void addToDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            this.id = conn.createStatement().executeUpdate(
                    "INSERT INTO message (content, date, chat) " +
                    "VALUES ('" + content + "', '" + date + "', '" + chat_id + "') " +
                            "RETURNING id");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Message getMessageById(int id) {
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM message WHERE id = " + id);
            if (rs.next()) {
                Message message = new Message();
                message.id = rs.getInt("id");
                message.content = rs.getString("content");
                message.date = rs.getTimestamp("date").toString();
                return message;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Message> getMessagesByChatId(int chat_id) {
        Connection conn = Database.getDBConnection();
        ArrayList<Message> messages = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM message WHERE chat = " + chat_id);
            while (rs.next()) {
                Message message = new Message();
                message.id = rs.getInt("id");
                message.content = rs.getString("content");
                message.date = rs.getTimestamp("date").toString();
                messages.add(message);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public int getChatId() {
        return chat_id;
    }

}
