package com.achchaimae.loisiresa.Domain.club;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club,Integer> {
    Page<Club> findByStatus(Status status, Pageable pageable);
}
