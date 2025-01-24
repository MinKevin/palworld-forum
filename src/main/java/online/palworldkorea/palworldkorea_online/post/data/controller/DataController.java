package online.palworldkorea.palworldkorea_online.post.data.controller;

import online.palworldkorea.palworldkorea_online.post.common.controller.CommonPostController;
import online.palworldkorea.palworldkorea_online.post.data.dto.DataDto;
import online.palworldkorea.palworldkorea_online.post.data.entity.Data;
import online.palworldkorea.palworldkorea_online.post.data.mapper.DataMapper;
import online.palworldkorea.palworldkorea_online.post.data.service.DataService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/datas")
public class DataController extends CommonPostController<
        Data,
        DataDto.Request,
        DataDto.Response,
        DataMapper> {
    public DataController(DataService dataService) {
        super(dataService);
    }
}
