package com.revature.pokemondemo;

import com.revature.pokemondemo.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TrainerController {
    @Autowired
    private TrainerController trainerController;

    @MockBean
    private TrainerService trainerServ;

    public void Add_Trainer_Should_Add_A_Trainer()
    {
        
    }
}
