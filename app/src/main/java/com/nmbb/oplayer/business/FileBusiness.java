package com.nmbb.oplayer.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.nmbb.oplayer.database.SQLiteHelperOrm;
import com.nmbb.oplayer.exception.Logger;
import com.nmbb.oplayer.po.POMedia;

public final class FileBusiness {

	public static List<POMedia> getAllSortFiles() {
		SQLiteHelperOrm db = new SQLiteHelperOrm();
		try {
			Dao<POMedia, Long> dao = db.getDao(POMedia.class);
			QueryBuilder<POMedia, Long> query = dao.queryBuilder();
			query.orderBy("title_key", true);
			return dao.query(query.prepare());
		} catch (SQLException e) {
			Logger.e(e);
		} finally {
			if (db != null)
				db.close();
		}
		return new ArrayList<POMedia>();
	}
}
