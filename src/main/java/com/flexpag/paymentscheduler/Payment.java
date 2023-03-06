package com.flexpag.paymentscheduler;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String status;
    Float valor;
    @JsonFormat(pattern = "dd-mm-yyyy")
    Date data;
    @JsonFormat(pattern="HH:mm")
    Date hora;

}
