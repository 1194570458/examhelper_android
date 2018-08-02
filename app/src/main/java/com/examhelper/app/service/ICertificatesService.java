package com.examhelper.app.service;

import com.examhelper.app.entity.Certification;

import java.util.List;

/**
 * Created by Administrator on 2018/7/29.
 */

public interface ICertificatesService {
    void addCertificate(Certification certification);

    void addCertificates(List<Certification> certifications);

    void removeCertificate(Certification certification);

    void updateCertificate(Certification certification);

    Certification queryCertificate(int id);

    List<Certification> queryCertificates();

}
