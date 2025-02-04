package online.palworldkorea.palworldkorea_online.admin.link.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.link.dto.LinkDto;
import online.palworldkorea.palworldkorea_online.admin.link.entity.Link;
import online.palworldkorea.palworldkorea_online.admin.link.mapper.LinkMapper;
import online.palworldkorea.palworldkorea_online.admin.link.repository.LinkRepository;
import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LinkService {
    private final MemberService memberService;
    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;

    public List<LinkDto.Response> getLinks() {
        return linkRepository.findAll().stream()
                .map(linkMapper::toResponse)
                .toList();
    }

    public LinkDto.Response updateLink(LinkDto.Request linkRequestDto) {
        memberService.validateIsAdmin();

        Link link = getLinkEntity(linkRequestDto);

        link.update(linkRequestDto.getUrl());
        linkRepository.save(link);

        return linkMapper.toResponse(link);
    }

    private Link getLinkEntity(LinkDto.Request linkRequestDto) {
        return linkRepository.findByLinkType(linkRequestDto.getLinkType())
                .orElse(new Link(linkRequestDto.getLinkType()));
    }
}
