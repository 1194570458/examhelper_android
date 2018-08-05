package com.examhelper.app.service;

import com.examhelper.app.entity.Question;

import java.util.List;

/**
 * Created by Administrator on 2018/7/24.
 */

public interface IQuestionService {

    /**
     * 添加一个试题数据
     * @param question
     */
    void addQuestion(Question question);

    /**
     * 添加多个试题数据
     * @param questionList
     */
    void addQuestions(List<Question> questionList);

    /**
     * 移除所有试题数据
     */
    void removeAllQuestion();

    /**
     * 更新一个试题数据
     * @param question
     */
    void updateQuestion(Question question);



    /**
     * 根据试题ID查询一个试题数据
     * @param questionId
     * @return
     */
    Question queryQuestion(int questionId);

    /**
     * 查询所有试题数据
     * @return
     */
    List<Question> queryAllQuestions();

    /**
     * 查询所有随机试题数据
     * @return
     */
    List<Question> queryRandomQuestions();

    /**
     * 查询所有收藏试题数据
     * @return
     */
    List<Question> queryCollectedQuestion();

    /**
     * 查询所有错题数据
     * @return
     */
    List<Question> queryErrorQuestion();
}
