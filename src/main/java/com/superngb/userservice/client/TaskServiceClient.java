package com.superngb.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "task-service", url = "${feign_client.task_service.url}")
public interface TaskServiceClient {
    @PatchMapping("/removeUser/{id}")
    void removeUserFromTasks(@PathVariable Long id);
}
