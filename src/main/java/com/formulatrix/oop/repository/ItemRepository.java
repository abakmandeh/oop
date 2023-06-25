package com.formulatrix.oop.repository;

import com.formulatrix.oop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemName(String itemName);

}
