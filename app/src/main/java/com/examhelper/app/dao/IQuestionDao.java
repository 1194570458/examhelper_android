package com.examhelper.app.dao;

import com.examhelper.app.entity.Question;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IQuestionDao {
    /**
     * 更新或者插入
     *
     * @param question
     */
    void insert(Question question);

    /**
     * 删除所有
     *
     */
    void deleteAll();

    /**
     * 更新
     *
     * @param question
     */
    void update(Question question);

    /**
     * 根据主键查询
     *
     * @param questionId
     * @return
     */
    Question selectQuestion(int questionId);

    /**
     * 查询全部题目
     *
     * @return
     */
    List<Question> selectAllQuestions();

    /**
     * 查询全部收藏题目
     *
     * @return
     */
    List<Question> selectCollectedQuestion();

    /**
     * 查询做错题
     * @return
     */
    List<Question> selectErrorQuesttions();
}
