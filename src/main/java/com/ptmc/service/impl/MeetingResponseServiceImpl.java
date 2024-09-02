package com.ptmc.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ptmc.entity.MeetingResponse;
import com.ptmc.repository.MeetingResponseRepository;
import com.ptmc.service.MeetingResponseService;

@Service
public class MeetingResponseServiceImpl implements MeetingResponseService{

    private final MeetingResponseRepository meetingResponseRepository;

    public MeetingResponseServiceImpl(MeetingResponseRepository meetingResponseRepository) {
        this.meetingResponseRepository = meetingResponseRepository;
    }

    public MeetingResponse createMeetingResponse(MeetingResponse meetingResponse) {
        return meetingResponseRepository.save(meetingResponse);
    }

    public MeetingResponse getMeetingResponse(String memberNumber, Long meetingNumber) {
        return meetingResponseRepository.findByMemberNumberAndMeetingNumber(memberNumber, meetingNumber);
    }

    public MeetingResponse updateMeetingResponse(String memberNumber, Long meetingNumber, MeetingResponse request) {
        MeetingResponse meetingResponse = meetingResponseRepository.findByMemberNumberAndMeetingNumber(memberNumber, meetingNumber);
        if (meetingResponse != null) {
            meetingResponse.setResponse(request.getResponse());
            return meetingResponseRepository.save(meetingResponse);
        }
        return null;
    }

    public void deleteMeetingResponse(String memberNumber, Long meetingNumber) {
        MeetingResponse meetingResponse = meetingResponseRepository.findByMemberNumberAndMeetingNumber(memberNumber, meetingNumber);
        if (meetingResponse != null) {
            meetingResponseRepository.delete(meetingResponse);
        }
    }

    public List<MeetingResponse> getAllMeetingResponses() {
        return meetingResponseRepository.findAll();
    }
}
