package com.Shipmnts.Project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "plans")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {

    @Id
    private Long id;

    private String store_location;

    private Date valid_from;

    private Date valid_to;
    @OneToMany()
    @JoinColumn(name = "item_id")
    private List<Item> items;



}
