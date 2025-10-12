/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ulti;

import java.sql.Timestamp;

/**
 *
 * @author Administrator
 */
public class Room {
        public int id;
        public String name;
        public int createdBy;
        public Timestamp createdAt;

        public Room(int id, String name, int createdBy, Timestamp createdAt) {
            this.id = id;
            this.name = name;
            this.createdBy = createdBy;
            this.createdAt = createdAt;
        }
    }
