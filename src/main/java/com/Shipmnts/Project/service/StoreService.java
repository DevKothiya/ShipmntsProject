package com.Shipmnts.Project.service;

import com.Shipmnts.Project.helper.StoreResponse;
import com.Shipmnts.Project.model.Store;
import com.Shipmnts.Project.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public Store getStore(String location){

        return storeRepository.findByStoreLocation(location.toLowerCase());
    }
    public StoreResponse saveStore(Store store){
        Store isStore=getStore(store.getStoreLocation());

        if(isStore==null){
            store.setStoreLocation(store.getStoreLocation().toLowerCase());
            storeRepository.save(store);
            return new StoreResponse(true,"Store Created Successfully");
        }
        else{
            return new StoreResponse(false,"Store with this location already exists");

        }

    }
    public StoreResponse updateStore(Store updateStore,String location){
        Store currentStore=getStore(location);
        if(currentStore!=null){
            currentStore.setCurrency(updateStore.getCurrency());
            currentStore.setPremiumItems(updateStore.getPremiumItems());
            currentStore.setTaxPercentage(updateStore.getTaxPercentage());
            storeRepository.save(currentStore);
            return new StoreResponse(true, "Store Updated Successfully");

        }
        else{
            return new StoreResponse(true, "Store doesn't exist");

        }
    }
}
