package edu.karazin.shop.repository;

import edu.karazin.shop.model.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository  extends JpaRepository<PurchaseItem, Long> {

}
