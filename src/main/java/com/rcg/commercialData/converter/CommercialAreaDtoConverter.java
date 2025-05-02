package com.rcg.commercialData.converter;

import com.rcg.commercialData.dto.*;
import com.rcg.commercialData.entity.*;
import org.springframework.stereotype.Component;

@Component
public class CommercialAreaDtoConverter {

    public CommercialAreaDto toDto(CommercialArea area) {
        if (area == null) return null;

        return CommercialAreaDto.builder()
                .trdarCd(area.getTrdarCd())
                .trdarCdNm(area.getTrdarCdNm())
                .adstrdCdNm(area.getAdstrdCdNm())
                .signguCdNm(area.getSignguCdNm())
                .relmAr(area.getRelmAr())
                .latitude(area.getLatitude())     // ✅ 위도 (WGS84)
                .longitude(area.getLongitude())   // ✅ 경도 (WGS84)
                .build();
    }


    public CommercialAreaFloatingPopulationDto toDto(CommercialAreaFloatingPopulation entity) {
        if (entity == null) return null;
        return CommercialAreaFloatingPopulationDto.builder()
                .trdarCd(entity.getTrdarCd())
                .trdarCdNm(entity.getTrdarCdNm())
                .stdrYyquCd(entity.getStdrYyquCd())
                .totFlpopCo(entity.getTotFlpopCo())
                .mlFlpopCo(entity.getMlFlpopCo())
                .fmlFlpopCo(entity.getFmlFlpopCo())
                .agrde10FlpopCo(entity.getAgrde10FlpopCo())
                .agrde20FlpopCo(entity.getAgrde20FlpopCo())
                .agrde30FlpopCo(entity.getAgrde30FlpopCo())
                .agrde40FlpopCo(entity.getAgrde40FlpopCo())
                .agrde50FlpopCo(entity.getAgrde50FlpopCo())
                .agrde60FlpopCo(entity.getAgrde60FlpopCo())
                .tmzon0006FlpopCo(entity.getTmzon0006FlpopCo())
                .tmzon0611FlpopCo(entity.getTmzon0611FlpopCo())
                .tmzon1114FlpopCo(entity.getTmzon1114FlpopCo())
                .tmzon1417FlpopCo(entity.getTmzon1417FlpopCo())
                .tmzon1721FlpopCo(entity.getTmzon1721FlpopCo())
                .tmzon2124FlpopCo(entity.getTmzon2124FlpopCo())
                .monFlpopCo(entity.getMonFlpopCo())
                .tuesFlpopCo(entity.getTuesFlpopCo())
                .wedFlpopCo(entity.getWedFlpopCo())
                .thurFlpopCo(entity.getThurFlpopCo())
                .friFlpopCo(entity.getFriFlpopCo())
                .satFlpopCo(entity.getSatFlpopCo())
                .sunFlpopCo(entity.getSunFlpopCo())
                .build();
    }

    public CommercialAreaSalesDto toDto(CommercialAreaSales entity) {
        if (entity == null) return null;
        return CommercialAreaSalesDto.builder()
                .trdarCd(entity.getTrdarCd())
                .trdarCdNm(entity.getTrdarCdNm())
                .stdrYyquCd(entity.getStdrYyquCd())
                .svcIndutyCd(entity.getSvcIndutyCd())
                .svcIndutyCdNm(entity.getSvcIndutyCdNm())
                .thsmonSelngAmt(entity.getThsmonSelngAmt())
                .thsmonSelngCo(entity.getThsmonSelngCo())
                .mdwkSelngAmt(entity.getMdwkSelngAmt())
                .wkendSelngAmt(entity.getWkendSelngAmt())
                .monSelngAmt(entity.getMonSelngAmt())
                .tuesSelngAmt(entity.getTuesSelngAmt())
                .wedSelngAmt(entity.getWedSelngAmt())
                .thurSelngAmt(entity.getThurSelngAmt())
                .friSelngAmt(entity.getFriSelngAmt())
                .satSelngAmt(entity.getSatSelngAmt())
                .sunSelngAmt(entity.getSunSelngAmt())
                .tmzon0006SelngAmt(entity.getTmzon0006SelngAmt())
                .tmzon0611SelngAmt(entity.getTmzon0611SelngAmt())
                .tmzon1114SelngAmt(entity.getTmzon1114SelngAmt())
                .tmzon1417SelngAmt(entity.getTmzon1417SelngAmt())
                .tmzon1721SelngAmt(entity.getTmzon1721SelngAmt())
                .tmzon2124SelngAmt(entity.getTmzon2124SelngAmt())
                .mlSelngAmt(entity.getMlSelngAmt())
                .fmlSelngAmt(entity.getFmlSelngAmt())
                .agrde10SelngAmt(entity.getAgrde10SelngAmt())
                .agrde20SelngAmt(entity.getAgrde20SelngAmt())
                .agrde30SelngAmt(entity.getAgrde30SelngAmt())
                .agrde40SelngAmt(entity.getAgrde40SelngAmt())
                .agrde50SelngAmt(entity.getAgrde50SelngAmt())
                .agrde60AboveSelngAmt(entity.getAgrde60AboveSelngAmt())
                .mdwkSelngCo(entity.getMdwkSelngCo())
                .wkendSelngCo(entity.getWkendSelngCo())
                .monSelngCo(entity.getMonSelngCo())
                .tuesSelngCo(entity.getTuesSelngCo())
                .wedSelngCo(entity.getWedSelngCo())
                .thurSelngCo(entity.getThurSelngCo())
                .friSelngCo(entity.getFriSelngCo())
                .satSelngCo(entity.getSatSelngCo())
                .sunSelngCo(entity.getSunSelngCo())
                .tmzon0006SelngCo(entity.getTmzon0006SelngCo())
                .tmzon0611SelngCo(entity.getTmzon0611SelngCo())
                .tmzon1114SelngCo(entity.getTmzon1114SelngCo())
                .tmzon1417SelngCo(entity.getTmzon1417SelngCo())
                .tmzon1721SelngCo(entity.getTmzon1721SelngCo())
                .tmzon2124SelngCo(entity.getTmzon2124SelngCo())
                .mlSelngCo(entity.getMlSelngCo())
                .fmlSelngCo(entity.getFmlSelngCo())
                .agrde10SelngCo(entity.getAgrde10SelngCo())
                .agrde20SelngCo(entity.getAgrde20SelngCo())
                .agrde30SelngCo(entity.getAgrde30SelngCo())
                .agrde40SelngCo(entity.getAgrde40SelngCo())
                .agrde50SelngCo(entity.getAgrde50SelngCo())
                .agrde60SelngCo(entity.getAgrde60SelngCo())
                .build();
    }

