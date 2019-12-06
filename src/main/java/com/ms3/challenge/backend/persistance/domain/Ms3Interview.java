package com.ms3.challenge.backend.persistance.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @author Matthew Puentes on 12/5/19
 */

@Entity
@Data
public class Ms3Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private double G;
    private boolean H;
    private boolean I;
    private String J;
}
