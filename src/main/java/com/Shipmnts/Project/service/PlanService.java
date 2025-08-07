package com.Shipmnts.Project.service;

import com.Shipmnts.Project.helper.PlanResponse;
import com.Shipmnts.Project.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

}
