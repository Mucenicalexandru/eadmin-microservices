package com.eadmin.group.VO;

import com.eadmin.group.model.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {

    private Administrator administrator;
    private Censor censor;
    private Group group;
}
