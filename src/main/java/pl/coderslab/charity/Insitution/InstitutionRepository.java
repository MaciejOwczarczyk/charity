package pl.coderslab.charity.Insitution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InstitutionRepository extends JpaRepository<Institution, Long> {

    @Query(value = "select count(*) from `charity-donation`.institutions", nativeQuery = true)
    Long countAll();

    Institution findAllById(Long id);
}
