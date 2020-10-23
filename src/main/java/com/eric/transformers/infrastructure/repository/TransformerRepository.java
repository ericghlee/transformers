package com.eric.transformers.infrastructure.repository;

import com.eric.transformers.domain.Transformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerRepository extends JpaRepository<Transformer, String> {

}
