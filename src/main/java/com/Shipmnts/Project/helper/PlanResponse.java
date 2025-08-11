package com.Shipmnts.Project.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponse {

    private Long plan_id;
    private String store_location;
    private boolean success;
    private String message ;
}
