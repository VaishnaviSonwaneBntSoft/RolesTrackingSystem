package com.ptmc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ptmc.entity.AllocateRole;

@Repository
public interface AllocateRoleRepository extends JpaRepository<AllocateRole , UUID>{

    AllocateRole findByAllocationNumber(Long allocationNumber);

    @Query("SELECT COALESCE(MAX(a.allocationNumber), 0) FROM AllocateRole a")
    Long getMaxAllocationNumber();

    @Query("SELECT a FROM AllocateRole a WHERE a.meetingNumber = :meetingNumber AND a.memberNumber = :memberNumber AND a.roleName = :roleName")
    List<AllocateRole> findByMeetingNumberAndMemberNumberAndRoleName(@Param("meetingNumber") Long meetingNumber, 
                                                                @Param("memberNumber") String memberNumber, 
                                                                @Param("roleName") String roleName);


}
