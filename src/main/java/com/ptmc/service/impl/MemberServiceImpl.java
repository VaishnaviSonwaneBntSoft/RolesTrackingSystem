package com.ptmc.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    
        return  memberRepository.save(member);
    }


    @Override
    public Member getMember(String memberNumber) {
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
        return memberRepository.findAll();
    }

}
