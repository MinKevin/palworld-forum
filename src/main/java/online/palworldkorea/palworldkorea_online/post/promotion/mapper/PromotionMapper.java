package online.palworldkorea.palworldkorea_online.post.promotion.mapper;

import online.palworldkorea.palworldkorea_online.post.common.mapper.CommonPostMapper;
import online.palworldkorea.palworldkorea_online.post.promotion.dto.PromotionDto;
import online.palworldkorea.palworldkorea_online.post.promotion.entity.Promotion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config= CommonPostMapper.class)
public interface PromotionMapper extends CommonPostMapper<Promotion, PromotionDto.Request, PromotionDto.Response> {
}
