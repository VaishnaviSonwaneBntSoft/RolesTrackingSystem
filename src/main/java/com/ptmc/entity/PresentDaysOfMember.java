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
public class PresentDaysOfMember {

    @Id
    @Generated(value = "identity")
    private Integer id;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP" , name="timestmp" , insertable = false)
    private LocalDateTime timestamp;
    
    private Integer meetingNumber;

    private LocalDate dateOfMeeting;

    private Integer memberId;

    private boolean isPresent;

}
