package com.ptmc.service;

import java.util.List;

import com.ptmc.entity.Member;

public interface MemberService {
    Member createMember(Member member);

    Member getMember(String memberNumber);

    void updateMember(String memberNumber, Member member);

    void deleteMember(String memberNumber);

    List<Member> getAllMembers();
}
