package com.ptmc.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ptmc.constant.MemberResponseMessage;
import com.ptmc.entity.Member;
import com.ptmc.exception.MemberException;
import com.ptmc.repository.MemberRepository;
import com.ptmc.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public Member createMember(Member member) {
        String workFlow = "MemberServiceImpl.createMember";
        Member existingMember = memberRepository.findByMemberNumber(member.getMemberNumber());
        if (existingMember != null) {
            throw new MemberException(MemberResponseMessage.MEMBER_EXITS_ALREADY.getMessage(member.getMemberNumber()),
                    HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workFlow);
        }
        return  memberRepository.save(member);
    }


    @Override
    public Member getMember(String memberNumber) {
        String workFlow = "MemberServiceImpl.getMember";

        Member existingMember = memberRepository.findByMemberNumber(memberNumber);
        if (existingMember == null) {
            throw new MemberException(MemberResponseMessage.MEMBER_NOT_FOUND.getMessage(memberNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }
        return memberRepository.findByMemberNumber(memberNumber);
    }


    @Override
    public void updateMember(String memberNumber, Member member) {
        String workFlow = "MemberServiceImpl.updateMember";

        Member existingMember = memberRepository.findByMemberNumber(memberNumber);
        if (existingMember == null) {
            throw new MemberException(MemberResponseMessage.MEMBER_NOT_FOUND.getMessage(memberNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }
        member.setMemberId(existingMember.getMemberId());
        member.setMemberNumber(existingMember.getMemberNumber());
        member.setDeleted(existingMember.isDeleted());
        memberRepository.save(member);
    }

    
    @Override
    public void deleteMember(String memberNumber) {
        String workFlow = "MemberServiceImpl.deleteMember";

        Member existingMember = memberRepository.findByMemberNumber(memberNumber);
        if (existingMember == null) {
            throw new MemberException(MemberResponseMessage.MEMBER_NOT_FOUND.getMessage(memberNumber),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }
        memberRepository.softDeleteMember(memberNumber);
    }


    @Override
    public List<Member> getAllMembers() {
        String workFlow = "MemberServiceImpl.getAllMembers()";
        try{
             return memberRepository.findAll();
        }catch(MemberException exception){
            throw new MemberException(MemberResponseMessage.FAILED_TO_FETCH_MEMBER_LIST.getMessage(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workFlow);
        }
    }

}
