package online.palworldkorea.palworldkorea_online.admin.integrated_link.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.integrated_link.dto.IntegratedLinkDto;
import online.palworldkorea.palworldkorea_online.admin.integrated_link.entity.IntegratedLink;
import online.palworldkorea.palworldkorea_online.admin.integrated_link.mapper.IntegratedLinkMapper;
import online.palworldkorea.palworldkorea_online.admin.integrated_link.repository.IntegratedLinkRepository;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidIntegratedLinkIdException;
import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IntegratedLinkService {
    private final MemberService memberService;
    private final IntegratedLinkRepository integratedLinkRepository;
    private final IntegratedLinkMapper integratedLinkMapper;

    public List<IntegratedLinkDto.Response> getIntegratedLinks() {
        memberService.validateIsAdmin();

        return integratedLinkRepository.findAll().stream()
                .map(integratedLinkMapper::toResponse)
                .toList();
    }

    public IntegratedLinkDto.Response updateIntegratedLink(IntegratedLinkDto.Request integratedLinkRequestDto) {
        memberService.validateIsAdmin();

        IntegratedLink integratedLink = getIntegratedLinkEntity(integratedLinkRequestDto.getId());

        integratedLink.update(integratedLinkRequestDto.getTitle(), integratedLinkRequestDto.getUrl());

        integratedLinkRepository.save(integratedLink);

        return integratedLinkMapper.toResponse(integratedLink);
    }

    public boolean deleteIntegratedLink(Long id) {
        memberService.validateIsAdmin();

        integratedLinkRepository.deleteById(id);

        return true;
    }

    private IntegratedLink getIntegratedLinkEntity(Long id) {
        if (id == null)
            return new IntegratedLink();
        else
            return integratedLinkRepository.findById(id)
                    .orElseThrow(InvalidIntegratedLinkIdException::new);
    }
}
