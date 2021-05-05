package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.SituationOffer;
import com.colatina.sti.service.service.dto.SelectDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SituationOfferMapper extends EntityMapper<SituationOffer, SelectDTO> {
}
