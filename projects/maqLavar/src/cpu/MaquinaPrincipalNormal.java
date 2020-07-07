/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpu;

/**
 *
 * @author PedroHenrique
 */
public enum MaquinaPrincipalNormal {

    TRAV(0, 2),
    ENCH(1, Boolean.TRUE),
    AG_ML(2, 3000),
    SB(3, 2),
    DREN_1(4, Boolean.FALSE),
    DREN_2(5, 60),
    R9_LIGA(6, 10),
    R9_DESLIGA(7, 20),
    R1(8, 63),
    R4_LIGA(9, 30),
    R4_DESLIGA(10, 100),
    TRAV2(11, 2),
    ENCH2(12, Boolean.TRUE),
    AG(13, 120),
    AM(14, 15),
    AG2(15, 120),
    SB_2(16, 2),
    DREN2_1(17, Boolean.FALSE),
    DREN2_2(18, 160),
    R9_2LIGA(19, 10),
    R9_2DESLIGA(20, 20),
    R3_LIGA(21, 20),
    R3_DESLIGA(22, 60),
    CT(23, 720),
    R5_DESLIGA(24, 100),
    R5_LIGA(25, 5),
    PAUSE();

    private int id;
    private Long timeToFinish;
    private Boolean pressostato;

    private MaquinaPrincipalNormal() {

    }

    private MaquinaPrincipalNormal(int id, boolean pressostato) {
        this.id = id;
        this.pressostato = pressostato;
    }

    private MaquinaPrincipalNormal(int id, long timeToFinish) {
        this.id = id;
        this.timeToFinish = timeToFinish;
    }

    public MaquinaPrincipalNormal getState(int id) {
        for (MaquinaPrincipalNormal e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

    public MaquinaPrincipalNormal getNextState(MaquinaPrincipalNormal currentState, long time, Boolean pressostato, boolean portaAberta) {
        if (currentState.pressostato != null) {
            if (currentState.pressostato.equals(pressostato)) {
                currentState = this.getState(currentState.id + 1);
            }
        }

        if (currentState.timeToFinish != null) {
            if (currentState.timeToFinish.equals(time)) {
                currentState = this.getState(currentState.id + 1);
            }
        }

        return currentState;
    }

    public Long getTimeToFinish() {
        return timeToFinish;
    }

    public Boolean getPressostato() {
        return pressostato;
    }

}
