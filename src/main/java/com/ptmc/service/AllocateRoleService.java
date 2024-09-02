package com.ptmc.service;

import java.util.List;


import com.ptmc.entity.AllocateRole;
import com.ptmc.request.AllocateRoleRequest;

public interface AllocateRoleService {

    List<AllocateRole> getAllAllocateRoles();

    void deleteAllocateRole(Long allocationNumber);

    AllocateRole updateAllocateRole(Long allocationNumber, AllocateRole allocateRole);

    AllocateRole getAllocateRoleByNumber(Long allocationNumber);

    AllocateRole createAllocateRole(AllocateRole allocateRole);

    void createAllocateRoles(List<AllocateRoleRequest> allocateRoles);

}
