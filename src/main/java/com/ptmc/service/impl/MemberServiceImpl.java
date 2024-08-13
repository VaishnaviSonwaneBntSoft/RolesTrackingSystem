package com.ptmc.service.impl;

import org.springframework.stereotype.Service;

import com.ptmc.entity.Member;
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

}
