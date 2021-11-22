package com.swp.dataweb.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TDataModel implements Serializable {
    @JsonProperty("subject_form")
    private long[] subjectForm;
    @JsonProperty("tData")
    private Object tData;

}
