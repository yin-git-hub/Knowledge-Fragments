package com.atguigu.design.behavioral.state;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 状态切换
 */
public class MainTest {

    public static void main(String[] args) {

        LockSupport.park();
        SKTTeam sktTeam = new SKTTeam();
        TeamState state = new VocationState();
        sktTeam.setTeamState(state);
        sktTeam.startGame();


//        sktTeam.startGame();
//
//        sktTeam.nextState();
//
//
//        sktTeam.startGame();
//
//        sktTeam.nextState();
//        sktTeam.startGame();


        state = state.next();
        sktTeam.setTeamState(state);
        sktTeam.startGame();



        //状态需要维护自己的切换逻辑
        state = state.next();
        sktTeam.setTeamState(state);
        sktTeam.startGame();
    }
}
