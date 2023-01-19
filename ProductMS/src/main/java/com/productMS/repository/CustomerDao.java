package com.productMS.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.productMS.entity.Customer;

public class CustomerDao implements IRepository<Customer> {
	@Override
	public void create(Customer entity) {
		Session session = null;

		try {
			session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Customer data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Customer data");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Customer deleteCustomer = find(id);
			if (deleteCustomer != null) {

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteCustomer);
				session.getTransaction().commit();

				System.out.println("Customer data is deleted from DB");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while Deleting Customer");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Customer entity) {
		Session session = null;

		try {
			Customer customer = find(id);
			if (customer != null) {
				customer.setProducts(entity.getProducts());

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(customer);
				session.getTransaction().commit();
				System.out.println("Successful Customer update ");

			}
		} catch (Exception e) {
			System.out.println("Some problem has occured while Customer UPDATE.");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Customer> listAll() {
		Session session = null;

		session = databaseConnectionHibernate();
		String query = "select customer from Customer as customer";
		TypedQuery<Customer> typedQuery = session.createQuery(query, Customer.class);
		List<Customer> customerList = typedQuery.getResultList();

		for (Customer customer : customerList) {
			System.out.println(customer);
		}
		return customerList;
	}

	@Override
	public Customer find(long id) {
		Session session = databaseConnectionHibernate();
		Customer customer;

		try {
			customer = session.find(Customer.class, id);

			if (customer != null) {
				System.out.println("Customer found: " + customer);
				return customer;
			} else {
				System.out.println("Customer not found");
				return customer;
			}

		} catch (Exception e) {
			System.out.println("Some problems has occured during Customer find operation.");

		} finally {
			session.close();
		}

		return null;
	}

	public Customer findByEmail(String email) {
		Session session = null;
		try {
			session = databaseConnectionHibernate();
			String query = "select customer from Customer as customer where customer.email = '" + email + "'";
			TypedQuery<Customer> typedQuery = session.createQuery(query, Customer.class);
			Customer customer = typedQuery.getSingleResult();
			return customer;
		} catch (Exception e) {
			System.out.println("Exception");
		}
		return null;
	}
	
	
	
}
