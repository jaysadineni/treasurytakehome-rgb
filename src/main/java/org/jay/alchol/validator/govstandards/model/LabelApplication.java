package org.jay.alchol.validator.govstandards.model;

import lombok.Data;

@Data
public class LabelApplication {
    private String brand;
    private String abv;
    private String netContents;
    private String classType;
    private String producer;
    private String address;
    private String originCountry;
    private String governmentWarning;
}
