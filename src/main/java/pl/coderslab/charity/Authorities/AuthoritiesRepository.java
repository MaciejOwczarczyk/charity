package pl.coderslab.charity.Authorities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {

    Set<Authorities> findAllByUserUsername(String username);
}
