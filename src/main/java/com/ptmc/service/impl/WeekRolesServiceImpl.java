package com.ptmc.service.impl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ptmc.constant.WeekRolesResponseMessage;
import com.ptmc.entity.WeekRoles;
import com.ptmc.exception.WeekRolesException;
import com.ptmc.repository.WeekRolesRepository;
import com.ptmc.service.WeekRolesService;

@Service
public class WeekRolesServiceImpl implements WeekRolesService {

    private final WeekRolesRepository weekRolesRepository;

    public WeekRolesServiceImpl(WeekRolesRepository weekRolesRepository) {
        this.weekRolesRepository = weekRolesRepository;
    }

    @Override
    public WeekRoles createWeekRoles(WeekRoles weekRoles) {
        String workFlow = "WeekRolesServiceImpl.createWeekRoles";
        WeekRoles existingWeekRoles = weekRolesRepository.findByTitle(weekRoles.getTitle());
        if (existingWeekRoles != null) {
            throw new WeekRolesException(WeekRolesResponseMessage.WEEK_ROLES_EXISTS_ALREADY.getMessage(weekRoles.getTitle()),
                    HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workFlow);
        }
        return weekRolesRepository.save(weekRoles);
    }

    @Override
    public void updateWeekRoles(String title, WeekRoles weekRoles) {
        String workFlow = "WeekRolesServiceImpl.updateWeekRoles";
        WeekRoles existingWeekRoles = weekRolesRepository.findByTitle(title);
        if (existingWeekRoles == null) {
            throw new WeekRolesException(WeekRolesResponseMessage.WEEK_ROLES_NOT_FOUND.getMessage(title),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }
        weekRoles.setWeekRolesId(existingWeekRoles.getWeekRolesId());
        weekRolesRepository.save(weekRoles);
    }

    @Override
    public void deleteWeekRoles(String title) {
        String workFlow = "WeekRolesServiceImpl.deleteWeekRoles";
        WeekRoles existingWeekRoles = weekRolesRepository.findByTitle(title);
        if (existingWeekRoles == null) {
            throw new WeekRolesException(WeekRolesResponseMessage.WEEK_ROLES_NOT_FOUND.getMessage(title),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }
        weekRolesRepository.deleteByTitle(title);
    }

    @Override
    public WeekRoles getWeekRoles(String title) {
        String workFlow = "WeekRolesServiceImpl.getWeekRoles";
        WeekRoles existingWeekRoles = weekRolesRepository.findByTitle(title);
        if (existingWeekRoles == null) {
            throw new WeekRolesException(WeekRolesResponseMessage.WEEK_ROLES_NOT_FOUND.getMessage(title),
                    HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, workFlow);
        }
        return existingWeekRoles;
    }

    @Override
    public List<WeekRoles> getAllWeekRoles() {
        String workFlow = "WeekRolesServiceImpl.getAllWeekRoles";

        try{
            return weekRolesRepository.findAll();
        }catch(WeekRolesException exception){
            throw new WeekRolesException(WeekRolesResponseMessage.WEEK_ROLES_FAILED_TO_FETCH.getMessage(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, workFlow);
        }
    }
}
