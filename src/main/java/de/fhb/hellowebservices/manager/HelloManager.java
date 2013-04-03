/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.hellowebservices.manager;

import de.fhb.hellowebservices.util.logging.ManagerLoggerInterceptor;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;

/**
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
@Stateless
@Interceptors(ManagerLoggerInterceptor.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class HelloManager implements HelloManagerLocal {

	@Override
	public String helloWorld(String name, String nameTwo) {
		checkIfNullObject(name);
		checkIfNullObject(nameTwo);
		return "Hello " + name + " " + nameTwo + "!";
	}

	@ExcludeClassInterceptors
	private void checkIfNullObject(Object object) {
		if (object == null) {
			throw new RuntimeException("RuntimeException: Recieved Object was Null.");
		}
	}
}
