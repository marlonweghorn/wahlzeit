package org.wahlzeit.model;

import org.wahlzeit.services.SysLog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoldCoinPhotoFactory extends PhotoFactory {

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    private static GoldCoinPhotoFactory instance = null;

    private GoldCoinPhotoFactory() {
        /* intentionally left blank */
    }

    /**
     * Public singleton access method.
     */
    public static synchronized GoldCoinPhotoFactory getInstance() {
        if (instance == null) {
            SysLog.logSysInfo("setting concrete GoldCoinPhotoFactory");
            setInstance(new GoldCoinPhotoFactory());
        }

        return instance;
    }

    /**
     * Method to set the singleton instance of PhotoFactory.
     */
    protected static synchronized void setInstance(GoldCoinPhotoFactory goldCoinPhotoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize GoldCoinPhotoFactory twice");
        }

        instance = goldCoinPhotoFactory;
    }

    /**
     * Hidden singleton instance; needs to be initialized from the outside.
     */
    public static void initialize() {
        getInstance(); // drops result due to getInstance() side-effects
    }

    /**
     * @methodtype factory
     */
    @Override
    public GoldCoinPhoto createPhoto() {
        return new GoldCoinPhoto();
    }

    /**
     *
     */
    @Override
    public GoldCoinPhoto createPhoto(PhotoId id) {
        return new GoldCoinPhoto(id);
    }

    /**
     *
     */
    @Override
    public GoldCoinPhoto createPhoto(ResultSet rs) throws SQLException {
        return new GoldCoinPhoto(rs);
    }
}
