package pl.coderslab.charity.Donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query(value = "select sum(`charity-donation`.donations.quantity) from `charity-donation`.donations", nativeQuery = true)
    Long sumQuantity();
}
