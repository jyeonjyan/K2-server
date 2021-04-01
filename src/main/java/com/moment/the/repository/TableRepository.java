package com.moment.the.repository;

import com.moment.the.domain.TableDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TableRepository extends JpaRepository<TableDomain, Long>{
    Optional<TableDomain> findByBoardIdx(Long boardIdx);
    // Goods 수 내림차순 정렬.
    List<TableDomain> findAllByOrderByGoodsDesc();
}