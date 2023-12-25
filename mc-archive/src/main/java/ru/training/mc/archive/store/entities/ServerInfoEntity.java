package ru.training.mc.archive.store.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerInfoEntity {

    @Id
    private Long id;

    private String location;

    private String description;
}
