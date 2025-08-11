package com.Shipmnts.Project.service;

import com.Shipmnts.Project.helper.PlanResponse;
import com.Shipmnts.Project.model.Plan;
import com.Shipmnts.Project.model.Store;
import com.Shipmnts.Project.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private StoreService storeService;

    public PlanResponse createPlan(Plan plan){
        Store store= storeService.getStore(plan.getStoreLocation());
        if(store !=null){
            plan.getItems().forEach((e)->e.setPlan(plan));
            Plan plan1=planRepository.save(plan);

            return new PlanResponse(plan1.getId(),plan1.getStoreLocation(),true, "Plan Created Successfully");
        }
        else{
            return new PlanResponse(-1L,"",false,"Plan not Created");
        }
    }


    public Plan getPlan(Long id) {
        return planRepository.findById(id).orElse(null);
    }
    public Plan getCurrentPlan(String storeLocation,
                               LocalDate validFrom,
                               LocalDate validTo){
        return planRepository.findByStoreLocationAndValidFromLessThanEqualAndValidToGreaterThanEqual(storeLocation,validFrom,validTo);
    }
}
