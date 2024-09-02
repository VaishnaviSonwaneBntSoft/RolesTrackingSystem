package com.ptmc.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ptmc.constant.AllocateRoleResponseMessage;
import com.ptmc.entity.AllocateRole;
import com.ptmc.entity.Member;
import com.ptmc.exception.AllocateRoleException;
import com.ptmc.repository.AllocateRoleRepository;
import com.ptmc.repository.MemberRepository;
import com.ptmc.request.AllocateRoleRequest;
import com.ptmc.service.AllocateRoleService;


@Service
public class AllocateRoleServiceImpl implements AllocateRoleService {

    private final AllocateRoleRepository allocateRoleRepository;
    private final MemberRepository memberRepository;

    public AllocateRoleServiceImpl(AllocateRoleRepository allocateRoleRepository, MemberRepository memberRepository) {
        this.allocateRoleRepository = allocateRoleRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public AllocateRole createAllocateRole(AllocateRole allocateRole) {
        String workFlow = "AllocateRoleServiceImpl.createAllocateRole";

        Long allocateNumber = allocateRoleRepository.getMaxAllocationNumber();
        allocateRole.setAllocationNumber(allocateNumber);

        AllocateRole existingAllocateRole = allocateRoleRepository.findByAllocationNumber(allocateNumber);

        if (existingAllocateRole != null) {
            throw new AllocateRoleException(
                AllocateRoleResponseMessage.ALLOCATE_ROLE_EXITS_ALREADY.getMessage(allocateRole.getAllocationNumber()),
                HttpStatus.CONFLICT,
                HttpStatus.CONFLICT.value(),
                workFlow
            );
        }
        allocateRole.setTimestamp(LocalDateTime.now());
        return allocateRoleRepository.save(allocateRole);
    }

    @Override
    public void createAllocateRoles(List<AllocateRoleRequest> allocateRoleRequests) {
        String workFlow = "AllocateRoleServiceImpl.createAllocateRoles";
        Set<AllocateRole> uniqueRoles = new HashSet<>();

        for (AllocateRoleRequest request : allocateRoleRequests) {
           
            String[] nameParts = request.getMemberName().split(" ", 2);
            if (nameParts.length < 2) {
                throw new RuntimeException("Invalid member name format: " + request.getMemberName());
            }

            String firstName = nameParts[0];
            String lastName = nameParts[1];

            Member member = memberRepository.findByFirstNameAndLastName(firstName, lastName);

            if (member != null) {
                
                String memberNumber = member.getMemberNumber();

                AllocateRole allocateRole = new AllocateRole();
                allocateRole.setAllocateRoleId(UUID.randomUUID()); 
                allocateRole.setAllocationNumber(allocateRoleRepository.getMaxAllocationNumber()+1L);
                allocateRole.setMemberNumber(memberNumber);
                allocateRole.setRoleName(request.getRoleName());
                allocateRole.setMeetingNumber(request.getMeetingNumber());
                allocateRole.setDeleted(false);

                if (uniqueRoles.add(allocateRole)) {
                    
                    List<AllocateRole> existingRoles = allocateRoleRepository.findByMeetingNumberAndMemberNumberAndRoleName(
                            allocateRole.getMeetingNumber(), allocateRole.getMemberNumber(), allocateRole.getRoleName());

                    if (existingRoles.isEmpty()) {
                        allocateRoleRepository.save(allocateRole);
                    }
                }
            } else {
                throw new AllocateRoleException(AllocateRoleResponseMessage.FAILED_TO_FETCH_ALLOCATE_ROLE_LIST.getMessage(), HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), workFlow);
            }
        }
    }

    @Override
    public AllocateRole getAllocateRoleByNumber(Long allocationNumber) {
        String workFlow = "AllocateRoleServiceImpl.getAllocateRoleByNumber";

        AllocateRole allocateRole = allocateRoleRepository.findByAllocationNumber(allocationNumber);
        if (allocateRole == null) {
            throw new AllocateRoleException(
                AllocateRoleResponseMessage.ALLOCATE_ROLE_NOT_FOUND.getMessage(allocationNumber),
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                workFlow
            );
        }
        return allocateRole;
    }

    @Override
    public AllocateRole updateAllocateRole(Long allocationNumber, AllocateRole updatedAllocateRole) {
        String workFlow = "AllocateRoleServiceImpl.updateAllocateRole";

        AllocateRole existingAllocateRole = allocateRoleRepository.findByAllocationNumber(allocationNumber);
        if (existingAllocateRole == null) {
            throw new AllocateRoleException(
                AllocateRoleResponseMessage.ALLOCATE_ROLE_NOT_FOUND.getMessage(allocationNumber),
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                workFlow
            );
        }
        existingAllocateRole.setMemberNumber(updatedAllocateRole.getMemberNumber());
        existingAllocateRole.setRoleName(updatedAllocateRole.getRoleName());
        existingAllocateRole.setMeetingNumber(updatedAllocateRole.getMeetingNumber());
        existingAllocateRole.setTimestamp(LocalDateTime.now());
        allocateRoleRepository.save(existingAllocateRole);

        return existingAllocateRole;
    }

    @Override
    public void deleteAllocateRole(Long allocationNumber) {
        String workFlow = "AllocateRoleServiceImpl.deleteAllocateRole";

        AllocateRole existingAllocateRole = allocateRoleRepository.findByAllocationNumber(allocationNumber);
        if (existingAllocateRole == null) {
            throw new AllocateRoleException(
                AllocateRoleResponseMessage.ALLOCATE_ROLE_NOT_FOUND.getMessage(allocationNumber),
                HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                workFlow
            );
        }
        allocateRoleRepository.delete(existingAllocateRole);
    }

    @Override
    public List<AllocateRole> getAllAllocateRoles() {
        String workFlow = "AllocateRoleServiceImpl.getAllAllocateRoles";

        try {
            return allocateRoleRepository.findAll();
        } catch (Exception e) {
            throw new AllocateRoleException(
                AllocateRoleResponseMessage.FAILED_TO_FETCH_ALLOCATE_ROLE_LIST.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                workFlow
            );
        }
    }



}
