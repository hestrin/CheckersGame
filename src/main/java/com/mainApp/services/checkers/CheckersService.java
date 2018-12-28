package com.mainApp.services.checkers;

import com.model.checkers.GamePostion;
import org.springframework.stereotype.Component;

@Component
public interface CheckersService {

    String applyPostion(String Board);
}
