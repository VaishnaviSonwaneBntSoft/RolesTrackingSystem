package com.ptmc.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptmc.entity.MeetingResponse;

@Repository
public interface MeetingResponseRepository extends JpaRepository<MeetingResponse , UUID>{

    MeetingResponse findByMemberNumberAndMeetingNumber(String memberNumber, Long meetingNumber);

}
