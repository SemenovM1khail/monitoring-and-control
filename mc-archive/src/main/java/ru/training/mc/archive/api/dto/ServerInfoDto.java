package ru.training.mc.archive.api.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServerInfoDto {

    @NonNull
    private Long id;

    @NonNull
    private String location;

    @NonNull
    private String description;
}
