package online.palworldkorea.palworldkorea_online.admin.coalition.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.coalition.dto.CoalitionDto;
import online.palworldkorea.palworldkorea_online.admin.coalition.entity.Coalition;
import online.palworldkorea.palworldkorea_online.admin.coalition.mapper.CoalitionMapper;
import online.palworldkorea.palworldkorea_online.admin.coalition.repository.CoalitionRepository;
import online.palworldkorea.palworldkorea_online.admin.integrated_link.dto.IntegratedLinkDto;
import online.palworldkorea.palworldkorea_online.admin.integrated_link.entity.IntegratedLink;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidIntegratedLinkIdException;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CoalitionService {
    private final MemberService memberService;
    private final AttachmentService attachmentService;
    private final CoalitionRepository coalitionRepository;
    private final CoalitionMapper coalitionMapper;

    public List<CoalitionDto.Response> getCoalitions() {
        return coalitionRepository.findAll().stream()
                .map(coalitionMapper::toResponse)
                .toList();
    }

    public CoalitionDto.Response updateCoalition(CoalitionDto.Request coalitionRequestDto) {
        memberService.validateIsAdmin();

        Coalition coalition = getCoalitionEntity(coalitionRequestDto.getId());

        Attachment attachment = attachmentService.saveAttachment(coalitionRequestDto.getAttachment());

        coalition.update(coalitionRequestDto.getName(), attachment);

        coalitionRepository.save(coalition);

        return coalitionMapper.toResponse(coalition);
    }

    public boolean deleteCoalition(Long id) {
        memberService.validateIsAdmin();

        coalitionRepository.deleteById(id);

        return true;
    }

    private Coalition getCoalitionEntity(Long id) {
        if (id == null)
            return new Coalition();
        else
            return coalitionRepository.findById(id)
                    .orElseThrow(InvalidIntegratedLinkIdException::new);
    }
}