    public CommercialAreaStoreStatusDto toDto(CommercialAreaStoreStatus entity) {
        if (entity == null) return null;
        return CommercialAreaStoreStatusDto.builder()
                .trdarCd(entity.getTrdarCd())
                .trdarCdNm(entity.getTrdarCdNm())
                .stdrYyquCd(entity.getStdrYyquCd())
                .svcIndutyCd(entity.getSvcIndutyCd())
                .svcIndutyCdNm(entity.getSvcIndutyCdNm())
                .storeCo(entity.getStoreCo())
                .similrStoreCo(entity.getSimilrStoreCo())
                .openRt(entity.getOpenRt())
                .openStoreCo(entity.getOpenStoreCo())
                .closeRt(entity.getCloseRt())
                .closeStoreCo(entity.getCloseStoreCo())
                .frnchsStoreCo(entity.getFrnchsStoreCo())
                .build();
    }

    public CommercialResidentPopulationDto toDto(CommercialResidentPopulation entity) {
        if (entity == null) return null;
        return CommercialResidentPopulationDto.builder()
                .trdarCd(entity.getTrdarCd())
                .trdarCdNm(entity.getTrdarCdNm())
                .stdrYyquCd(entity.getStdrYyquCd())
                .totResidPopltnCnt(entity.getTotResidPopltnCnt())
                .mlResidPopltnCnt(entity.getMlResidPopltnCnt())
                .fmlResidPopltnCnt(entity.getFmlResidPopltnCnt())
                .age10ResidPopltnCnt(entity.getAge10ResidPopltnCnt())
                .age20ResidPopltnCnt(entity.getAge20ResidPopltnCnt())
                .age30ResidPopltnCnt(entity.getAge30ResidPopltnCnt())
                .age40ResidPopltnCnt(entity.getAge40ResidPopltnCnt())
                .age50ResidPopltnCnt(entity.getAge50ResidPopltnCnt())
                .age60AboveResidPopltnCnt(entity.getAge60AboveResidPopltnCnt())
                .mlAge10ResidPopltnCnt(entity.getMlAge10ResidPopltnCnt())
                .mlAge20ResidPopltnCnt(entity.getMlAge20ResidPopltnCnt())
                .mlAge30ResidPopltnCnt(entity.getMlAge30ResidPopltnCnt())
                .mlAge40ResidPopltnCnt(entity.getMlAge40ResidPopltnCnt())
                .mlAge50ResidPopltnCnt(entity.getMlAge50ResidPopltnCnt())
                .mlAge60AboveResidPopltnCnt(entity.getMlAge60AboveResidPopltnCnt())
                .fmlAge10ResidPopltnCnt(entity.getFmlAge10ResidPopltnCnt())
                .fmlAge20ResidPopltnCnt(entity.getFmlAge20ResidPopltnCnt())
                .fmlAge30ResidPopltnCnt(entity.getFmlAge30ResidPopltnCnt())
                .fmlAge40ResidPopltnCnt(entity.getFmlAge40ResidPopltnCnt())
                .fmlAge50ResidPopltnCnt(entity.getFmlAge50ResidPopltnCnt())
                .fmlAge60AboveResidPopltnCnt(entity.getFmlAge60AboveResidPopltnCnt())
                .build();
    }

    public CommercialSpendingDto toDto(CommercialSpending entity) {
        if (entity == null) return null;
        return CommercialSpendingDto.builder()
                .trdarCd(entity.getTrdarCd())
                .trdarCdNm(entity.getTrdarCdNm())
                .stdrYyquCd(entity.getStdrYyquCd())
                .totalExpendAmt(entity.getTotalExpendAmt())
                .foodExpendAmt(entity.getFoodExpendAmt())
                .clothingExpendAmt(entity.getClothingExpendAmt())
                .livingExpendAmt(entity.getLivingExpendAmt())
                .medicalExpendAmt(entity.getMedicalExpendAmt())
                .transportExpendAmt(entity.getTransportExpendAmt())
                .leisureExpendAmt(entity.getLeisureExpendAmt())
                .cultureExpendAmt(entity.getCultureExpendAmt())
                .educationExpendAmt(entity.getEducationExpendAmt())
                .entertainmentExpendAmt(entity.getEntertainmentExpendAmt())
                .build();
    }
}
