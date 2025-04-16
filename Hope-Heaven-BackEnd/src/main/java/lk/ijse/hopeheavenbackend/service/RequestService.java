package lk.ijse.hopeheavenbackend.service;

import lk.ijse.hopeheavenbackend.dto.RequestDTO;


import java.util.List;

public interface RequestService {
    String generateNextRequestId();
    int deleteRequests(String id);
    int updateRequests(RequestDTO requestDTO);
    List<RequestDTO> getAllRequests();
    int addRequest(RequestDTO requestDTO);
}
