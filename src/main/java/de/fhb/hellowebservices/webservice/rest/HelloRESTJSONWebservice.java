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
 * URL:
 * http://localhost:8080/HelloWebservices/services/json/helloWorld?name=bernd&nameTwo=bernd2
 *
 * Response:
 * {"status":"SUCCESS","errorMsg":null,"fieldErrors":null,"data":"Hello bernd
 * bernd2!","version":1.0}
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
@Path("/json")
@Stateless
@Interceptors(WebserviceLoggerInterceptor.class)
public class HelloRESTJSONWebservice {

	@EJB
	private HelloManagerLocal helloManager;

	@GET
	@Path("ping")
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
		return "alive";
	}

	@GET
	@Path("helloWorld")
	@Produces(MediaType.APPLICATION_JSON)
	public Response helloWorld(@QueryParam("name") String name, @QueryParam("nameTwo") String nameTwo) {
		JsonResponse json = new JsonResponse();
		try {
			json.setStatus("SUCCESS");
			json.setData(helloManager.helloWorld(name, nameTwo));
		} catch (Exception ex) {
			ex.printStackTrace();
			json.setStatus("FAILED");
			json.setErrorMsg(ex.getMessage()); //TODO Dont show direct errormessages to user in productionmode
		}
		return Response.ok().entity(json).build();
	}
}
