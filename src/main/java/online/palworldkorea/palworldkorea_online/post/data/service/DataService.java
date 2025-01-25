package online.palworldkorea.palworldkorea_online.post.data.service;

import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService;
import online.palworldkorea.palworldkorea_online.post.data.dto.DataDto;
import online.palworldkorea.palworldkorea_online.post.data.entity.Data;
import online.palworldkorea.palworldkorea_online.post.data.mapper.DataMapper;
import online.palworldkorea.palworldkorea_online.post.data.repository.DataRepository;
import org.springframework.stereotype.Service;

@Service
public class DataService extends CommonPostService<
        Data,
        DataDto.Request,
        DataDto.Response,
        DataMapper> {
    public DataService(MemberService memberService,
                       AttachmentService attachmentService,
                       DataRepository dataRepository,
                       DataMapper dataMapper) {
        super(memberService, attachmentService, dataRepository, dataMapper, Data.class);
    }
}