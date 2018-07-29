package com.examhelper.app.service;

import com.examhelper.app.entity.Certification;

/**
 * Created by Administrator on 2018/7/29.
 */

public interface ICertificatesService {
    void addCertificates(Certification certification);

    void removeCertificates(Certification certification);

    void updateCertificates(Certification certification);

    Certification queryCertificates();
}
