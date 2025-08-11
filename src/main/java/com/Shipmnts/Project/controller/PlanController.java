package com.Shipmnts.Project.controller;

import com.Shipmnts.Project.helper.PlanResponse;
import com.Shipmnts.Project.model.Plan;
import com.Shipmnts.Project.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @PostMapping
    public ResponseEntity<PlanResponse> createPlan(@RequestBody Plan plan){
        PlanResponse planResponse=planService.createPlan(plan);

        return new ResponseEntity<>(planResponse, planResponse.isSuccess()? HttpStatus.OK: HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlan(@PathVariable Long id){
        Plan plan=planService.getPlan(id);
        return new ResponseEntity<>(plan,HttpStatus.OK);
    }
}
