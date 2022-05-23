package com.api.database;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

public enum BorrowStatus {

    BORROWED(0, "Borrowed"),
    RETURNED(1, "Returned"),
    OVERDUE(2, "Overdue"),
    LOST(3, "Lost"),
    ORDERED(4, "Ordered");

    private static HashMap<Integer, BorrowStatus> enumById = new HashMap<>();
    static {
        Arrays.stream(values()).forEach(e -> enumById.put(e.getId(), e));
    }

    public static BorrowStatus getById(int id) {
        return enumById.getOrDefault(id, RETURNED);
    }

    private int id;
    private String status;
    BorrowStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
