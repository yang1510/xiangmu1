package com.example.wanandroid.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.wanandroid.home.bean.DatasBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DATAS_BEAN".
*/
public class DatasBeanDao extends AbstractDao<DatasBean, Long> {

    public static final String TABLENAME = "DATAS_BEAN";

    /**
     * Properties of entity DatasBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Mid = new Property(0, Long.class, "mid", true, "_id");
        public final static Property ApkLink = new Property(1, String.class, "apkLink", false, "APK_LINK");
        public final static Property Author = new Property(2, String.class, "author", false, "AUTHOR");
        public final static Property ChapterId = new Property(3, int.class, "chapterId", false, "CHAPTER_ID");
        public final static Property ChapterName = new Property(4, String.class, "chapterName", false, "CHAPTER_NAME");
        public final static Property Collect = new Property(5, boolean.class, "collect", false, "COLLECT");
        public final static Property CourseId = new Property(6, int.class, "courseId", false, "COURSE_ID");
        public final static Property Desc = new Property(7, String.class, "desc", false, "DESC");
        public final static Property EnvelopePic = new Property(8, String.class, "envelopePic", false, "ENVELOPE_PIC");
        public final static Property Fresh = new Property(9, boolean.class, "fresh", false, "FRESH");
        public final static Property Id = new Property(10, int.class, "id", false, "ID");
        public final static Property Link = new Property(11, String.class, "link", false, "LINK");
        public final static Property NiceDate = new Property(12, String.class, "niceDate", false, "NICE_DATE");
        public final static Property Origin = new Property(13, String.class, "origin", false, "ORIGIN");
        public final static Property Prefix = new Property(14, String.class, "prefix", false, "PREFIX");
        public final static Property ProjectLink = new Property(15, String.class, "projectLink", false, "PROJECT_LINK");
        public final static Property PublishTime = new Property(16, long.class, "publishTime", false, "PUBLISH_TIME");
        public final static Property SuperChapterId = new Property(17, int.class, "superChapterId", false, "SUPER_CHAPTER_ID");
        public final static Property SuperChapterName = new Property(18, String.class, "superChapterName", false, "SUPER_CHAPTER_NAME");
        public final static Property Title = new Property(19, String.class, "title", false, "TITLE");
        public final static Property Type = new Property(20, int.class, "type", false, "TYPE");
        public final static Property UserId = new Property(21, int.class, "userId", false, "USER_ID");
        public final static Property Visible = new Property(22, int.class, "visible", false, "VISIBLE");
        public final static Property Zan = new Property(23, int.class, "zan", false, "ZAN");
        public final static Property IsChaked = new Property(24, boolean.class, "isChaked", false, "IS_CHAKED");
    }


    public DatasBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DatasBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DATAS_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: mid
                "\"APK_LINK\" TEXT," + // 1: apkLink
                "\"AUTHOR\" TEXT," + // 2: author
                "\"CHAPTER_ID\" INTEGER NOT NULL ," + // 3: chapterId
                "\"CHAPTER_NAME\" TEXT," + // 4: chapterName
                "\"COLLECT\" INTEGER NOT NULL ," + // 5: collect
                "\"COURSE_ID\" INTEGER NOT NULL ," + // 6: courseId
                "\"DESC\" TEXT," + // 7: desc
                "\"ENVELOPE_PIC\" TEXT," + // 8: envelopePic
                "\"FRESH\" INTEGER NOT NULL ," + // 9: fresh
                "\"ID\" INTEGER NOT NULL ," + // 10: id
                "\"LINK\" TEXT," + // 11: link
                "\"NICE_DATE\" TEXT," + // 12: niceDate
                "\"ORIGIN\" TEXT," + // 13: origin
                "\"PREFIX\" TEXT," + // 14: prefix
                "\"PROJECT_LINK\" TEXT," + // 15: projectLink
                "\"PUBLISH_TIME\" INTEGER NOT NULL ," + // 16: publishTime
                "\"SUPER_CHAPTER_ID\" INTEGER NOT NULL ," + // 17: superChapterId
                "\"SUPER_CHAPTER_NAME\" TEXT," + // 18: superChapterName
                "\"TITLE\" TEXT," + // 19: title
                "\"TYPE\" INTEGER NOT NULL ," + // 20: type
                "\"USER_ID\" INTEGER NOT NULL ," + // 21: userId
                "\"VISIBLE\" INTEGER NOT NULL ," + // 22: visible
                "\"ZAN\" INTEGER NOT NULL ," + // 23: zan
                "\"IS_CHAKED\" INTEGER NOT NULL );"); // 24: isChaked
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DATAS_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DatasBean entity) {
        stmt.clearBindings();
 
        Long mid = entity.getMid();
        if (mid != null) {
            stmt.bindLong(1, mid);
        }
 
        String apkLink = entity.getApkLink();
        if (apkLink != null) {
            stmt.bindString(2, apkLink);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
        stmt.bindLong(4, entity.getChapterId());
 
        String chapterName = entity.getChapterName();
        if (chapterName != null) {
            stmt.bindString(5, chapterName);
        }
        stmt.bindLong(6, entity.getCollect() ? 1L: 0L);
        stmt.bindLong(7, entity.getCourseId());
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(8, desc);
        }
 
        String envelopePic = entity.getEnvelopePic();
        if (envelopePic != null) {
            stmt.bindString(9, envelopePic);
        }
        stmt.bindLong(10, entity.getFresh() ? 1L: 0L);
        stmt.bindLong(11, entity.getId());
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(12, link);
        }
 
        String niceDate = entity.getNiceDate();
        if (niceDate != null) {
            stmt.bindString(13, niceDate);
        }
 
        String origin = entity.getOrigin();
        if (origin != null) {
            stmt.bindString(14, origin);
        }
 
        String prefix = entity.getPrefix();
        if (prefix != null) {
            stmt.bindString(15, prefix);
        }
 
        String projectLink = entity.getProjectLink();
        if (projectLink != null) {
            stmt.bindString(16, projectLink);
        }
        stmt.bindLong(17, entity.getPublishTime());
        stmt.bindLong(18, entity.getSuperChapterId());
 
        String superChapterName = entity.getSuperChapterName();
        if (superChapterName != null) {
            stmt.bindString(19, superChapterName);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(20, title);
        }
        stmt.bindLong(21, entity.getType());
        stmt.bindLong(22, entity.getUserId());
        stmt.bindLong(23, entity.getVisible());
        stmt.bindLong(24, entity.getZan());
        stmt.bindLong(25, entity.getIsChaked() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DatasBean entity) {
        stmt.clearBindings();
 
        Long mid = entity.getMid();
        if (mid != null) {
            stmt.bindLong(1, mid);
        }
 
        String apkLink = entity.getApkLink();
        if (apkLink != null) {
            stmt.bindString(2, apkLink);
        }
 
        String author = entity.getAuthor();
        if (author != null) {
            stmt.bindString(3, author);
        }
        stmt.bindLong(4, entity.getChapterId());
 
        String chapterName = entity.getChapterName();
        if (chapterName != null) {
            stmt.bindString(5, chapterName);
        }
        stmt.bindLong(6, entity.getCollect() ? 1L: 0L);
        stmt.bindLong(7, entity.getCourseId());
 
        String desc = entity.getDesc();
        if (desc != null) {
            stmt.bindString(8, desc);
        }
 
        String envelopePic = entity.getEnvelopePic();
        if (envelopePic != null) {
            stmt.bindString(9, envelopePic);
        }
        stmt.bindLong(10, entity.getFresh() ? 1L: 0L);
        stmt.bindLong(11, entity.getId());
 
        String link = entity.getLink();
        if (link != null) {
            stmt.bindString(12, link);
        }
 
        String niceDate = entity.getNiceDate();
        if (niceDate != null) {
            stmt.bindString(13, niceDate);
        }
 
        String origin = entity.getOrigin();
        if (origin != null) {
            stmt.bindString(14, origin);
        }
 
        String prefix = entity.getPrefix();
        if (prefix != null) {
            stmt.bindString(15, prefix);
        }
 
        String projectLink = entity.getProjectLink();
        if (projectLink != null) {
            stmt.bindString(16, projectLink);
        }
        stmt.bindLong(17, entity.getPublishTime());
        stmt.bindLong(18, entity.getSuperChapterId());
 
        String superChapterName = entity.getSuperChapterName();
        if (superChapterName != null) {
            stmt.bindString(19, superChapterName);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(20, title);
        }
        stmt.bindLong(21, entity.getType());
        stmt.bindLong(22, entity.getUserId());
        stmt.bindLong(23, entity.getVisible());
        stmt.bindLong(24, entity.getZan());
        stmt.bindLong(25, entity.getIsChaked() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DatasBean readEntity(Cursor cursor, int offset) {
        DatasBean entity = new DatasBean( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // mid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // apkLink
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // author
            cursor.getInt(offset + 3), // chapterId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // chapterName
            cursor.getShort(offset + 5) != 0, // collect
            cursor.getInt(offset + 6), // courseId
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // desc
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // envelopePic
            cursor.getShort(offset + 9) != 0, // fresh
            cursor.getInt(offset + 10), // id
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // link
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // niceDate
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // origin
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // prefix
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // projectLink
            cursor.getLong(offset + 16), // publishTime
            cursor.getInt(offset + 17), // superChapterId
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // superChapterName
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // title
            cursor.getInt(offset + 20), // type
            cursor.getInt(offset + 21), // userId
            cursor.getInt(offset + 22), // visible
            cursor.getInt(offset + 23), // zan
            cursor.getShort(offset + 24) != 0 // isChaked
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DatasBean entity, int offset) {
        entity.setMid(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setApkLink(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAuthor(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setChapterId(cursor.getInt(offset + 3));
        entity.setChapterName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCollect(cursor.getShort(offset + 5) != 0);
        entity.setCourseId(cursor.getInt(offset + 6));
        entity.setDesc(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setEnvelopePic(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setFresh(cursor.getShort(offset + 9) != 0);
        entity.setId(cursor.getInt(offset + 10));
        entity.setLink(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setNiceDate(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setOrigin(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setPrefix(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setProjectLink(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setPublishTime(cursor.getLong(offset + 16));
        entity.setSuperChapterId(cursor.getInt(offset + 17));
        entity.setSuperChapterName(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setTitle(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setType(cursor.getInt(offset + 20));
        entity.setUserId(cursor.getInt(offset + 21));
        entity.setVisible(cursor.getInt(offset + 22));
        entity.setZan(cursor.getInt(offset + 23));
        entity.setIsChaked(cursor.getShort(offset + 24) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DatasBean entity, long rowId) {
        entity.setMid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DatasBean entity) {
        if(entity != null) {
            return entity.getMid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DatasBean entity) {
        return entity.getMid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
