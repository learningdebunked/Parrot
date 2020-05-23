package com.learningdebunked.mock.repository;

import com.learningdebunked.mock.model.Templates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kapil
 * @created 22/05/2020 - 3:35 PM
 * @project mock
 */
@Repository
public interface TemplateRepository extends JpaRepository<Templates, Long> {
}
