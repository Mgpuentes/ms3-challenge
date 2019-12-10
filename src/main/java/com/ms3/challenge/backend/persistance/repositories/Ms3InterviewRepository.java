package com.ms3.challenge.backend.persistance.repositories;

import com.ms3.challenge.backend.persistance.domain.Ms3Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Matthew Puentes on 12/5/19
 */

@Repository
public interface Ms3InterviewRepository extends JpaRepository<Ms3Interview, Long> {
}
