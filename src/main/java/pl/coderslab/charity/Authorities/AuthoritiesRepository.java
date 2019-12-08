package pl.coderslab.charity.Authorities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {

    Set<Authorities> findAllByUserUsername(String username);
    List<Authorities> findAllByAuthority(String authority);
}
