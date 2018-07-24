package com.examhelper.app.dao;

import com.examhelper.app.entity.ErrorRecognition;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IErrorRecognitionDao {
    /**
     * 单个插入
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
     * 更新
     *
     * @param errorRecognition
     */
    void update(ErrorRecognition errorRecognition);

    /**
     * 根据主键查询
     *
     * @param questionId
     * @return
     */
    ErrorRecognition selectErrorRecognition(int questionId);

    /**
     * 查询全部题目
     *
     * @return
     */
    List<ErrorRecognition> selectErrorRecognitions();
}
