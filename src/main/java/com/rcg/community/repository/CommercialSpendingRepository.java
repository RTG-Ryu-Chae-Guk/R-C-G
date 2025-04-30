package com.rcg.community.repository;

import com.rcg.community.entity.CommercialSpending;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommercialSpendingRepository extends JpaRepository<CommercialSpending, Long> {
}