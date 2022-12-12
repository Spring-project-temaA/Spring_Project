package com.example.smp_1.service;

import com.example.smp_1.dto.userDto;
import com.example.smp_1.mapper.promiseHairMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class promiseHairServiceImpl implements promiseHairService {
    @Autowired
    private promiseHairMapper phMapper;

    @Override
    public userDto loginChk() throws Exception {
        userDto userId = phMapper.loginChk();
        return userId;
    }
}
