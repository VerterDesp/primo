package com.verter.service;

import com.verter.dto.PurchaseDTO;

public interface ProducerService {

    void sendMessage(PurchaseDTO dto);
}
