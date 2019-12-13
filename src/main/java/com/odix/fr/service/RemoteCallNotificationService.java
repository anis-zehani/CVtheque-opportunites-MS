package com.odix.fr.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="backend")
public interface RemoteCallNotificationService {

}
