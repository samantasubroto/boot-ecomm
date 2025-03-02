package com.example.springboot_demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="itemtype")
public class ItemType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTime;

    @PrePersist
    protected void onCreate() {
        this.creationTime = new Date();
        this.modifiedTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedTime = new Date();
    }
}
