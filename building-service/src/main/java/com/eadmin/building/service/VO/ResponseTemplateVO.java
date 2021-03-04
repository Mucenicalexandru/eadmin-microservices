package com.eadmin.building.service.VO;

import com.eadmin.building.service.model.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTemplateVO {

    private President president;
    private Building building;
}
