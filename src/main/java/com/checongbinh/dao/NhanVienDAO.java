package com.checongbinh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.checongbinh.daoimp.NhanVienImp;
import com.checongbinh.entity.NhanVien;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class NhanVienDAO implements NhanVienImp{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public boolean KiemTraDangNhap(String email, String matkhau) {
		Session session = sessionFactory.getCurrentSession();
		try{
			NhanVien nhanVien = (NhanVien) session.createQuery("from NHANVIEN where email='"+email+"' AND matkhau='"+matkhau+"'").getSingleResult();
			if (nhanVien != null) {
				return true;
			}else{
				return false;
			}
		}catch (Exception e) {
			return false;
		}
		
	}

	@Override
	@Transactional
	public boolean ThemNhanVien(NhanVien nhanVien) {
		Session session = sessionFactory.getCurrentSession();
		int manhanvien = (int) session.save(nhanVien);
		if(manhanvien > 0){
			return true;
		}else{
			return false;
		}
		
	}
	
	
}
