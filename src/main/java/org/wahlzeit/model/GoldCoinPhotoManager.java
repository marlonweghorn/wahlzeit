package org.wahlzeit.model;

import org.wahlzeit.main.ServiceMain;
import org.wahlzeit.services.SysLog;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class GoldCoinPhotoManager extends PhotoManager {

    /**
     *
     */
    protected static final GoldCoinPhotoManager instance = new GoldCoinPhotoManager();

    /**
     * In-memory cache for photos
     */
    protected Map<PhotoId, GoldCoinPhoto> photoCache = new HashMap<PhotoId, GoldCoinPhoto>();

    /**
     *
     */
    public static GoldCoinPhotoManager getInstance() {
        return instance;
    }

    /**
     *
     */
    public static boolean hasPhoto(String id) {
        return hasPhoto(PhotoId.getIdFromString(id));
    }

    /**
     *
     */
    public static boolean hasPhoto(PhotoId id) {
        return getPhoto(id) != null;
    }

    /**
     *
     */
    public static GoldCoinPhoto getPhoto(String id) {
        return getPhoto(PhotoId.getIdFromString(id));
    }

    /**
     *
     */
    public static GoldCoinPhoto getPhoto(PhotoId id) {
        return instance.getPhotoFromId(id);
    }

    /**
     * @methodtype boolean-query
     * @methodproperties primitive
     */
    @Override
    protected boolean doHasPhoto(PhotoId id) {
        return photoCache.containsKey(id);
    }

    /**
     *
     */
    @Override
    public GoldCoinPhoto getPhotoFromId(PhotoId id) {
        if (id.isNullId()) {
            return null;
        }

        GoldCoinPhoto result = doGetPhotoFromId(id);

        if (result == null) {
            try {
                PreparedStatement stmt = getReadingStatement("SELECT * FROM photos WHERE id = ?");
                result = (GoldCoinPhoto) readObject(stmt, id.asInt());
            } catch (SQLException sex) {
                SysLog.logThrowable(sex);
            }
            if (result != null) {
                doAddPhoto(result);
            }
        }

        return result;
    }

    /**
     * @methodtype get
     * @methodproperties primitive
     */
    @Override
    protected GoldCoinPhoto doGetPhotoFromId(PhotoId id) {
        return photoCache.get(id);
    }

    /**
     *
     */
    @Override
    protected GoldCoinPhoto createObject(ResultSet rset) throws SQLException {
        return GoldCoinPhotoFactory.getInstance().createPhoto(rset);
    }

    /**
     * @methodtype command
     *
     * Load all persisted photos. Executed when Wahlzeit is restarted.
     */
    public void addPhoto(GoldCoinPhoto photo) {
        PhotoId id = photo.getId();
        assertIsNewPhoto(id);
        doAddPhoto(photo);

        try {
            PreparedStatement stmt = getReadingStatement("INSERT INTO photos(id) VALUES(?)");
            createObject(photo, stmt, id.asInt());
            ServiceMain.getInstance().saveGlobals();
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }
    }

    /**
     * @methodtype command
     * @methodproperties primitive
     */
    protected void doAddPhoto(GoldCoinPhoto goldCoinPhoto) {
        super.doAddPhoto(goldCoinPhoto);
    }

    /**
     *
     */
    @Override
    public void savePhoto(Photo photo) {
        try {
            PreparedStatement stmt = getUpdatingStatement("SELECT * FROM photos WHERE id = ?");
            updateObject(photo, stmt);
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }
    }

    /**
     *
     */
    @Override
    public void savePhotos() {
        try {
            PreparedStatement stmt = getUpdatingStatement("SELECT * FROM photos WHERE id = ?");
            updateObjects(photoCache.values(), stmt);
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }
    }

    /**
     * @methodtype command
     *
     * Persists all available sizes of the Photo. If one size exceeds the limit of the persistence layer, e.g. > 1MB for
     * the Datastore, it is simply not persisted.
     */
    @Override
    public Set<Photo> findPhotosByOwner(String ownerName) {
        Set<Photo> result = new HashSet<Photo>();
        try {
            PreparedStatement stmt = getReadingStatement("SELECT * FROM photos WHERE owner_name = ?");
            readObjects(result, stmt, ownerName);
        } catch (SQLException sex) {
            SysLog.logThrowable(sex);
        }

        for (Iterator<Photo> i = result.iterator(); i.hasNext(); ) {
            doAddPhoto(i.next());
        }

        return result;
    }

    /**
     *
     */
    @Override
    public GoldCoinPhoto createPhoto(File file) throws Exception {
        PhotoId id = PhotoId.getNextId();
        GoldCoinPhoto result = PhotoUtil.createPhoto(file, id);
        addPhoto(result);
        return result;
    }
}
