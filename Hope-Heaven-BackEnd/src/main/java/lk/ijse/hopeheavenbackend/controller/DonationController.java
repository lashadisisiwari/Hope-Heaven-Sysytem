package lk.ijse.hopeheavenbackend.controller;

import jakarta.validation.Valid;
import lk.ijse.hopeheavenbackend.dto.DonationDTO;
import lk.ijse.hopeheavenbackend.dto.RequestDTO;
import lk.ijse.hopeheavenbackend.dto.ResponseDTO;
import lk.ijse.hopeheavenbackend.service.DonationService;
import lk.ijse.hopeheavenbackend.utill.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/donation")
@CrossOrigin(origins = "*")
public class DonationController {
    @Autowired
    private DonationService donationService;

    @PostMapping(value = "saveDonation")
    public ResponseEntity<ResponseDTO> saveRequest(@Valid @RequestBody DonationDTO donationDTO) {
        int response = donationService.addDonation(donationDTO);

        if (response == VarList.Created) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(VarList.Created, "Donation saved  Successfully", null));
        } else if (response == VarList.Not_Acceptable) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ResponseDTO(VarList.Not_Acceptable, "Already expire Donation", null));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(VarList.Internal_Server_Error, "Something went wrong", null));
        }
    }

    @GetMapping(value = "getNextDonationId")
    public ResponseEntity<ResponseDTO> getNextDonationId() {
        String nextDonationId = donationService.generateNextDonationId();
        if (nextDonationId != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(VarList.Created, "New DonationId Successfully Generated", nextDonationId));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(VarList.Internal_Server_Error, "Something went wrong", null));
        }
    }

    @GetMapping(value = "getAllDonation")
    public ResponseEntity<ResponseDTO> getAllDonation() {
        if (donationService.getAllDonations() != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(VarList.Created, "All Request Successfully Fetched", donationService.getAllDonations()));
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDTO(VarList.Internal_Server_Error, "Something went wrong", null));
        }
    }

    @PutMapping(value = "updateDonation")
    public ResponseEntity<ResponseDTO> updateDonation(@Valid @RequestBody DonationDTO donationDTO){
        int response = donationService.updateDonation(donationDTO);
        if(response== VarList.OK){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK, "Donation Successfully Updated",null));
        } else if (response==VarList.Not_Found){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(VarList.Not_Found, "Donation Not Found",null));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseDTO(VarList.Bad_Gateway, "Something went wrong",null));
        }
    }

    @DeleteMapping
    public ResponseEntity<ResponseDTO> deleteDonation(@Valid @RequestBody DonationDTO donationDTO){
        int response = donationService.deleteDonation(donationDTO.getDonationId());
        if(response==VarList.OK){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(VarList.OK,"Donation Delete Successfully",null));
        } else if (response==VarList.Not_Found) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(VarList.Not_Found, "Donation Not Found",null));
        }else {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseDTO(VarList.Bad_Gateway, "Something went wrong",null));
        }
    }


}

