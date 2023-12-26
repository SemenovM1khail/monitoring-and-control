package ru.training.mc.archive.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfoRequestDto {

    private Long id;

    private String location;

    private String description;

}
