package com.film.repository;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import com.film.entity.Category;

public class CategoryDao implements ICrud<Category> {

	@Override
	public void create(Category entity) {
		Session session = null;
		try {
			session = dataBaseConnectionHibernate();
			session.getTransaction().begin();
			session.save(entity);
			session.getTransaction().commit();
			System.out.println("Category data is added to DB");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Category to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public void delete(long id) {
		Session session = null;
		try {
			Category deletedCategory = find(id);
			if (deletedCategory != null) {
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.delete(deletedCategory);
				session.getTransaction().commit();
				System.out.println("Category data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Category to DB");
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
				session = dataBaseConnectionHibernate();
				session.getTransaction().begin();
				session.update(category);
				session.getTransaction().commit();
				System.out.println("Category data is added to DB");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Some problem occured while adding Category to DB");
		} finally {
			session.close();
		}

	}

	@Override
	public List<Category> listAll() {
		Session session = null;
		session = dataBaseConnectionHibernate();
		String query = "select category from Category as category";
		TypedQuery<Category> typedQuery = session.createQuery(query, Category.class);
		List<Category> categoryList = typedQuery.getResultList();
		categoryList.forEach(System.out::println);
		return categoryList;
	}

	@Override
	public Category find(long id) {
		Session session = dataBaseConnectionHibernate();
		Category category;
		try {
			category = session.find(Category.class, id);
			if (category != null) {
				System.out.println("Category Found--> " + category);
				return category;
			} else {
				System.out.println("Category not found");
				return null;
			}
		} catch (Exception e) {
			System.out.println("Some problem occured while adding Category to DB");
		} finally {
			session.close();
		}
		return null;
	}

}
