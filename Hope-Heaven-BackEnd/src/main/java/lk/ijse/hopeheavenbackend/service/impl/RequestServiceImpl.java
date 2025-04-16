package lk.ijse.hopeheavenbackend.service.impl;

import lk.ijse.hopeheavenbackend.dto.RequestDTO;
import lk.ijse.hopeheavenbackend.entity.Request;
import lk.ijse.hopeheavenbackend.entity.User;
import lk.ijse.hopeheavenbackend.repository.RequestRepo;
import lk.ijse.hopeheavenbackend.service.RequestService;
import lk.ijse.hopeheavenbackend.utill.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String generateNextRequestId() {
        long count = requestRepo.count() + 1;
        return String.format("RID-%04d", count);
    }

    @Override
    public int deleteRequests(String id) {
        if (requestRepo.existsById(id)) {
            requestRepo.deleteById(id);
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateRequests(RequestDTO requestDTO) {
        if (requestRepo.existsById(requestDTO.getRequestId())) {
            requestRepo.save(modelMapper.map(requestDTO, Request.class));
            return VarList.OK;
        } else {
            return VarList.Not_Found;
        }
    }

    @Override
    public List<RequestDTO> getAllRequests() {
        if (requestRepo.findAll().isEmpty()) {
            return null;
        } else {
            return modelMapper.map(requestRepo.findAll(), new TypeToken<List<RequestDTO>>() {
            }.getType());
        }
    }

    @Override
    public int addRequest(RequestDTO requestDTO) {
        if (requestRepo.existsById(requestDTO.getRequestId())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            requestDTO.setRequestId(encoder.encode(requestDTO.getRequestId()));
            requestRepo.save(modelMapper.map(requestDTO, Request.class));
            return VarList.Created;
        }
    }
}

