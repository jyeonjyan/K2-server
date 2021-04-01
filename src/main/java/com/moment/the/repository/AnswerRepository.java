package com.moment.the.repository;

import com.moment.the.domain.AdminDomain;
import com.moment.the.domain.AnswerDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerDomain, Long> {
    List<AnswerDomain> findAllBy();
    void deleteAllByAnswerIdx(Long answerIdx);

    Optional<AnswerDomain> findByAdminDomain(AdminDomain adminDomain);
}