package com.examhelper.app.service;

import com.examhelper.app.entity.Certification;

import java.util.List;

/**
 * Created by Administrator on 2018/7/29.
 */

public interface ICertificatesService {
    /**
     * 添加一个证书数据
     * @param certification
     */
    void addCertificate(Certification certification);

    /**
     * 添加多个证书数据
     * @param certifications
     */
    void addCertificates(List<Certification> certifications);

    /**
     * 删除一个证书数据
     * @param certification
     */
    void removeCertificate(Certification certification);

    /**
     * 更新一个证书数据
     * @param certification
     */
    void updateCertificate(Certification certification);

    /**
     * 根据证书ID查询一个证书数据
     * @param id
     * @return
     */
    Certification queryCertificate(int id);


    /**
     * 查询所有证书数据
     * @return
     */
    List<Certification> queryCertificates();

}
