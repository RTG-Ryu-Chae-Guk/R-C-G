package com.rcg.commercialData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommercialAreaDetailDto {
    private CommercialAreaDto area;

    private CommercialAreaFloatingPopulationDto floatingPopulation;
    private List<CommercialAreaSalesDto> salesList;
    private List<CommercialAreaStoreStatusDto> storeStatusList;
    private CommercialResidentPopulationDto residentPopulation;
    private CommercialSpendingDto spending;
}

