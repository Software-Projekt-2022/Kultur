package com.api.database;

import jdk.vm.ci.meta.Local;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Message {

    private int id;
    private String content;
    private LocalDateTime date;
    private int chat_id;
    //private User sender;

    public Message() {
    }

    public Message(int chat_id, String content, LocalDateTime date) {
        this.chat_id = chat_id;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public int getChatId() {
        return chat_id;
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
                message.date = rs.getTimestamp("date").toLocalDateTime();
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
                message.date = rs.getTimestamp("date").toLocalDateTime();
                messages.add(message);
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }


}
