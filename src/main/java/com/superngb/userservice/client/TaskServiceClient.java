package com.superngb.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "task-service", url = "${feign_client.task_service.url}")
public interface TaskServiceClient {
    @PutMapping("/removeUser/{id}")
    ResponseEntity<?> removeUserFromTasks(@PathVariable Long id);
}
