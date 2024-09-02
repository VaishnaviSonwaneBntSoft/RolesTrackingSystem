package com.ptmc.service;

import java.util.List;

import com.ptmc.entity.Member;
import com.ptmc.response.MemberResponse;

public interface MemberService {
    Member createMember(Member member);

    Member getMember(String memberNumber);

    void updateMember(String memberNumber, Member member);

    void deleteMember(String memberNumber);

    List<MemberResponse> getAllMembers();

    List<Member> getAllMembersList();

    boolean validateMember(String memberNumber);
}
