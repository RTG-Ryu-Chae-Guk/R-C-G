package com.rcg.commercialData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialAreaDetailDto {
    private CommercialAreaDto area;

    private CommercialAreaFloatingPopulationDto floatingPopulation;
    private CommercialAreaSalesDto sales;
    private CommercialAreaStoreStatusDto storeStatus;
    private CommercialResidentPopulationDto residentPopulation;
    private CommercialSpendingDto spending;
}

