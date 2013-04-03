/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhb.hellowebservices.manager;

import javax.ejb.Local;

/**
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
@Local
public interface HelloManagerLocal {

	String helloWorld(String name, String nameTwo);
}
