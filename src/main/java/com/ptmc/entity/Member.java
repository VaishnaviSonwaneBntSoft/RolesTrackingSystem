package com.ptmc.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Member {

    @Id
    @Generated(value = "Identity")
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" , name="timestmp" , insertable = false)
    private LocalDateTime timestamp;

    private String firstName;

    private String lastName;

    private Long phoneNumber;

    private String emailAddress;

    private String occupation;

    private LocalDate dateOfBirth;

    private boolean isExMember;

    private LocalDate dateOfJoining;
}
