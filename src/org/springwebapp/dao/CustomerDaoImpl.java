package org.springwebapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springwebapp.entity.Customer;

//need for DAO implementations
@Repository
public class CustomerDaoImpl implements CustomerDAO {
   
	//need the inject the required session 
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override

	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		
		//create query
		
		Query<Customer> theQuery= currentSession.createQuery("from Customer order by lastName",Customer.class);
		
		//execute the query and get the result
		
		List<Customer> customers=theQuery.getResultList();
		
		//return the required result
		
		return customers;
	}

	
	@Override
	public void saveCustomer(Customer theCustomer) {
		Session currentSession=sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(theCustomer);	
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session currentSession=sessionFactory.getCurrentSession();
		Customer theCustomer=currentSession.get(Customer.class,theId);
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		Session currentSession=sessionFactory.getCurrentSession();
		Query theQuery= currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId",theId);
		
		theQuery.executeUpdate();
		
	}

}
