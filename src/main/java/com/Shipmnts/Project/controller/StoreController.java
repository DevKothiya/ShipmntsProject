package com.Shipmnts.Project.controller;

import com.Shipmnts.Project.helper.StoreResponse;
import com.Shipmnts.Project.model.Store;
import com.Shipmnts.Project.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private StoreService storeService;


    @GetMapping("{location}")
    public  ResponseEntity<Store> getStore(@PathVariable String location){
        return new ResponseEntity<>(storeService.getStore(location),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<StoreResponse> createStore(@RequestBody Store store){
        StoreResponse storeResponse=storeService.saveStore(store);
        return new ResponseEntity<StoreResponse>(storeResponse, storeResponse.isSuccess()?HttpStatus.OK:HttpStatus.BAD_REQUEST);
    }

    @PutMapping("{location}")
    public  ResponseEntity<StoreResponse> updateStore(@RequestBody Store store, @PathVariable String location){
        return new ResponseEntity<>(storeService.updateStore(store,location),HttpStatus.OK);
    }

}
