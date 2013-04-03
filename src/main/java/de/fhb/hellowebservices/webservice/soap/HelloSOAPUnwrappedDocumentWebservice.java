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
package de.fhb.hellowebservices.webservice.soap;

import de.fhb.hellowebservices.manager.HelloManagerLocal;
import de.fhb.hellowebservices.util.logging.WebserviceLoggerInterceptor;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.ws.rs.core.Response;

/**
 *
 * Request:
 * <?xml version="1.0" encoding="UTF-8"?><S:Envelope
 * xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
 * <S:Header/>
 * <S:Body>
 * <nameTwo
 * xmlns="http://soap.webservice.hellowebservices.fhb.de/">bernd</nameTwo>
 * </S:Body>
 * </S:Envelope>
 *
 * Response:
 * <?xml version="1.0" encoding="UTF-8"?><S:Envelope
 * xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
 * <S:Body>
 * <helloWorldResponse
 * xmlns="http://soap.webservice.hellowebservices.fhb.de/">Hello ohoh
 * bernd!</helloWorldResponse>
 * </S:Body>
 * </S:Envelope>
 *
 * @author Michael Koppen <michael.koppen@googlemail.com>
 */
//SOAP- Webservice
@WebService
/*
 * Unwrapped Document literal SOAPBinding
 * Note: Only literal is accepted by WS-I.
 */
@SOAPBinding(
		parameterStyle = SOAPBinding.ParameterStyle.BARE,
		style = SOAPBinding.Style.DOCUMENT,
		use = SOAPBinding.Use.LITERAL)
@Stateless
@Interceptors(WebserviceLoggerInterceptor.class)
public class HelloSOAPUnwrappedDocumentWebservice {

	@EJB
	private HelloManagerLocal helloManager;

	@WebMethod
	public String helloWorld(
			//			@WebParam(name = "name") String name,
			@WebParam(name = "nameTwo") String nameTwo) {
		String name = "ohoh";
		return helloManager.helloWorld(name, nameTwo);
	}
}
