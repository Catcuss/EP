
package lab.bookings.repositories;

import lab.bookings.models.Apartment;
import lab.bookings.models.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
