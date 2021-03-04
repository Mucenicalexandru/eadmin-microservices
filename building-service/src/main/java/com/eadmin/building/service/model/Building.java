package com.eadmin.building.service.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="buildings", catalog = "buildings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long buildingId;

    private Long groupId;

    private String street;
    private String number;
    private String town;
    private String country;
    private String buildingName;
    private String entrance;

    private Long noticeBoardId;

}
