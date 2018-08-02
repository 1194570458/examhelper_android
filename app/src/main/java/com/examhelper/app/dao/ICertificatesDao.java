package com.examhelper.app.dao;

import com.examhelper.app.entity.Certification;

import java.util.List;

/**
 * Created by Administrator on 2018/7/29.
 */

public interface ICertificatesDao {
    void insert(Certification certification);

    void delete(Certification certification);

    void update(Certification certification);

    Certification selectById(int id);

    List<Certification> selectAll();

}
