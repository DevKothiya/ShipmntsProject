package com.Shipmnts.Project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @JsonProperty("store_location")
    private String storeLocation;

    @JsonProperty("order_date")
    private LocalDate orderDate;

    private String length;
    private Map<String, HashSet<String>> selections;
    private Map<String, HashSet<String>> extras;
}
