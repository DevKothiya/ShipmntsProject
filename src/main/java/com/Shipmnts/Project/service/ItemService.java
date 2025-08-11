package com.Shipmnts.Project.service;

import com.Shipmnts.Project.model.Item;
import com.Shipmnts.Project.model.Plan;
import com.Shipmnts.Project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item getItemByName(String name, Plan plan){
        return itemRepository.findByNameAndPlan(name, plan);
    }

    public Long getAverageByCategoryInHalfLength( String category, Plan plan){
        return itemRepository.getAverageByCategoryInHalfLength(category,plan);
    }

    public Long getAverageByCategoryInFullLength(String category, Plan plan){
        return itemRepository.getAverageByCategoryInFullLength(category,plan);
    }

}
