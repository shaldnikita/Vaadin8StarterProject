package ru.shaldnikita.Tags.backend.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.shaldnikita.Tags.backend.model.Reg;

import java.time.LocalDate;
import java.util.Collection;

public interface RegRepository extends JpaRepository<Reg,Long> {

    Collection<Reg> findAllByDateAfter(LocalDate localDate);

    Reg findOneByDateEquals(LocalDate localDate);

    @Override
    @EntityGraph(value = "Reg.time", type = EntityGraph.EntityGraphType.LOAD)
    Reg findOne(Long aLong);
}
