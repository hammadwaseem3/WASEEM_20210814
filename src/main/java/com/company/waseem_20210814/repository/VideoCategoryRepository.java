package com.company.waseem_20210814.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.company.waseem_20210814.entity.VideoCategory;

@Repository
public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Integer> {
}
