package trip.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trip.app.entity.TourSpot;

public interface TourSpotRepository extends JpaRepository<TourSpot, Long> {
    boolean existsByName(String name);
}
