package com.productMS.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.productMS.entity.Admin;

public class AdminDao implements IRepository<Admin> {

	@Override
	public void create(Admin entity) {
		Session session = null;

		try {
			session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Admin data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Admin data");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Admin deleteAdmin = find(id);
			if (deleteAdmin != null) {

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteAdmin);
				session.getTransaction().commit();

				System.out.println("Admin data is deleted from DB");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while Deleting Admin");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Admin entity) {
		Session session = null;

		try {
			Admin admin = find(id);
			if (admin != null) {
				admin.setEmail(entity.getEmail());
				admin.setFirstName(entity.getFirstName());
				admin.setLastName(entity.getLastName());

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(admin);
				session.getTransaction().commit();
				System.out.println("Successful Admin update ");

			}
		} catch (Exception e) {
			System.out.println("Some problem has occured while Admin UPDATE.");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Admin> listAll() {
		Session session = null;

		session = databaseConnectionHibernate();
		String query = "select admin from Admin as admin";
		TypedQuery<Admin> typedQuery = session.createQuery(query, Admin.class);
		List<Admin> AdminList = typedQuery.getResultList();

		for (Admin Admin : AdminList) {
			System.out.println(Admin);
		}
		return AdminList;
	}

	@Override
	public Admin find(long id) {
		Session session = databaseConnectionHibernate();
		Admin admin;

		try {
			admin = session.find(Admin.class, id);

			if (admin != null) {
				System.out.println("Admin found: " + admin);
				return admin;
			} else {
				System.out.println("Admin not found");
				return admin;
			}

		} catch (Exception e) {
			System.out.println("Some problems has occured during Admin find operation.");

		} finally {
			session.close();
		}

		return null;
	}

}
