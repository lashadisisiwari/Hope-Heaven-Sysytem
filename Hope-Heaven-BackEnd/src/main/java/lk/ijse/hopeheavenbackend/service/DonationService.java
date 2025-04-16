package lk.ijse.hopeheavenbackend.service;

import lk.ijse.hopeheavenbackend.dto.DonationDTO;


import java.util.List;

public interface DonationService {
    int addDonation(DonationDTO donationDTO);
    int deleteDonation(String id);
    int updateDonation(DonationDTO donationDTO);
    List<DonationDTO> getAllDonations();
    String generateNextDonationId();
}
