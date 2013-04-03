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

import javax.ejb.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class JacksonJsonProvider implements ContextResolver<ObjectMapper> {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		// since Jackson 1.9
		// this default configuration can be overwritten via annotations
		// e.g. @JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
		MAPPER.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
	}

	public JacksonJsonProvider() {
		System.out.println("Constructor called: de.fhb.account.webservice.rest.util.JacksonJsonProvider");
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		System.out.println("JacksonJsonProvider.getContext(): " + type);
		return MAPPER;
	}
}
