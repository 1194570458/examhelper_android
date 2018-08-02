package com.examhelper.app.service.imp;

import android.content.Context;

import com.examhelper.app.dao.ICertificatesDao;
import com.examhelper.app.dao.imp.CertificatesDaoImp;
import com.examhelper.app.entity.Certification;
import com.examhelper.app.service.ICertificatesService;

import java.util.List;

/**
 * Created by Administrator on 2018/7/29.
 */

public class CertificatesServiceImp implements ICertificatesService {
    ICertificatesDao certificatesDao;

    public CertificatesServiceImp(Context context) {
        certificatesDao = new CertificatesDaoImp(context);
    }


    @Override
    public void addCertificate(Certification certification) {
        certificatesDao.insert(certification);
    }

    @Override
    public void addCertificates(List<Certification> certifications) {
        for (Certification certification : certifications) {
            certificatesDao.insert(certification);
        }
    }

    @Override
    public void removeCertificate(Certification certification) {
        certificatesDao.delete(certification);

    }

    @Override
    public void updateCertificate(Certification certification) {
        certificatesDao.update(certification);

    }

    @Override
    public Certification queryCertificate(int id) {
        return certificatesDao.selectById(id);
    }

    @Override
    public List<Certification> queryCertificates() {
        return certificatesDao.selectAll();
    }
}
