package trip.app;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import trip.app.services.TourSpotService;

//@Component
@RequiredArgsConstructor
public class DataInitailizer implements CommandLineRunner {
    private final TourSpotService tourSpotService;

    @Override
    public void run(String... args) throws Exception{
        tourSpotService.fetchAndSaveTourSpots();
    }
}
