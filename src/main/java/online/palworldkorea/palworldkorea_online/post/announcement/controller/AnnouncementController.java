package online.palworldkorea.palworldkorea_online.post.announcement.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.post.announcement.service.AnnouncementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcement")
@RequiredArgsConstructor
public class AnnouncementController {
    private final AnnouncementService announcementService;


}
