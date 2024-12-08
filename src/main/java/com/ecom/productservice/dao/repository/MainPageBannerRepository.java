package com.ecom.productservice.dao.repository;

import com.ecom.productservice.controller.MainPageBannerModel;
import com.ecom.productservice.dao.entities.MainPageBanner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainPageBannerRepository extends JpaRepository<MainPageBanner, Long> {
    @Query("select m from MainPageBanner m where m.active = true")
    List<MainPageBanner> findAllActive();
}
