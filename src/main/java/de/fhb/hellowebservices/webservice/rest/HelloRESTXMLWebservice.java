/*
 * Copyright (C) 2013 Michael Koppen
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.fhb.hellowebservices.webservice.rest;

import de.fhb.hellowebservices.manager.HelloManagerLocal;
import de.fhb.hellowebservices.util.logging.WebserviceLoggerInterceptor;
import de.fhb.hellowebservices.webservice.rest.util.JsonResponse;
import de.fhb.hellowebservices.webservice.rest.util.XmlResponse;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * URL:
 * http://localhost:8080/HelloWebservices/services/xml/helloWorld?name=bernd&nameTwo=bernd2
 *
 * Response:
 * <xmlResponse>
 * <data xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 * xmlns:xs="http://www.w3.org/2001/XMLSchema" xsi:type="xs:string">Hello bernd
 * bernd2!</data>
 * <status>SUCCESS</status>
 * </xmlResponse>
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
@Path("/xml")
@Stateless
@Interceptors(WebserviceLoggerInterceptor.class)
public class HelloRESTXMLWebservice {

	@EJB
	private HelloManagerLocal helloManager;

	@GET
	@Path("helloWorld")
	@Produces(MediaType.APPLICATION_XML)
	public Response helloWorld(@QueryParam("name") String name, @QueryParam("nameTwo") String nameTwo) {
		XmlResponse xml = new XmlResponse();
		try {
			xml.setStatus("SUCCESS");
			xml.setData(helloManager.helloWorld(name, nameTwo));
		} catch (Exception ex) {
			ex.printStackTrace();
			xml.setStatus("FAILED");
			xml.setErrorMsg(ex.getMessage()); //TODO Dont show direct errormessages to user in productionmode
		}
		return Response.ok().entity(xml).build();
	}
}
