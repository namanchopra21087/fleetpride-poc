package com.ps.fleetpride.dto;

import com.ps.fleetpride.intfc.Cacheable;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class INMOMSPRDDto implements Serializable, Cacheable {
    private Long id;
    private String prdPart;
}
