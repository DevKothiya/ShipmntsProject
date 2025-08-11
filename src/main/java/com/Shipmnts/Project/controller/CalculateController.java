package com.Shipmnts.Project.controller;

import com.Shipmnts.Project.model.Item;
import com.Shipmnts.Project.model.OrderRequest;
import com.Shipmnts.Project.model.Plan;
import com.Shipmnts.Project.model.Store;
import com.Shipmnts.Project.service.ItemService;
import com.Shipmnts.Project.service.PdfService;
import com.Shipmnts.Project.service.PlanService;
import com.Shipmnts.Project.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

@RestController
public class CalculateController {

    @Autowired
    private PlanService planService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/calculate")
    public ResponseEntity<Map<String,Object>> calculate(@RequestParam(defaultValue = "false") boolean pdf, @RequestBody OrderRequest orderRequest){

        Map<String,Object> bill=new HashMap<>();
        String storeLocation= orderRequest.getStoreLocation();
        LocalDate orderDate=orderRequest.getOrderDate();
        Plan plan=planService.getCurrentPlan(storeLocation,orderDate,orderDate);
        System.out.println(orderDate+ " "+orderDate.getClass().getName());
        Map<String,HashSet<String>> selections=orderRequest.getSelections();
        String length=orderRequest.getLength();
        Store store=storeService.getStore(storeLocation);
        HashSet<String> premiumItems=store.getPremiumItems();
        long price=0L;
        HashMap<String,Long> itemCal=new HashMap<>();



        for(Map.Entry<String,HashSet<String>> cat:selections.entrySet()){
            String category=cat.getKey();
            HashSet<String> items=cat.getValue();

            for(String item:items){
                Item current = itemService.getItemByName(item,plan);
                System.out.println("half "+item + " " + current);
                if(current!=null) {
                    long curPrice=length.equals("half")?current.getHalfPrice():current.getFullPrice();
                    price+=curPrice;
                    itemCal.put(item,itemCal.getOrDefault(item,0L)+curPrice);
                    if(premiumItems.contains(item)){
                        curPrice=length.equals("half")?20*current.getHalfPrice()/100:20*current.getFullPrice()/100;
                        price+=curPrice;
                        itemCal.put(item,itemCal.getOrDefault(item,0L)+curPrice);

                    }
                }
                else{
                    long curPrice=length.equals("half")?itemService.getAverageByCategoryInHalfLength(category,plan):
                            itemService.getAverageByCategoryInFullLength(category,plan);
                    price+=curPrice;
                    itemCal.put(item,itemCal.getOrDefault(item,0L)+curPrice);
                }
            }

        }
        Map<String,HashSet<String>> extras=orderRequest.getExtras();
        if(extras!=null) {
            for (Map.Entry<String, HashSet<String>> cat : extras.entrySet()) {
                HashSet<String> items = cat.getValue();

                for (String item : items) {

                    Item current = itemService.getItemByName(item, plan);
                    System.out.println("extra "+item + " " + current);
                    if(current!=null) {
                        price += current.getExtraCharge();
                        itemCal.put(item, itemCal.getOrDefault(item, 0L) + current.getExtraCharge());
                    }
                }

            }
        }
        List<ItemRate> itemRateList=new ArrayList<>();
        for(Map.Entry<String,Long> rate:itemCal.entrySet()){
            itemRateList.add(new ItemRate(rate.getKey(), rate.getValue()));
        }
        bill.put("store_location",storeLocation);
        bill.put("currency",store.getCurrency());
        bill.put("items",itemRateList);
        bill.put("length",length);
        bill.put("total_before_tax",price);
        double tax = store.getTaxPercentage() / 100.0 * price;
        double afterTax =price+ Math.round(tax * 100.0) / 100.0;
        bill.put("tax_percentage",store.getTaxPercentage());
        bill.put("total_after_tax",afterTax);

        if(pdf){
            String directory = "D:\\docs\\";
            String filename = "bill_0"  + ".pdf";  // more unique
            String fullPath = directory + filename;

            pdfService.generatePdf(bill, fullPath);

            bill.put("pdf_path", fullPath);

        }
        return new ResponseEntity<>(bill, HttpStatus.OK);



    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class ItemRate{
        private String name;
        private long rate;
    }
}
