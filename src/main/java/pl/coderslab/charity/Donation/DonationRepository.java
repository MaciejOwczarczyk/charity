package pl.coderslab.charity.Donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "select sum(`charity`.donations.quantity) from `charity`.donations", nativeQuery = true)
    Long sumQuantity();

    Donation findAllById(Long id);
}
