package com.store.Domain.Repository;

import com.store.Domain.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String ClientId);
    Purchase save(Purchase purchase);
}
