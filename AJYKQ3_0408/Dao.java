package com.msc;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

public class Dao {
	
	// Vevo
	
	public void saveCustomer(Vevo vevo) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.msc.VevoMapper.insertCustomer", vevo);
		session.commit();
		session.close();
	}
	
	public List<Vevo> getAllCustomerData(){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Vevo> customers = session.selectList("com.msc.VevoMapper.findAllCustomer");
		session.close();
		return customers;
	}
	
	public Vevo getCustomerById(int vevoID){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Vevo customer = session.selectOne("com.msc.VevoMapper.findCustomerById", vevoID);
		session.close();
		return customer;
	}
	
	// Mozi
	
	public void saveCinema(Mozi mozi) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.msc.MoziMapper.insertCinema", mozi);
		session.commit();
		session.close();
	}
	
	public List<Mozi> getAllCinemaData(){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Mozi> cinemas = session.selectList("com.msc.MoziMapper.findAllCinemas");
		session.close();
		return cinemas;
	}
	
	public Mozi getCinemaById(int moziID){
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		Mozi mozi = session.selectOne("com.msc.MoziMapper.findCinemaById", moziID);
		session.close();
		return mozi;
	}
	
	public void deleteCinemaById(int moziID) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.delete("com.msc.MoziMapper.deleteCinemaById", moziID);
            session.commit();
        }
    }

	// Szelekciók
	
	public List<VM> findAllCinemaCustomerRelationships() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<VM> vm = session.selectList("com.msc.VMMapper.findAllCinemaCustomerRelationships");
		session.close();
		return vm;
	}
	
	public List<Mozi> findCinemasWithCustomers() {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		List<Mozi> mozi = session.selectList("com.msc.VMMapper.findCinemasWithCustomers");
		session.close();
		return mozi;
	}
	
	// Kapcsolat
	
	public void saveRelationship(VM vm) {
		SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
		session.insert("com.msc.VMMapper.insertRelationship", vm);
		session.commit();
		session.close();
    }
	
	public void deleteCinemaRelationshipById(int moziID) {
        try (SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession()) {
            session.delete("com.msc.VMMapper.deleteCinemaRelationship", moziID);
            session.commit();
        }
    }


}