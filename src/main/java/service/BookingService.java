package service;

import repository.BookingRepository;

public class BookingService {

    BookingRepository bookingRepository = new BookingRepository();

    public void createBooking(String packageId, String nameId){
        bookingRepository.createBooking(packageId, nameId);
    }

}
