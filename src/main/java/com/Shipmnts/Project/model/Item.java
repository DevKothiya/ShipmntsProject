package com.Shipmnts.Project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"plan"})

public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    private Plan plan;
    private String category;
    @NotBlank
    private String name;

    @JsonProperty("half_price")
    private Long halfPrice;

    @JsonProperty("full_price")
    private Long fullPrice;

    @JsonProperty("extra_charge")
    private Long extraCharge;

}
