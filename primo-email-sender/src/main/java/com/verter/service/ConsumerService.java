package com.verter.service;

import com.verter.dto.PurchaseDTO;

public interface ConsumerService {
    void receivedMessage(PurchaseDTO dto);
}
