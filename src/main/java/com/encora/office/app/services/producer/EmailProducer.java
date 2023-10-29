package com.encora.office.app.services.producer;

import java.util.Map;

public interface EmailProducer {

    void sendEmail(String subject, Map<String, String> payload);
}
