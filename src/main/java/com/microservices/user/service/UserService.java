package com.microservices.user.service;

import com.microservices.user.VO.Department;
import com.microservices.user.VO.ResponseVO;
import com.microservices.user.entity.User;
import com.microservices.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User save(User user) {
        log.info("Inside save of Service");
        return userRepository.save(user);
    }

    public ResponseVO getUserByDepartment(Long userId) {
        log.info("Inside getUserByDepartment of Service");
        ResponseVO res = new ResponseVO();
        User user = userRepository.findByUserId(userId);
        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(), Department.class);
        res.setDepartment(department);
        res.setUser(user);
        return res;
    }
}
