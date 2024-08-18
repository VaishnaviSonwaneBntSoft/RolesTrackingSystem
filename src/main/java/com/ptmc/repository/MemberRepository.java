package com.ptmc.repository;


import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptmc.entity.Member;


@Repository
public interface MemberRepository extends JpaRepository<Member , UUID>{

    Member findByMemberNumber(String memberNumber);

    @Modifying
    @Query("UPDATE Member m SET m.deleted = true WHERE m.memberNumber = :memberNumber")
    void softDeleteMember(@Param("memberNumber") String memberNumber);

}
