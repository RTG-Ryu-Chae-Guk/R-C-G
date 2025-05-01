package com.rcg.citydata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 실시간(동적) 데이터를 저장하는 엔티티
 */
@Entity
@Table(name = "city_dynamic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDynamic {

  @EmbeddedId
  private CityDynamicId id;

  /**
   * 동적으로 변하는 나머지 데이터(JSON 문자열)
   * - loader에서 정적 필드를 제거한 나머지 전체를 이 컬럼에 저장
   */
  @Column(name = "dynamic_data", columnDefinition = "JSON")
  private String dynamicData;
}
