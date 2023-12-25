package ru.training.mc.archive.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.training.mc.archive.store.entities.ServerInfoEntity;

@Repository
public interface ServerInfoRepository extends JpaRepository<ServerInfoEntity, Long> {

}
