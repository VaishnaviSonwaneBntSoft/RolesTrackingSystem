package com.ptmc.request;


import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class AllocateRoleRequest {

    @NonNull
    private Long allocationNumber;

    @NonNull
    private String memberName;

    @NonNull
    private String roleName;

    @NonNull
    private Long meetingNumber;

}
