package com.crud.springboot.app.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crud.springboot.app.entity.Employee;


@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {
	
	//define entitymanager
	
	private EntityManager entityManager;
	
	//set up constructor injection
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		// TODO Auto-generated constructor stub
		
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		//get the current hibernate session
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Employee>theQuery = currentSession.createQuery("from Employee", Employee.class);
		
		List<Employee>employees = theQuery.getResultList();
		
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = entityManager.unwrap(Session.class);
		
		Employee theEmployee = currentSession.get(Employee.class, theId);
		
		
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		Session currentSession = entityManager.unwrap(Session.class);
		currentSession.saveOrUpdate(theEmployee);
		
	}

	@Override
	public void deleteById(int theId) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}

}
