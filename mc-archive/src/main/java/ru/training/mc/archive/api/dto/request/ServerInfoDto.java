package ru.training.mc.archive.api.dto.request;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfoDto {

    @Id
    private Long id;

    private String location;

    private String description;

}
