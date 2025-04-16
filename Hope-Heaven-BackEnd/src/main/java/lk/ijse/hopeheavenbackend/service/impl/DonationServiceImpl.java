package lk.ijse.hopeheavenbackend.service.impl;

import lk.ijse.hopeheavenbackend.dto.DonationDTO;
import lk.ijse.hopeheavenbackend.dto.RequestDTO;
import lk.ijse.hopeheavenbackend.entity.Donation;
import lk.ijse.hopeheavenbackend.entity.Request;
import lk.ijse.hopeheavenbackend.repository.DonationRepo;
import lk.ijse.hopeheavenbackend.repository.RequestRepo;
import lk.ijse.hopeheavenbackend.service.DonationService;
import lk.ijse.hopeheavenbackend.service.RequestService;
import lk.ijse.hopeheavenbackend.utill.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepo donationRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public int addDonation(DonationDTO donationDTO) {
        if (donationRepo.existsById(donationDTO.getDonationId())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            donationDTO.setDonationId(encoder.encode(donationDTO.getDonationId()));
            donationRepo.save(modelMapper.map(donationDTO, Donation.class));
            return VarList.Created;
        }
    }

    @Override
    public int deleteDonation(String id) {
        if (donationRepo.existsById(id)) {
            donationRepo.deleteById(id);
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateDonation(DonationDTO donationDTO) {
        if (donationRepo.existsById(donationDTO.getDonationId())) {
            donationRepo.save(modelMapper.map(donationDTO, Donation.class));
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public List<DonationDTO> getAllDonations() {
        if (donationRepo.findAll().isEmpty()) {
            return null;
        } else {
            return modelMapper.map(donationRepo.findAll(), new TypeToken<List<DonationDTO>>() {
            }.getType());
        }
    }

    @Override
    public String generateNextDonationId() {
        long count = donationRepo.count() + 1;
        return String.format("DID-%04d", count);
    }
}
