package com.achchaimae.loisiresa.Domain.user.guide;

import com.achchaimae.loisiresa.security.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuideRepository extends JpaRepository<Guide,Integer> {
    Optional<Guide> findByEmail(String email);
}
