package com.booking.persistence.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
 
 private static SessionFactory sessionFactory;
 private static SessionFactory buildSessionFactory() {
  try {
   // Creating the SessionFactory from hibernate.cfg.xml
   SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
   return sessionFactory;
   
  } catch(Throwable ex) {
   ex.printStackTrace();
   throw new ExceptionInInitializerError(ex);
  }
 }
 
 public static SessionFactory getSessionFactory() {
  if(sessionFactory == null) {
   sessionFactory = buildSessionFactory();
  }
  return sessionFactory;
 }

}

