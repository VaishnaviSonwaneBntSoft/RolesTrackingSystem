package com.ptmc.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ptmc.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting , UUID>{

    Meeting findByMeetingNumber(Long meetingNumber);

}
