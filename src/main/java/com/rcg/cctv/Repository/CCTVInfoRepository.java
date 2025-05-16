package com.rcg.cctv.Repository;

import com.rcg.cctv.Entity.CCTVInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CCTVInfoRepository extends JpaRepository<CCTVInfo, Long> {
}
