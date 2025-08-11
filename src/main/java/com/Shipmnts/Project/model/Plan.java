package com.Shipmnts.Project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("store_location")
    private String storeLocation;

    @JsonProperty("valid_from")
    private LocalDate validFrom;

    @JsonProperty("valid_to")
    private LocalDate validTo;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Item> items;



}
