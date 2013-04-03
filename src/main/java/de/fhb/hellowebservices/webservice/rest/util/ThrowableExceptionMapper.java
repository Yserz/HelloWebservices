/*
 * Copyright (C) 2013 Michael Koppen <michael.koppen@googlemail.com>
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
package de.fhb.hellowebservices.webservice.rest.util;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

	private static final Response RESPONSE;
	private static final JsonResponse JSON = new JsonResponse("ERROR");

	static {
		RESPONSE = Response.status(500).entity(JSON).build();
	}

	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response toResponse(Throwable ex) {
		System.out.println("ThrowableExceptionMapper: " + ex.getClass());
		ex.printStackTrace();
		//usually you don't pass detailed info out (don't do this here in production environments)
		JSON.setErrorMsg(ex.getMessage());

		return RESPONSE;
	}
}
