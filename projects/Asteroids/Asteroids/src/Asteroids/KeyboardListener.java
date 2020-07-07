package Asteroids;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39: //RIGHT KEY
                Space.getInstance().turnStationCW();
                break;
//            case 68: //D KEY
//                Space.getInstance().turnStationCW();
//                break;
            case 37: //LEFT KEY
                Space.getInstance().turnStationCCW();
                break;
//            case 65://A KEY
//                Space.getInstance().turnStationCCW();
//                break;
            case 38://UP KEY
                Space.getInstance().acceleratingStation(+1);
                break;
//            case 87://W KEY
//                Space.getInstance().acceleratingStation(+1);
//                break;
            case 40://DOWN KEY
                Space.getInstance().acceleratingStation(-1);
                break;
//            case 83://S KEY
//                Space.getInstance().acceleratingStation(-1);
//                break;
            case 88://X KEY
                System.exit(0);
                break;
            case 82://R KEY
                Space.getInstance().createStation(Space.getInstance().hasStation);
                Space.getInstance().getStation().initializeStation();
                Space.getInstance().createEnemy(Space.getInstance().hasEnemy);
                Space.getInstance().getEnemy().initializeEnemy();
                break;
                case 32://SPACE KEY
               Space.getInstance().getStation().shoot();                       
                break;
//            case 81://Q KEY
//                break; AINDA NAO IMPLEMENTADO

        }
        //System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 39:
                Space.getInstance().stopTurningStationCW();
                break;
            case 37:
                Space.getInstance().stopTurningStationCCW();
                break;
            case 38:
                Space.getInstance().acceleratingStation(0);
                break;
            case 40:
                Space.getInstance().acceleratingStation(0);
                break;
                
        }
    }

}
