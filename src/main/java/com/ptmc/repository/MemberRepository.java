package com.ptmc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ptmc.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member , Integer>{

}
