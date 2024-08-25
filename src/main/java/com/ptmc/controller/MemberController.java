package com.ptmc.controller;


import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptmc.constant.MemberResponseMessage;
import com.ptmc.entity.Member;
import com.ptmc.response.MemberResponse;
import com.ptmc.service.MemberService;

@RestController
@RequestMapping("/api/member")
@CrossOrigin(origins = "*")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Member member)
    {
        log.info("Request received for new member creation : {}",member);
        Member responseMember = memberService.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(responseMember);
    }

    @GetMapping("/{member-number}")
    public ResponseEntity<Member> getMember(@PathVariable("member-number")String memberNumber)
    {
        log.info("Request received for get member details by member number : {}",memberNumber);
        Member responseMember = memberService.getMember(memberNumber);
        return ResponseEntity.status(HttpStatus.FOUND.value()).body(responseMember);
    }   

    @PutMapping("/{member-number}")
    public ResponseEntity<String> updateMember(@PathVariable("member-number")String memberNumber , @RequestBody Member member)
    {
        log.info("Request received for update member with member number : {}",memberNumber);
        memberService.updateMember(memberNumber , member);
        return ResponseEntity.status(HttpStatus.OK.value()).body(MemberResponseMessage.MEMBER_UPDATED_SUCCESSFULLY.getMessage(memberNumber));
    }

    @DeleteMapping("/{member-number}")
    public ResponseEntity<String> deleteMember(@PathVariable("member-number")String memberNumber)
    {
        log.info("Request received for delete member with member number : {}",memberNumber);
        memberService.deleteMember(memberNumber);
        return ResponseEntity.status(HttpStatus.OK.value()).body(MemberResponseMessage.MEMBER_DELETED_SUCCESSFULLY.getMessage(memberNumber));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers()
    {
        log.info("Request received for get list of member");        
        List<MemberResponse> members = memberService.getAllMembers();
        return ResponseEntity.status(HttpStatus.OK.value()).body(members);
    }


}
