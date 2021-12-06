package com.swp.dataweb.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
@ToString
public class ExcelExporter implements Serializable {
    private Date endDate;
    private Date startDate;
//    private filter[] filters;
    private List<Long> formIds;
    private Long subjectId;
    private String subjectName;
//    @Data
//    @ToString
//    public static class filter{
//        String filter;
//        public filter(){}
//        public String getFilter() {
//            return filter;
//        }
//        public String setFilter(String filter) {
//            this.filter = filter;
//            return this.filter;
//        }
//    }
}
