package com.ptmc.service;

import java.util.List;

import com.ptmc.entity.MemberShip;

public interface MemberShipService {

    MemberShip createMemberShip(MemberShip memberShip);

    MemberShip getMemberShip(String memberShipTitle);

    MemberShip updateMemberShip(String memberShipTitle, MemberShip memberShip);

    void deleteMemberShip(String memberShipTitle);

    List<MemberShip> getAllMemberShip();

}
