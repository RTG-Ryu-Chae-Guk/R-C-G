package com.rcg.citydata.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityDynamicId implements Serializable {

  @Column(name = "area_cd", length = 20)
  private String areaCd;

  @Column(name = "area_nm", length = 100)
  private String areaNm;

  @Column(name = "measured_at")
  private LocalDateTime measuredAt;
}
