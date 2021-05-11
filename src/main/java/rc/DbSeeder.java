package rc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {
    private HotelRepository hotelRepository;



    public DbSeeder(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }
    @Override
    public void run(String... strings) throws Exception {
        Hotel marriot =new Hotel(
                "Marriot",
                130,
                new Address("Paris","France"),
                Arrays.asList(
                        new Reviews("John", 8, false),
                        new Reviews("Mary", 7, true)

                )
        );
        Hotel ibis =new Hotel(
                "Ibis",
                90,
                new Address("Bucharest","Romania"),
                Arrays.asList(
                        new Reviews("Teddy", 9, false)

                )
        );
        Hotel sofitel =new Hotel(
                "Sofitel",
                200,
                new Address("Rome","Italy"),
                new ArrayList<>()
        );

        // drop all hotels
        this.hotelRepository.deleteAll();

        //add our hotels to database
        List<Hotel> hotels = Arrays.asList(marriot,ibis,sofitel);
        this.hotelRepository.saveAll(hotels);

    }
}
