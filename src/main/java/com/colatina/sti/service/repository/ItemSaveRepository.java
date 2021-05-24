package com.colatina.sti.service.repository;

import com.colatina.sti.service.domain.ItemSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemSaveRepository extends JpaRepository<ItemSave, Long> {
}
