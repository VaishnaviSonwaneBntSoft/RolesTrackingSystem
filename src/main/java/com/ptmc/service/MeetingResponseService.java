package com.ptmc.service;

import java.util.List;

import com.ptmc.entity.MeetingResponse;

public interface MeetingResponseService {

    MeetingResponse createMeetingResponse(MeetingResponse request);

    MeetingResponse getMeetingResponse(String memberNumber, Long meetingNumber);

    MeetingResponse updateMeetingResponse(String memberNumber, Long meetingNumber, MeetingResponse request);

    void deleteMeetingResponse(String memberNumber, Long meetingNumber);

    List<MeetingResponse> getAllMeetingResponses();

}
