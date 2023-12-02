package com.superngb.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "board-service", url = "${feign_client.board_service.url}")
public interface BoardServiceClient {
    @PutMapping("/removeUser/{id}")
    void removeUserFromBoards(@PathVariable Long id);
}
