package com.hieult.foodhub.data;

public class StateManager {
    private static StateManager instance;
    private boolean isFavorite = false;

    private StateManager() {}

    public static StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
        }
        return instance;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
