package com.ptmc.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ptmc.constant.AllocateRoleResponseMessage;
import com.ptmc.entity.AllocateRole;
import com.ptmc.request.AllocateRoleRequest;
import com.ptmc.service.AllocateRoleService;

@RestController
@RequestMapping("/api/allocate-role")
@CrossOrigin(origins = "*")
public class AllocateRoleController {

    private static final Logger logger = LoggerFactory.getLogger(AllocateRoleController.class);

    private final AllocateRoleService allocateRoleService;

    public AllocateRoleController(AllocateRoleService allocateRoleService) {
        this.allocateRoleService = allocateRoleService;
    }

    @PostMapping
    public ResponseEntity<AllocateRole> createAllocateRole(@RequestBody AllocateRole allocateRole) {
        logger.info("Request received for creating allocation role with member name: {}", allocateRole.getMemberNumber());
        AllocateRole allocateRoleResponse = allocateRoleService.createAllocateRole(allocateRole);
        return ResponseEntity.status(HttpStatus.CREATED).body(allocateRoleResponse);
    }

    @PostMapping("/batch")
    public ResponseEntity<String> createAllocateRoles(@RequestBody @Validated List<AllocateRoleRequest> allocateRoles) {
        logger.info("Request received for creating allocation roles");
        allocateRoleService.createAllocateRoles(allocateRoles);
        return ResponseEntity.status(HttpStatus.CREATED).body(AllocateRoleResponseMessage.LIST_OF_ALLOCATE_ROLE_SAVED_SUCCESSFULLY.getMessage());
    }

    @GetMapping("/{allocation-number}")
    public ResponseEntity<AllocateRole> getAllocateRole(@PathVariable("allocation-number") Long allocationNumber) {
        logger.info("Request received for fetching allocation role with number: {}", allocationNumber);
        AllocateRole allocateRoleResponse = allocateRoleService.getAllocateRoleByNumber(allocationNumber);
        return ResponseEntity.status(HttpStatus.OK).body(allocateRoleResponse);
    }

    @PutMapping("/{allocation-number}")
    public ResponseEntity<AllocateRole> updateAllocateRole(@PathVariable("allocation-number") Long allocationNumber,
            @RequestBody AllocateRole allocateRole) {
        logger.info("Request received for updating allocation role with number: {}", allocationNumber);
        AllocateRole updatedAllocateRole = allocateRoleService.updateAllocateRole(allocationNumber, allocateRole);
        return ResponseEntity.status(HttpStatus.OK).body(updatedAllocateRole);
    }

    @DeleteMapping("/{allocation-number}")
    public ResponseEntity<String> deleteAllocateRole(@PathVariable("allocation-number") Long allocationNumber) {
        logger.info("Request received for deleting allocation role with number: {}", allocationNumber);
        allocateRoleService.deleteAllocateRole(allocationNumber);
        return ResponseEntity.status(HttpStatus.OK).body(AllocateRoleResponseMessage.ALLOCATE_ROLE_UPDATED_SUCCESSFULLY.getMessage(allocationNumber));
    }

    @GetMapping
    public ResponseEntity<List<AllocateRole>> getAllAllocateRoles() {
        logger.info("Request received for fetching all allocation roles");
        List<AllocateRole> allocateRolesList = allocateRoleService.getAllAllocateRoles();
        return ResponseEntity.status(HttpStatus.OK).body(allocateRolesList);
    }
}
