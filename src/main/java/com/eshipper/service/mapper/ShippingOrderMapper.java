package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.ShippingOrderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ShippingOrder} and its DTO {@link ShippingOrderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ShippingOrderMapper extends EntityMapper<ShippingOrderDTO, ShippingOrder> {


    @Mapping(target = "soDocs", ignore = true)
    @Mapping(target = "removeSoDocs", ignore = true)
    ShippingOrder toEntity(ShippingOrderDTO shippingOrderDTO);

    default ShippingOrder fromId(Long id) {
        if (id == null) {
            return null;
        }
        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setId(id);
        return shippingOrder;
    }
}
