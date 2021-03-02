package com.eadmin.group.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="groups", catalog = "groups")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupId;

    private String officialName;
    private String shortName;

    private String street;
    private String number;
    private String town;
    private String country;

    private String email;
    private String IBAN;

    private Long administratorId;
    private Long censorId;

    private String picture;
}
