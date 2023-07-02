package org.hary.agustian.repository;

import org.hary.agustian.entity.BorrowerDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowerDetailRepository extends JpaRepository<BorrowerDetail, Integer> {
    Optional<BorrowerDetail> findByQuantity(Integer quantity);
    Page<BorrowerDetail> findByQuantityContainingIgnoreCase(Integer quantity, Pageable pageable);
}
