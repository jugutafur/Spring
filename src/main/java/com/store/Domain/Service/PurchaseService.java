package com.store.Domain.Service;

import com.store.Domain.Purchase;
import com.store.Domain.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Purchase> getAll(){
        return purchaseRepository.getAll();
    }

    public Optional<List<Purchase>> getByClient(String ClientId){
        return purchaseRepository.getByClient(ClientId);
    }

    public Purchase save(Purchase purchase){
        return  purchaseRepository.save(purchase);
    }

}
