package Game.Interfaces;

import Collections.Lists.UnorderedListADT;
import Game.GameImpl.Game;

public interface Manual{
    void startGameManual();

    void setIsEndTrue();

    void setContinuarNoEdificio();

    void setPegouKit();

    UnorderedListADT<String> getPathDivisions();
}
