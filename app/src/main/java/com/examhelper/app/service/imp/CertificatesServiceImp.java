package com.examhelper.app.service.imp;

import android.content.Context;

import com.examhelper.app.dao.ICertificatesDao;
import com.examhelper.app.dao.imp.CertificatesDaoImp;
import com.examhelper.app.entity.Certification;
import com.examhelper.app.service.ICertificatesService;

/**
 * Created by Administrator on 2018/7/29.
 */

public class CertificatesServiceImp implements ICertificatesService {
    ICertificatesDao certificatesDao;

    public CertificatesServiceImp(Context context) {
        certificatesDao = new CertificatesDaoImp(context);
    }


    @Override
    public void addCertificates(Certification certification) {
        certificatesDao.insert(certification);
    }

    @Override
    public void removeCertificates(Certification certification) {
        certificatesDao.delete(certification);

    }

    @Override
    public void updateCertificates(Certification certification) {
        certificatesDao.update(certification);

    }

    @Override
    public Certification queryCertificates() {
        return certificatesDao.select();
    }
}
