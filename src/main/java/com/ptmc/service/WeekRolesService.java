package com.ptmc.service;

import java.util.List;

import com.ptmc.entity.WeekRoles;

public interface WeekRolesService {

    WeekRoles createWeekRoles(WeekRoles weekRoles);

    void updateWeekRoles(String title, WeekRoles weekRoles);

    void deleteWeekRoles(String title);

    WeekRoles getWeekRoles(String title);

    List<WeekRoles> getAllWeekRoles();

}
