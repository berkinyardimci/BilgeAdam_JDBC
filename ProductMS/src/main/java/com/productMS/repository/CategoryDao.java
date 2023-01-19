package com.productMS.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.productMS.entity.Category;

public class CategoryDao implements IRepository<Category> {

	@Override
	public void create(Category entity) {
		Session session = null;

		try {
			session = databaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Category data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Category data");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Category deleteCategory = find(id);
			if (deleteCategory != null) {

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.remove(deleteCategory);
				session.getTransaction().commit();

				System.out.println("Category data is deleted from DB");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while Deleting Category");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Category entity) {
		Session session = null;

		try {
			Category category = find(id);
			if (category != null) {
				category.setName(entity.getName());

				session = databaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(category);
				session.getTransaction().commit();
				System.out.println("Successful Category update ");

			}
		} catch (Exception e) {
			System.out.println("Some problem has occured while Category UPDATE.");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public List<Category> listAll() {
		Session session = null;

		session = databaseConnectionHibernate();
		String query = "select category from Category as category";
		TypedQuery<Category> typedQuery = session.createQuery(query, Category.class);
		List<Category> categoryList = typedQuery.getResultList();

		for (Category category : categoryList) {
			System.out.println(category);
		}
		return categoryList;
	}

	@Override
	public Category find(long id) {
		Session session = databaseConnectionHibernate();
		Category category;

		try {
			category = session.find(Category.class, id);

			if (category != null) {
				System.out.println("Category found: " + category);
				return category;
			} else {
				System.out.println("Category not found");
				return category;
			}

		} catch (Exception e) {
			System.out.println("Some problems has occured during Category find operation.");

		} finally {
			session.close();
		}

		return null;
	}

}
