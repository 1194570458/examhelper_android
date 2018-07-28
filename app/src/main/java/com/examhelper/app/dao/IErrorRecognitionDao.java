package com.examhelper.app.dao;

import com.examhelper.app.entity.ErrorRecognition;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IErrorRecognitionDao {
    /**
     * 更新或者插入
     *
     * @param errorRecognition
     */
    void insert(ErrorRecognition errorRecognition);

    /**
     * 删除
     *
     * @param errorRecognition
     */
    void delete(ErrorRecognition errorRecognition);


    /**
     * 查询全部题目
     *
     * @return
     */
    List<ErrorRecognition> selectErrorRecognitions();
}
